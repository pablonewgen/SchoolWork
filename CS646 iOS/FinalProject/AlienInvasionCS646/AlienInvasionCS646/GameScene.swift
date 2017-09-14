//
//  GameScene.swift
//  AlienInvasionCS646
//
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import SpriteKit
import GameplayKit

// Collision detection
// ***Remember to read spritekit documentation physics section***
struct PhysicsCategory {
    static let None: UInt32 = 0
    static let All: UInt32 = UInt32.max
    static let LaserShot: UInt32 = 0b10
    static let Alien: UInt32 = 0b1
}

// Vectors for Shot. Found from:
// iOS Swift Game Development Cookbook: Simple Solutions for Game Development Problems
// by Jonathon Manning, Paris Buttfield-Addison
func + (left: CGPoint, right: CGPoint) -> CGPoint {
    return CGPoint(x: left.x + right.x, y: left.y + right.y)
}

func - (left: CGPoint, right: CGPoint) -> CGPoint {
    return CGPoint(x: left.x - right.x, y: left.y - right.y)
}

func * (point: CGPoint, scalar: CGFloat) -> CGPoint {
    return CGPoint(x: point.x * scalar, y: point.y * scalar)
}

func / (point: CGPoint, scalar: CGFloat) -> CGPoint {
    return CGPoint(x: point.x / scalar, y: point.y / scalar)
}

#if !(arch(x86_64) || arch(arm64))
    func sqrt(a: CGFloat) -> CGFloat {
        return CGFloat(sqrtf(Float(a)))
    }
#endif

extension CGPoint {
    func length() -> CGFloat {
        return sqrt(x*x + y*y)
    }
    
    func normalized() -> CGPoint {
        return self / length()
    }
}
// End of vectors

class GameScene: SKScene, SKPhysicsContactDelegate {
    var background = SKSpriteNode(imageNamed: "bg5")
    let spaceship = SKSpriteNode(imageNamed: "Spaceship")
    let counter = SKLabelNode(fontNamed: "Arial-BoldMT")
    var healthLabel = SKLabelNode(fontNamed: "Arial-BoldMT")

    var aliensDestroyed = 0
    var health = 100
    
    
    override func didMove(to view: SKView) {
        background.zPosition = -1
        background.position = CGPoint(x: frame.size.width / 2, y: frame.size.height / 2)
        addChild(background)
        
        healthLabel.text = "Health: 100"
        healthLabel.fontSize = 20
        healthLabel.fontColor = SKColor.red
        healthLabel.position = CGPoint(x:self.frame.minX + 70, y:self.frame.minY + 10)
        addChild(healthLabel)
        
        counter.text = "Enemies Destroyed: 30"
        counter.fontSize = 20
        counter.fontColor = SKColor.red
        counter.position = CGPoint(x:self.frame.maxX - 125, y:self.frame.maxY - 20)
        addChild(counter)
        
        spaceship.setScale(0.15)
        spaceship.position = CGPoint(x: size.width * 0.1, y: size.height * 0.5)
        addChild(spaceship)
        
        physicsWorld.gravity = CGVector.zero
        physicsWorld.contactDelegate = self
        
        run(SKAction.repeatForever(SKAction.sequence([SKAction.run(spawnAlien),SKAction.wait(forDuration: 1.75)])))
    }
    
    // Generate random positive value
    func randomNumberGenerator() -> CGFloat {
        return CGFloat(Float(arc4random()) / 0xFFFFFFFF)
    }
    
    // Take random positive value and alter position
    func randomPosition(min: CGFloat, max: CGFloat) -> CGFloat {
        return randomNumberGenerator() * (max - min) + min
    }
    
    // Shot of spaceship
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first else {
            return
        }
        
        let touchLocation = touch.location(in: self)
        let laserShot = SKSpriteNode(imageNamed: "laserShot")
        laserShot.position = spaceship.position
        
