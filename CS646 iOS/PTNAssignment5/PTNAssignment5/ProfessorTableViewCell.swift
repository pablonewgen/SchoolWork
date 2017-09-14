//
//  ProfessorTableViewCell.swift
//  PTNAssignment5
//
//  Created by Paul Truong Nguyen on 11/1/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class ProfessorTableViewCell: UITableViewCell {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var idLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
