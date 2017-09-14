//
//  ViewController.swift
//  Assignment3
//
//  Created by Paul Truong Nguyen on 10/8/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var textInput: UITextField!
    @IBOutlet weak var textX: UITextField!
    @IBOutlet weak var textY: UITextField!
    @IBOutlet weak var alteredLabel: UILabel!
    let prefs = UserDefaults.standard

    override func viewDidLoad() {
        super.viewDidLoad()
        textX.keyboardType = UIKeyboardType.numberPad
        textY.keyboardType = UIKeyboardType.numberPad
        
        if let txtfield = prefs.string(forKey: "inputText") {
            textInput.text = txtfield
            alteredLabel.text = txtfield
        }
        else {
            alteredLabel.text = ""
        }
        
        if let xtxtfield = prefs.string(forKey: "xCoordinate") {
            textX.text = xtxtfield
        }
        else {
            textX.text = ""
        }
        
        if let ytxtfield = prefs.string(forKey: "yCoordinate") {
            textY.text = ytxtfield
        }
        else {
            textY.text = ""
        }
        
        if (!(textX.text?.isEmpty)! && !(textY.text?.isEmpty)!) {
            alteredLabel.center = CGPoint(x: Int(textX.text!)!, y: Int(textY.text!)!)
        }
        
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func updateText(_ sender: AnyObject) {
        alteredLabel.text = textInput.text
        
        if !(textInput.text?.isEmpty)! {
            prefs.set(textInput.text, forKey: "inputText")
        }
        
        if (!(textX.text?.isEmpty)! && !(textY.text?.isEmpty)!) {
            alteredLabel.center = CGPoint(x: Int(textX.text!)!, y: Int(textY.text!)!)
            prefs.set(Int(textX.text!)!, forKey: "xCoordinate")
            prefs.set(Int(textY.text!)!, forKey: "yCoordinate")
        }
        self.view.endEditing(true)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        var positionX:Int = 0
        var positionY:Int = 0
        if let touch = touches.first {
            let position = touch.location(in: self.view)
            positionX = Int(position.x)
            positionY = Int(position.y)
        }
        
        if (positionX > 0 && positionY > 0) {
            textX.text = String(positionX)
            textY.text = String(positionY)
            prefs.set(positionX, forKey: "xCoordinate")
            prefs.set(positionY, forKey: "yCoordinate")
            alteredLabel.center = CGPoint(x: positionX, y: positionY)
        }
        
    }

    
}

