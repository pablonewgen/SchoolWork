//
//  ThirdViewController.swift
//  PTNAssignment4
//
//  Created by Paul Truong Nguyen on 10/23/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class ThirdViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var segmentControl: UISegmentedControl!
    @IBOutlet weak var progressView: UIView!
    @IBOutlet weak var textView: UIView!
    @IBOutlet weak var alertView: UIView!
    @IBOutlet weak var progressAI: UIActivityIndicatorView!
    @IBOutlet weak var progressSwitch: UISwitch!
    @IBOutlet weak var textField: UITextField!
    @IBOutlet weak var alertButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.textField.delegate = self
        progressSwitch.isOn = false
        textView.isHidden = true
        alertView.isHidden = true
        
        let tappedOutside = UITapGestureRecognizer(target: self, action: #selector(ThirdViewController.dismissKeyboard))
        self.view.addGestureRecognizer(tappedOutside)
        
        // Do any additional setup after loading the view.
    }
    
    func dismissKeyboard(){
        self.view.endEditing(true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func segmentChanged(_ sender: UISegmentedControl) {
        switch segmentControl.selectedSegmentIndex
        {
        case 0:
            dismissKeyboard()
            progressView.isHidden = false
            textView.isHidden = true
            alertView.isHidden = true
        case 1:
            progressView.isHidden = true
            textView.isHidden = false
            alertView.isHidden = true
        case 2:
            dismissKeyboard()
            progressView.isHidden = true
            textView.isHidden = true
            alertView.isHidden = false
        default:
            break; 
        }
    }
    
    @IBAction func switchChanged(_ sender: UISwitch) {
        if progressSwitch.isOn {
            progressAI.startAnimating()
        }
        else {
            progressAI.stopAnimating()
        }
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool{
        textField.resignFirstResponder()
        self.view.endEditing(true);
        return true;
    }
    
    @IBAction func buttonPressed(_ sender: UIButton) {
        let alert = UIAlertController(title: "PLEASE LIKE THE IPHONE", message: "Do you like the iPhone?",
                                      preferredStyle: UIAlertControllerStyle.alert);
        alert.addAction(UIAlertAction(title: "Sure.", style: UIAlertActionStyle.default, handler: nil))
        present(alert, animated: true, completion: nil)
    }

}
