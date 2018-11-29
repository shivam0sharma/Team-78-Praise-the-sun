//
//  ViewController.swift
//  Donation Tracker
//
//  Created by Shivam Sharma on 11/27/18.
//  Copyright © 2018 ShivamSharma. All rights reserved.
//

import UIKit
import CoreData

class LoginVC: UIViewController {

    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.hideKeyboardWhenTappedAround()
    }

    @IBAction func loginPressed(_ sender: UIButton) {
        if (usernameTextField.text != "" && passwordTextField.text != "") {
            let request = NSFetchRequest<NSFetchRequestResult>(entityName: "User")
            let username = self.usernameTextField.text
            let password = self.passwordTextField.text
            let predicate = NSPredicate(format: "username == %@", username!)
            request.predicate = predicate
            request.fetchLimit = 1
            
            do {
                let result = try context.fetch(request)
                if result.count == 0 {
                    let alert = UIAlertController(title: "Invalid Credentials", message: "Some of the information you entered is not correct", preferredStyle: .alert)
                    
                    alert.addAction(UIAlertAction(title: "Ok", style: .cancel, handler: nil))
                    self.present(alert, animated: true, completion: nil)
                } else {
                    let n = (result[0] as AnyObject).value(forKey: "username") as! String
                    let p = (result[0] as AnyObject).value(forKey: "password") as! String
                    
                    if (username == n && password == p) {
                        if let tabBar = (storyboard?.instantiateViewController(withIdentifier: "tabBarController")) {
                            self.present(tabBar, animated: true, completion: nil)
                        }
                    } else {
                        let alert = UIAlertController(title: "Invalid Credentials", message: "Some of the information you entered is not correct", preferredStyle: .alert)
                        
                        alert.addAction(UIAlertAction(title: "Ok", style: .cancel, handler: nil))
                        self.present(alert, animated: true, completion: nil)
                    }
                }
            } catch let error as NSError {
                print("Could not fetch \(error)")
            }
        }
    }
}


// Put this piece of code anywhere you like
extension UIViewController {
    func hideKeyboardWhenTappedAround() {
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(UIViewController.dismissKeyboard))
        tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
    }
    
    @objc func dismissKeyboard() {
        view.endEditing(true)
    }
}
