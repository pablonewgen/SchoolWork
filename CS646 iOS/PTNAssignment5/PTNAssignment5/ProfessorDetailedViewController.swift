//
//  ProfessorDetailedViewController.swift
//  PTNAssignment5
//
//  Created by Paul Truong Nguyen on 11/2/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class ProfessorDetailedViewController: UIViewController {
    @IBOutlet weak var firstName: UILabel!
    @IBOutlet weak var lastName: UILabel!
    @IBOutlet weak var office: UILabel!
    @IBOutlet weak var phone: UILabel!
    @IBOutlet weak var email: UILabel!
    @IBOutlet weak var totalRating: UILabel!
    @IBOutlet weak var averageRating: UILabel!
    @IBOutlet weak var viewCommentsButton: UIButton!
    @IBOutlet weak var rateCommentPost: UIButton!

    @IBAction func viewCommentsButton(_ sender: UIButton) {
        performSegue(withIdentifier: "professorComments", sender: self)
    }

    @IBAction func rateCommentButton(_ sender: Any) {
        performSegue(withIdentifier: "rateAndComment", sender: self)

    }
    
    var idToPass: Int = 0
    var firstNamePassed: String = ""
    var lastNamePassed: String = ""
    var officePassed: String = ""
    var phonePassed: String = ""
    var emailPassed: String = ""
    var totalRatingsPassed: Int = 0
    var averageRatingPassed: Double = 0.0

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        getJsonProfessorDetails()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func getJsonProfessorDetails() {
        if let url = URL(string: "http://bismarck.sdsu.edu/rateme/instructor/" + String(idToPass)) {
            let session = URLSession.shared
            let task = session.dataTask(with: url, completionHandler: getWebPageDetails)
            task.resume()
        }
        else {
            print("Unable to create URL")
        }
    }
    
    func getWebPageDetails(data:Data?, response:URLResponse?, error:Error?) -> Void {
        guard error == nil else {
            print("error: \(error!.localizedDescription)")
            return
        }

        let httpResponse = response as? HTTPURLResponse
        let status:Int = httpResponse!.statusCode
        
        if data != nil && (status == 200) {
            do {
                let json = try JSONSerialization.jsonObject(with: data!)
                let item = json as? [String: AnyObject]
                
                if item?["id"] as! Int == idToPass {
                    firstNamePassed = item?["firstName"] as! String
                    lastNamePassed = item?["lastName"] as! String
                    officePassed = item?["office"] as! String
                    phonePassed = item?["phone"] as! String
                    emailPassed = item?["email"] as! String
                    if let rating = item?["rating"] as? [String: AnyObject] {
                        averageRatingPassed = rating["average"] as! Double
                        totalRatingsPassed = rating["totalRatings"] as! Int
                    }
                }
                DispatchQueue.main.async() {
                    self.firstName.text = self.firstNamePassed
                    self.lastName.text = self.lastNamePassed
                    self.office.text = self.officePassed
                    self.phone.text = self.phonePassed
                    self.email.text = self.emailPassed
                    self.totalRating.text = String(self.totalRatingsPassed)
                    self.averageRating.text = String(self.averageRatingPassed)
                }
            } catch let error as NSError {
                print(error)
            }
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "professorComments" {
            let controller = segue.destination as! ProfessorCommentTableViewController
            controller.idPassed = idToPass
        }
        if segue.identifier == "rateAndComment" {
            let controller = segue.destination as! RateAndCommentViewController
            controller.idPassed = idToPass
        }
    
    }

}
