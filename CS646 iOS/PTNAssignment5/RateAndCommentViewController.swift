//
//  RateAndCommentViewController.swift
//  PTNAssignment5
//
//  Created by Paul Truong Nguyen on 11/7/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class RateAndCommentViewController: UIViewController , UITextFieldDelegate{
    var ratingGiven: Int = 0
    var idPassed: Int = 0

    @IBOutlet weak var rateValue: UILabel!
    @IBOutlet weak var rateSliderDefaults: UISlider!
    @IBAction func rateSlider(_ sender: UISlider) {
        let currentValue = Int(sender.value)
        rateValue.text = String(currentValue)
    }
    @IBAction func submitRatingButton(_ sender: Any) {
        submitRating()
        navigationController?.popViewController(animated: true)
    }
    @IBOutlet weak var commentField: UITextField!
    @IBAction func submitCommentButton(_ sender: Any) {
        submitComment()
        navigationController?.popViewController(animated: true)
    }
    
    @IBAction func rateAndCommentButton(_ sender: UIButton) {
        let currentValue = Int(rateSliderDefaults.value)
        let commentText = commentField.text
        
        if  rateValue.text != "" && commentText != "" {
            ratingGiven = currentValue
            postRating()
            postJsonComment(text: commentText!)
        }
        else if (rateValue.text == "") {
            alertStatus(type: "Rating")
        }
        else if (commentText == "") {
            alertStatus(type: "Comment")
        }
        else {
            alertStatus(type: "Rating and Comment")
        }
        navigationController?.popViewController(animated: true)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.commentField.delegate = self
        rateSliderDefaults.maximumValue = 5.0
        rateSliderDefaults.minimumValue = 1.0
        
        let tapped: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(RateAndCommentViewController.dismissKeyboard))
        view.addGestureRecognizer(tapped)
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func dismissKeyboard() {
        view.endEditing(true)
        view.resignFirstResponder()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return false
    }
    
    func postRating() {
        if let url = URL(string: "http://bismarck.sdsu.edu/rateme/rating/" + String(idPassed) + "/" + String(ratingGiven)) {
            let session = URLSession.shared
            let task = session.dataTask(with: url, completionHandler: uploadResponse)
            task.resume()
        }
        else {
            print("Unable to create URL")
        }
    }
    
    func submitRating() {
        let currentValue = Int(rateSliderDefaults.value)
        if  rateValue.text != "" {
            ratingGiven = currentValue
            postRating()
        }
        else {
            alertStatus(type: "Rating")
        }
    }
    
    func submitComment() {
        let commentText = commentField.text
        if commentText != "" {
            postJsonComment(text: commentText!)
        }
        else {
            alertStatus(type: "Comment")
        }
    }
    
    func alertStatus(type: String) {
        let alert = UIAlertController(title: "Missing " + type  , message: "Please fill in a " + type, preferredStyle: UIAlertControllerStyle.alert)
        alert.addAction(UIAlertAction(title: "Okay", style: UIAlertActionStyle.default, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
    
    func postJsonComment(text: String)  {
        if let url = URL(string: "http://bismarck.sdsu.edu/rateme/comment/" + String(idPassed)) {
            var mutableRequest = URLRequest.init(url: url)
            mutableRequest.httpMethod = "POST"
            mutableRequest.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
            let session = URLSession.shared
            let task = session.uploadTask(with: mutableRequest,
                                      from: text.data(using:.utf8), completionHandler: uploadResponse)
        task.resume()
        }
        else {
            print("Unable to create URL")
        }
    }
    
    func uploadResponse(data:Data?, response:URLResponse?, error:Error?) -> Void {
        guard error == nil else {
            print("error: \(error!.localizedDescription)")
            return
        }
        let httpResponse = response as? HTTPURLResponse
        let status:Int = httpResponse!.statusCode
        if status != 200,
            let error = String(data: data!, encoding: String.Encoding.utf8){
            print(error)
            return
        }
    }
    
}