        let offsetOfShot = touchLocation - laserShot.position
        if (offsetOfShot.x < 0) {
            return
        }
        addChild(laserShot)
        
        laserShot.physicsBody = SKPhysicsBody(rectangleOf: laserShot.size)
        laserShot.physicsBody?.isDynamic = true
        laserShot.physicsBody?.collisionBitMask = PhysicsCategory.None
        laserShot.physicsBody?.categoryBitMask = PhysicsCategory.LaserShot
        laserShot.physicsBody?.contactTestBitMask = PhysicsCategory.Alien
        laserShot.physicsBody?.usesPreciseCollisionDetection = true
        
        let direction = offsetOfShot.normalized()
        let distanceOfShot = direction * 550
        let shotDestination = distanceOfShot + laserShot.position
        let startShot = SKAction.move(to: shotDestination, duration: 2.0)
        let endShot = SKAction.removeFromParent()
        
        laserShot.run(SKAction.sequence([startShot, endShot]))
        
    }
    
    // Spawn the alien
    func spawnAlien() {
        let alien = SKSpriteNode(imageNamed: "alien")

        let currentYPosition = randomPosition(min: alien.size.height/2, max: size.height - alien.size.height/2)
        alien.position = CGPoint(x: size.width + alien.size.width/2, y: currentYPosition)
        addChild(alien)
        
        alien.physicsBody = SKPhysicsBody(rectangleOf: alien.size)
        alien.physicsBody?.isDynamic = true
        alien.physicsBody?.collisionBitMask = PhysicsCategory.None
        alien.physicsBody?.contactTestBitMask = PhysicsCategory.LaserShot
        alien.physicsBody?.categoryBitMask = PhysicsCategory.Alien
    
        let moveSpeedToAttack = randomPosition(min: CGFloat(1.75), max: CGFloat(4.5))
        let startMoving = SKAction.move(to: CGPoint(x: -alien.size.width/2, y: currentYPosition), duration: TimeInterval(moveSpeedToAttack))
        let stopMoving = SKAction.removeFromParent()
        let healthDecrease = SKAction.run() {
            self.health -= 10
        }
        healthLabel.text = "Health: " + String(health)
        
        let lose = SKAction.run() {
            if self.health <= 0 {
                let reveal = SKTransition.fade(withDuration: 0.5)
                let gameOverScene = GameOverScene(size: self.size, status: false)
                self.view?.presentScene(gameOverScene, transition: reveal)
            }
        }
        alien.run(SKAction.sequence([startMoving, stopMoving, healthDecrease, lose]))
    }
    
    // Shot and alien collide
    func shotsFired(laserShot: SKSpriteNode, alien: SKSpriteNode) {
        laserShot.removeFromParent()
        alien.removeFromParent()
        
        aliensDestroyed += 1
        counter.text = "Enemies Destroyed: " + String(30-aliensDestroyed)
        if (aliensDestroyed >= 30) {
            let reveal = SKTransition.fade(withDuration: 1.0)
            let gameOverScene = GameOverScene(size: self.size, status: true)
            self.view?.presentScene(gameOverScene, transition: reveal)
        }
    }
    
    // Collision Detetction function
    func didBegin(_ contact: SKPhysicsContact) {
        var objectOne: SKPhysicsBody
        var objectTwo: SKPhysicsBody
        
        if contact.bodyA.categoryBitMask < contact.bodyB.categoryBitMask {
            objectOne = contact.bodyA
            objectTwo = contact.bodyB
        }
        else {
            objectOne = contact.bodyB
            objectTwo = contact.bodyA
        }
        
        if ((objectOne.categoryBitMask & PhysicsCategory.Alien != 0) &&
            (objectTwo.categoryBitMask & PhysicsCategory.LaserShot != 0)) {
            shotsFired(laserShot: objectOne.node as! SKSpriteNode, alien: objectTwo.node as! SKSpriteNode)
        }
    }
    
}
