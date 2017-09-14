//
//  FullCommentViewController.swift
//  PTNAssignment5
//
//  Created by Paul Truong Nguyen on 11/6/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class FullCommentViewController: UIViewController {
    @IBOutlet weak var commentDate: UILabel!
    @IBOutlet weak var fullComment: UILabel!
    var commentDatePassed: String = ""
    var fullCommentPassed: String = ""

    override func viewDidLoad() {
        super.viewDidLoad()
        
        commentDate.text = "Comment was made on: " + commentDatePassed
        fullComment.text = "\"" + fullCommentPassed + "\""

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
