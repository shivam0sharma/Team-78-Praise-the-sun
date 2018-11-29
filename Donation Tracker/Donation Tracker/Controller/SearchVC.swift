//
//  SearchVC.swift
//  Donation Tracker
//
//  Created by Shivam Sharma on 11/27/18.
//  Copyright © 2018 ShivamSharma. All rights reserved.
//

import UIKit
import CoreData

class SearchVC: UIViewController, UITableViewDelegate, UITableViewDataSource, NSFetchedResultsControllerDelegate, UISearchBarDelegate {
    @IBOutlet weak var searchBar: UISearchBar!
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var segment: UISegmentedControl!
    
    var controller: NSFetchedResultsController<Item>!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.hideKeyboardWhenTappedAround()
        
        tableView.delegate = self
        tableView.dataSource = self
        searchBar.delegate = self
        
        //generateTestItems()
        attempFetch()
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        if let sections = controller.sections {
            return sections.count
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if let sections = controller.sections {
            let sectionInfo = sections[section]
            return sectionInfo.numberOfObjects
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "itemCell", for: indexPath) as! ItemCell
        configureCell(cell: cell, indexPath: indexPath as NSIndexPath)
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let objs = controller.fetchedObjects, objs.count > 0 {
            let item = objs[indexPath.row]
            performSegue(withIdentifier: "itemDetailVC", sender: item)
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "itemDetailVC" {
            if let destination = segue.destination as? ItemDetailVC {
                if let item = sender as? Item {
                    destination.item = item
                }
            }
        }
    }
    
    func configureCell(cell: ItemCell, indexPath: NSIndexPath) {
        // update cell
        let item = controller.object(at: indexPath as IndexPath)
        cell.configureCell(item: item)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 166
    }
    
    func attempFetch() {
        let fetchRequest: NSFetchRequest<Item> = Item.fetchRequest()
        
        if segment.selectedSegmentIndex == 0 {
            fetchRequest.predicate = (searchBar.text?.count)! > 0 ? NSPredicate(format:"shortDetail contains[cd] %@", searchBar.text!) : nil
        } else if segment.selectedSegmentIndex == 1 {
            fetchRequest.predicate = (searchBar.text?.count)! > 0 ? NSPredicate(format:"category contains[cd] %@", searchBar.text!) : nil
        }
    
        let dateSort = NSSortDescriptor(key: "created", ascending: false)
        fetchRequest.sortDescriptors = [dateSort]
        

        let controller = NSFetchedResultsController(fetchRequest: fetchRequest, managedObjectContext: context, sectionNameKeyPath: nil, cacheName: nil)
        
        controller.delegate = self
        self.controller = controller
        
        do {
            try controller.performFetch()
        } catch {
            let error = error as NSError
            print("\(error)")
        }
    }
    
    @IBAction func segmentValueChanged(_ sender: UISegmentedControl) {
        attempFetch()
        tableView.reloadData()
    }
    
    
    func controllerWillChangeContent(_ controller: NSFetchedResultsController<NSFetchRequestResult>) {
        tableView.beginUpdates()
    }
    
    func controllerDidChangeContent(_ controller: NSFetchedResultsController<NSFetchRequestResult>) {
        tableView.endUpdates()
    }
    
    func controller(_ controller: NSFetchedResultsController<NSFetchRequestResult>, didChange anObject: Any, at indexPath: IndexPath?, for type: NSFetchedResultsChangeType, newIndexPath: IndexPath?) {
        switch type {
        case .insert:
            if let indexPath = newIndexPath {
                tableView.insertRows(at: [indexPath], with: .fade)
            }
            break
        case .delete:
            if let indexPath = newIndexPath {
                tableView.deleteRows(at: [indexPath], with: .fade)
            }
            break
        case .update:
            if let indexPath = newIndexPath {
                let cell = tableView.cellForRow(at: indexPath) as! ItemCell
                // update the cell data
                configureCell(cell: cell, indexPath: indexPath as NSIndexPath)
            }
            break
        case .move:
            if let indexPath = newIndexPath {
                tableView.deleteRows(at: [indexPath], with: .fade)
                tableView.insertRows(at: [indexPath], with: .fade)
            }
            break
        }
    }
    
    func generateTestItems() {
        let item = Item(context: context)
        item.shortDetail = "iPhone"
        item.price = 1100
        item.longDetail = "Brand new iPhone 8 Plus with Face ID"
        //item.category = "Electronic"
        //item.location = "Best Buy"
        
        let item2 = Item(context: context)
        item2.shortDetail = "Mac Book"
        item2.price = 1100
        item2.longDetail = "Brand new Mac Book Pro 15' with Touch Bar"
        //item2.category = "Electronic"
        //item2.location = "Apple"
        
        let item3 = Item(context: context)
        item3.shortDetail = "Shirt"
        item3.price = 1100
        item3.longDetail = "Old fashion shirt"
        //item3.category = "Clothing"
        //item3.location = "Macy"
        
        ad.saveContext()
    }
    
    @IBAction func logoutPressed(_ sender: UIBarButtonItem) {
        if let loginVC = (storyboard?.instantiateViewController(withIdentifier: "loginVC")) {
            self.present(loginVC, animated: true, completion: nil)
        }
    }
    
    func scrollViewWillBeginDragging(_ scrollView: UIScrollView) {
        searchBar.endEditing(true)
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        self.searchBar.endEditing(true)
        attempFetch()
        tableView.reloadData()
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        attempFetch()
        tableView.reloadData()
    }
}
