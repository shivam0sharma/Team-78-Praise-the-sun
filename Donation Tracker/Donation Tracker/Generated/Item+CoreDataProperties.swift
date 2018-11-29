//
//  Item+CoreDataProperties.swift
//  Donation Tracker
//
//  Created by Shivam Sharma on 11/27/18.
//  Copyright © 2018 ShivamSharma. All rights reserved.
//
//

import Foundation
import CoreData


extension Item {

    @nonobjc public class func fetchRequest() -> NSFetchRequest<Item> {
        return NSFetchRequest<Item>(entityName: "Item")
    }

    @NSManaged public var created: NSDate?
    @NSManaged public var shortDetail: String?
    @NSManaged public var longDetail: String?
    @NSManaged public var location: String?
    @NSManaged public var price: Double
    @NSManaged public var category: String?
    @NSManaged public var toCategory: Category?
    @NSManaged public var toLocation: Location?

}
