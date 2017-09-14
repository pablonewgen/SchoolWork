//
//  ProfessorCommentTableViewController.swift
//  PTNAssignment5
//
//  Created by Paul Truong Nguyen on 11/6/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class ProfessorCommentTableViewController: UITableViewController {
    var idPassed: Int = 0
    var commentList = [Comment]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getJsonProfessorCommentList()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return  commentList.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "ProfessorCommentTableViewCell"
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as! ProfessorCommentTableViewCell
        
        let comment = commentList[indexPath.row]
        
        cell.dateLabel.text = comment.date
        cell.commentLabel.text = "\"" + comment.comment + "\""
        
        return cell
    }
    
    func getJsonProfessorCommentList() {
        if let url = URL(string: "http://bismarck.sdsu.edu/rateme/comments/" + String(idPassed)) {
            let session = URLSession.shared
            let task = session.dataTask(with: url, completionHandler: getWebPageComments)
            task.resume()
        }
        else {
            print("Unable to create URL")
        }
    }
    
    func getWebPageComments(data:Data?, response:URLResponse?, error:Error?) -> Void {
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
                    let comment = Comment()
                    comment.date = item["date"] as! String
                    comment.comment = item["text"] as! String
                    self.commentList.append(comment)
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
        performSegue(withIdentifier: "fullComment", sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "fullComment" {
            if let indexPath = tableView.indexPathForSelectedRow {
                let controller = segue.destination as! FullCommentViewController
                controller.commentDatePassed = commentList[commentList.index(indexPath.row, offsetBy: 0)].date
                controller.fullCommentPassed = commentList[commentList.index(indexPath.row, offsetBy: 0)].comment
            }
        }
    }

}

class Comment {
    var date: String = ""
    var comment : String = ""
}
