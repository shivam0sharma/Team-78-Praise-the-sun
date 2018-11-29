//
//  SearchCell.swift
//  Donation Tracker
//
//  Created by Shivam Sharma on 11/27/18.
//  Copyright © 2018 ShivamSharma. All rights reserved.
//

import UIKit

class ItemCell: UITableViewCell {
    
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var locationLabel: UILabel!
    @IBOutlet weak var categoryLabel: UILabel!
    
    func configureCell(item: Item) {
        title.text = item.shortDetail
        priceLabel.text = "$\(item.price)"
        locationLabel.text = item.toLocation?.name
        categoryLabel.text = item.category
    }
}
