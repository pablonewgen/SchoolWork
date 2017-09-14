//
//  ProfessorCommentTableViewCell.swift
//  PTNAssignment5
//
//  Created by Paul Truong Nguyen on 11/6/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class ProfessorCommentTableViewCell: UITableViewCell {
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var commentLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
