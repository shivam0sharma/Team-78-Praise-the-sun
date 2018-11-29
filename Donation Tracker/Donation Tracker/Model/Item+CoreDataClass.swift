//
//  Item+CoreDataClass.swift
//  Donation Tracker
//
//  Created by Shivam Sharma on 11/27/18.
//  Copyright © 2018 ShivamSharma. All rights reserved.
//
//

import Foundation
import CoreData

@objc(Item)
public class Item: NSManagedObject {
    public override func awakeFromInsert() {
        super.awakeFromInsert()
        self.created = Date() as NSDate
    }
}
