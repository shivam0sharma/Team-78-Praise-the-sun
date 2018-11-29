//
//  MapVC.swift
//  Donation Tracker
//
//  Created by Shivam Sharma on 11/28/18.
//  Copyright © 2018 ShivamSharma. All rights reserved.
//

import UIKit
import MapKit

class MapVC: UIViewController {

    @IBOutlet weak var mapView: MKMapView!
    var locationInfo: [String : Any]!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        mapView.register(MKMarkerAnnotationView.self, forAnnotationViewWithReuseIdentifier: MKMapViewDefaultAnnotationViewReuseIdentifier)
        
        let coordinate = CLLocationCoordinate2D(latitude: locationInfo!["latitude"] as! CLLocationDegrees, longitude: locationInfo!["longitude"] as! CLLocationDegrees)
        let annotation = LocationAnnotation(coordinate: coordinate, title: locationInfo!["name"] as! String, subtitle: locationInfo!["phone"] as! String)
        
        mapView.addAnnotation(annotation)
        mapView.setRegion(annotation.region, animated: true)
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

class LocationAnnotation: NSObject, MKAnnotation {
    var coordinate: CLLocationCoordinate2D
    var title: String?
    var subtitle: String?
    
    init(coordinate: CLLocationCoordinate2D, title: String?, subtitle: String?) {
        self.coordinate = coordinate
        self.title = title
        self.subtitle = subtitle
        super.init()
    }
    
    var region: MKCoordinateRegion {
        let span = MKCoordinateSpan(latitudeDelta: 0.05, longitudeDelta: 0.05)
        return MKCoordinateRegion(center: coordinate, span: span)
    }
}

extension MapVC: MKMapViewDelegate {
    func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        if let locationAnnotation = mapView.dequeueReusableAnnotationView(withIdentifier: MKMapViewDefaultAnnotationViewReuseIdentifier) as? MKMarkerAnnotationView {
            locationAnnotation.animatesWhenAdded = true
            locationAnnotation.titleVisibility = .adaptive
            locationAnnotation.subtitleVisibility = .adaptive
            return locationAnnotation
        }
        
        return nil
    }
}
