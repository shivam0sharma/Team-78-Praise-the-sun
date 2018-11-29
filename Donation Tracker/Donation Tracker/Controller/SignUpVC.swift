//
//  SignUpVC.swift
//  Donation Tracker
//
//  Created by Shivam Sharma on 11/27/18.
//  Copyright © 2018 ShivamSharma. All rights reserved.
//

import UIKit
import CoreData

class SignUpVC: UIViewController {
    
    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var confirmPasswordTextField: UITextField!
    @IBOutlet weak var userTypeSegmentControl: UISegmentedControl!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.hideKeyboardWhenTappedAround()
    }
    
    @IBAction func signUpPressed(_ sender: UIButton) {
        if ((usernameTextField.text != "") && (passwordTextField.text != "") && (passwordTextField.text == confirmPasswordTextField.text)) {
            
            let request = NSFetchRequest<NSFetchRequestResult>(entityName: "User")
            let username = self.usernameTextField.text
            let predicate = NSPredicate(format: "username == %@", username!)
            request.predicate = predicate
            request.fetchLimit = 1
            
            do {
                let result = try context.fetch(request)
                if result.count > 0 {
                    let alert = UIAlertController(title: "Username already Exists", message: "The username you have used already exists. Try a different one.", preferredStyle: .alert)
                    
                    alert.addAction(UIAlertAction(title: "Ok", style: .cancel, handler: nil))
                    self.present(alert, animated: true, completion: nil)
                } else {
                    // register user
                    let user = User(context: context)
                    
                    user.password = passwordTextField.text
                    user.type = userTypeSegmentControl.titleForSegment(at: userTypeSegmentControl.selectedSegmentIndex)
                    user.username = usernameTextField.text
                    
                    ad.saveContext()
                    
                    if let tabBar = (storyboard?.instantiateViewController(withIdentifier: "tabBarController")) {
                        self.present(tabBar, animated: true, completion: nil)
                    }
                }
            }  catch let error as NSError {
                print("Could not fetch \(error)")
            }
            
        } else {
            let alert = UIAlertController(title: "Invalid Credentials", message: "Some of the information you entered is not correct", preferredStyle: .alert)
            
            alert.addAction(UIAlertAction(title: "Ok", style: .cancel, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
    }
}
