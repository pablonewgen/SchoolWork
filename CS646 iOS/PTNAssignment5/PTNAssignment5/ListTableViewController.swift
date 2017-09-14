//
//  ViewController.swift
//  PTNAssignment5
//
//  Created by Paul Truong Nguyen on 11/1/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class ListTableViewController: UITableViewController {
    var professorsList = [Professor]()
    var idArray: [Int] = []
    var idPassed: Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getJsonProfessorList()
        navigationController?.navigationBar.isTranslucent = false

        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func getJsonProfessorList() {
        if let url = URL(string: "http://bismarck.sdsu.edu/rateme/list") {
            let session = URLSession.shared
            let task = session.dataTask(with: url, completionHandler: getWebPage)
            task.resume()
        }
        else {
            print("Unable to create URL")
        }
    }
    
    func getWebPage(data:Data?, response:URLResponse?, error:Error?) -> Void {
        guard error == nil else {
            print("error: \(error!.localizedDescription)")
            return
        }
        
        let httpResponse = response as? HTTPURLResponse
        let status:Int = httpResponse!.statusCode
        
        if data != nil && (status == 200) {
            do {
                let json = try JSONSerialization.jsonObject(with: data!)
                let jsonArray = json as? [[String: AnyObject]]

                for item in jsonArray! {
                    let professor = Professor()
                    professor.id = item["id"] as! Int
                    professor.firstName = item["firstName"] as! String
                    professor.lastName = item["lastName"] as! String
                    self.professorsList.append(professor)
                }
                DispatchQueue.main.async() {
                    self.tableView.reloadData()
                }
            } catch let error as NSError {
                print(error)
            }
        }
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        performSegue(withIdentifier: "showDetail", sender: self)
    }

    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        if segue.identifier == "showDetail" {
            if let indexPath = tableView.indexPathForSelectedRow {
                let controller = segue.destination as! ProfessorDetailedViewController
                controller.idToPass = idArray[indexPath.row]
                idPassed = idArray[indexPath.row]
            }
        }
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return professorsList.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "ProfessorTableViewCell"
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as! ProfessorTableViewCell
        
        let professor = professorsList[indexPath.row]
        
        cell.nameLabel.text = professor.firstName + " " + professor.lastName
        cell.idLabel.text = "ID: " + String(professor.id)
        
        idArray.append(professor.id)
        
        return cell
    }
}

class Professor {
    var firstName = ""
    var lastName = ""
    var id = 0
}


