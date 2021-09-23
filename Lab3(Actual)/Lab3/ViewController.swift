//
//  ViewController.swift
//  Lab3
//
//  Created by Alex Herman on 9/20/21.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var image: UIImageView!
    
    @IBOutlet weak var label: UILabel!
    
    @IBOutlet weak var switchOutlet: UISwitch!
    
    @IBOutlet weak var segmentControlOutlet: UISegmentedControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func segmentControl(_ sender: UISegmentedControl) {
        
        if(sender.selectedSegmentIndex == 0){
            image.image = UIImage(named: "PINK")
            label.text="PIIIIINK"
            updateLowercase()
        }
        
        if(sender.selectedSegmentIndex == 1){
            image.image = UIImage(named: "GOLD")
            label.text="GOOOOOLD"
            updateLowercase()
        }

    }
    
    @IBAction func lowercase(_ sender: UISwitch) {
        if switchOutlet.isOn {
            label.text = label .text?.lowercased()
                } else {
                    label.text=label.text?.uppercased()
                }
    }
    
    @IBAction func slider(_ sender: UISlider) {
        let fontSize=sender.value
                let fontSizeCGFloat=CGFloat(fontSize)
                label.font=UIFont.systemFont(ofSize: fontSizeCGFloat)
    }
    
    func updateLowercase(){
        if switchOutlet.isOn {
            label.text=label.text?.lowercased()
        }
    }
}


