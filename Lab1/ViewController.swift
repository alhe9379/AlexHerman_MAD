//
//  ViewController.swift
//  Lab1
//
//  Created by Alex Herman on 9/8/21.
//

import UIKit

class ViewController: UIViewController {
   

    @IBOutlet weak var text: UILabel!
    @IBOutlet weak var image: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBAction func catsbutton(_ sender: Any) {
        text.text = "I don't have a cat, but this one is cute."
        image.image=UIImage(named: "cat")
    }
    
    @IBAction func dogsbutton(_ sender: Any) {
        text.text = "This is my dog Milli!!!"
        image.image=UIImage(named: "milli")
    }
    

}

