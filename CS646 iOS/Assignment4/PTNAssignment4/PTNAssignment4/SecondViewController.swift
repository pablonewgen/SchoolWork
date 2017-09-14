//
//  SecondViewController.swift
//  PTNAssignment4
//
//  Created by Paul Truong Nguyen on 10/19/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var urlTextBox: UITextField!
    @IBOutlet weak var webView: UIWebView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.urlTextBox.delegate = self;
        self.urlTextBox.keyboardType = UIKeyboardType.webSearch

        let tappedOutside = UITapGestureRecognizer(target: self, action: #selector(SecondViewController.dismissKeyboard))
        self.view.addGestureRecognizer(tappedOutside)
        
        webView.loadRequest(NSURLRequest(url: NSURL(string: "https://www.google.com")! as URL) as URLRequest)
        
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    func dismissKeyboard(){
        self.view.endEditing(true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        urlTextBox.resignFirstResponder()
        self.view.endEditing(true);
        let urlString = urlTextBox.text
        let alert = UIAlertController(title: "Invalid URL", message: "Please insert a valid URL. ex: https://www.abcd.xyz",
                                      preferredStyle: UIAlertControllerStyle.alert);
        alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
        if validateUrl(urlString: urlString) {
            let userURL = NSURL(string: urlString!)
            webView.loadRequest(NSURLRequest(url: userURL as! URL) as URLRequest)
        }
        else {
            present(alert, animated: true, completion: nil)
        }
        return true
    }
    
    func validateUrl (urlString: String?) -> Bool {
        let urlRegEx = "(https)://((\\w)*|([0-9]*)|([-|_])*)+([\\.|/]((\\w)*|([0-9]*)|([-|_])*))+"
        return NSPredicate(format: "SELF MATCHES %@", urlRegEx).evaluate(with: urlString)
    }
}

