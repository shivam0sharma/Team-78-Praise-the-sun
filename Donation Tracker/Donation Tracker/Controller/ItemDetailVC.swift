//
//  ItemDetailVC.swift
//  Donation Tracker
//
//  Created by Shivam Sharma on 11/27/18.
//  Copyright © 2018 ShivamSharma. All rights reserved.
//

import UIKit

class ItemDetailVC: UIViewController {

    var item: Item!
    
    @IBOutlet weak var shortDetailLabel: UILabel!
    @IBOutlet weak var fullDetailLabel: UILabel!
    
    @IBOutlet weak var priceLabel: UILabel!
    
    @IBOutlet weak var categoryLabel: UILabel!
    
    @IBOutlet weak var locationNameLabel: UILabel!
    @IBOutlet weak var locationAddressLabel: UILabel!
    @IBOutlet weak var locationTypeLabel: UILabel!
    @IBOutlet weak var locationPhoneNumberLabel: UILabel!
    @IBOutlet weak var locationWebsiteLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        shortDetailLabel.text = item.shortDetail
        fullDetailLabel.text = item.longDetail
        priceLabel.text = "$\(item.price)"
        categoryLabel.text = item.category
        
        // location detail
        locationNameLabel.text = item.toLocation?.name
        locationAddressLabel.text = item.toLocation?.address
        locationTypeLabel.text = item.toLocation?.type
        locationPhoneNumberLabel.text = item.toLocation?.phoneNumber
        locationWebsiteLabel.text = item.toLocation?.website
    }
    
    @IBAction func directionsPressed(_ sender: UIButton) {
        let locationInfo: [String : Any] = ["name" : item.toLocation?.name ?? "", "phone" : item.toLocation?.phoneNumber ?? "", "longitude" : item.toLocation?.longitude ?? 0, "latitude" : item.toLocation?.latitude ?? 0]
        performSegue(withIdentifier: "mapVC", sender: locationInfo)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "mapVC" {
            if let destination = segue.destination as? MapVC {
                if let locationInfo = sender as? [String : Any] {
                    destination.locationInfo = locationInfo
                }
            }
        }
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
