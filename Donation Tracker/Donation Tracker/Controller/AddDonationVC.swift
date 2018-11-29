//
//  AddDonationVC.swift
//  Donation Tracker
//
//  Created by Shivam Sharma on 11/27/18.
//  Copyright © 2018 ShivamSharma. All rights reserved.
//

import UIKit
import CoreData

class AddDonation: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    
    @IBOutlet weak var locationPicker: UIPickerView!
    @IBOutlet weak var shortDetailTextField: UITextField!
    @IBOutlet weak var fullDetailTextField: UITextField!
    @IBOutlet weak var priceTextField: UITextField!
    @IBOutlet weak var categorySegment: UISegmentedControl!
    
    var locations = [Location]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.hideKeyboardWhenTappedAround()
        
        locationPicker.delegate = self
        locationPicker.dataSource = self
        
        let hasVisitedThisPage = UserDefaults.standard.bool(forKey: "hasVisitedThisPage")
        
        if !hasVisitedThisPage {
            UserDefaults.standard.set(true, forKey: "hasVisitedThisPage")
            generateTestLocations()
        }
        
        getLocations()
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        let location = locations[row]
        return location.name
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return locations.count
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        // update when selected
    }
    
    func getLocations() {
        let fetchRequest: NSFetchRequest<Location> = Location.fetchRequest()
        
        do {
            self.locations = try context.fetch(fetchRequest)
            self.locationPicker.reloadAllComponents()
        } catch {
            
        }
    }
    
    @IBAction func addDonationPressed(_ sender: UIButton) {
        let item = Item(context: context)
        
        if let shortDetail = shortDetailTextField.text {
            item.shortDetail = shortDetail
        }
        
        if let fullDetail = fullDetailTextField.text {
            item.longDetail = fullDetail
        }
        
        if let price = priceTextField.text {
            item.price = (price as NSString).doubleValue
        }
        
        item.category = categorySegment.titleForSegment(at: categorySegment.selectedSegmentIndex)
        item.toLocation = locations[locationPicker.selectedRow(inComponent: 0)]
        
        ad.saveContext()
        
        self.tabBarController?.selectedIndex = 0
    }
    
    @IBAction func logoutPressed(_ sender: UIBarButtonItem) {
        if let loginVC = (storyboard?.instantiateViewController(withIdentifier: "loginVC")) {
            self.present(loginVC, animated: true, completion: nil)
        }
    }
    
    func generateTestLocations() {
        let location1 = Location(context: context)
        location1.name = "AFD Station 4"
        location1.address = "309 EDGEWOOD AVE SE, Atlanta, GA, 30332"
        location1.type = "Drop Off"
        location1.longitude = -84.37742
        location1.latitude = 33.75416
        location1.phoneNumber = "(404) 555 - 3456"
        location1.website = "www.afd04.atl.ga"
        
        let location2 = Location(context: context)
        location2.name = "BOYS & GILRS CLUB W.W. WOOLFOLK"
        location2.address = "1642 RICHLAND RD, Atlanta, GA, 30332"
        location2.type = "Store"
        location2.longitude = -84.43971
        location2.latitude = 33.73182
        location2.phoneNumber = "(404) 555 - 1234"
        location2.website = "www.bgc.wool.ga"
        
        let location3 = Location(context: context)
        location3.name = "PATHWAY UPPER ROOM CHRISTIAN MINISTRIES"
        location3.address = "1683 SYLVAN RD, Atlanta, GA, 30332"
        location3.type = "Warehouse"
        location3.longitude = -84.41853
        location3.latitude = 33.70866
        location3.phoneNumber = "(404) 555 - 5432"
        location3.website = "www.pathways.org"
        
        let location4 = Location(context: context)
        location4.name = "PAVILION OF HOPE INC"
        location4.address = "3558 EAST PONCE DE LEON AVE, SCOTTDALE, GA, 30079"
        location4.type = "Warehouse"
        location4.longitude = -84.25537
        location4.latitude = 33.80129
        location4.phoneNumber = "(404) 555 - 8765"
        location4.website = "www.pavhope.org"
        
        let location5 = Location(context: context)
        location5.name = "D&D CONVENIENCE STORE"
        location5.address = "2426 COLUMBIA DRIVE, DECATUR, GA, 30034"
        location5.type = "Drop Off"
        location5.longitude = -84.2521
        location5.latitude = 33.71747
        location5.phoneNumber = "(404) 555 - 9876"
        location5.website = "www.ddconv.com"
        
        let location6 = Location(context: context)
        location6.name = "KEEP NORTH FULTON BEAUTIFUL"
        location6.address = "470 MORGAN FALLS RD, Sandy Springs, GA, 30302"
        location6.type = "Store"
        location6.longitude = -84.3688
        location6.latitude = 33.96921
        location6.phoneNumber = "(770) 555 - 7321"
        location6.website = "www.knfb.org"
        
        ad.saveContext()
    }
}
