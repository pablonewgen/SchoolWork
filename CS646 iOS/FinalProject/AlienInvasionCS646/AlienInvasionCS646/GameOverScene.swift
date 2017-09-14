//
//  GameOverScene.swift
//  AlienInvasionCS646
//
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import Foundation
import SpriteKit

class GameOverScene: SKScene {
    let background = SKSpriteNode(imageNamed: "bg5")
    let buttonRestart = SKSpriteNode(imageNamed: "power")
    let restartLabel = SKLabelNode(fontNamed: "Arial-BoldMT")
    let label = SKLabelNode(fontNamed: "Arial-BoldMT")


    init(size: CGSize, status:Bool) {
        
        super.init(size: size)
        
        background.zPosition = -1
        background.position = CGPoint(x: frame.size.width / 2, y: frame.size.height / 2)
        addChild(background)
        
        buttonRestart.position = CGPoint(x: size.width/2, y: (size.height/2) )
        buttonRestart.setScale(0.1)
        buttonRestart.name = "restart"
        buttonRestart.isHidden = false
        addChild(buttonRestart)
        
        restartLabel.fontColor = SKColor.red
        restartLabel.fontSize = 12
        restartLabel.text = "Press button to restart the game."
        restartLabel.position = CGPoint(x: size.width/2, y: (size.height/2) - 50)
        addChild(restartLabel)
        
        let message = status ? "Aliens Eliminated. You won!" : "You Lose"
        label.fontColor = SKColor.red
        label.fontSize = 40
        label.text = message
        label.position = CGPoint(x: size.width/2, y: (size.height/2) + 50)
        addChild(label)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        for touch: AnyObject in touches {
            let touchLocation = touch.location(in: self)
            if buttonRestart.contains(touchLocation) {
                let reveal = SKTransition.flipHorizontal(withDuration: 0.5)
                let scene = GameScene(size: self.size)
                self.view?.presentScene(scene, transition:reveal)
            }
        }
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

