//
//  ViewController.swift
//  Project1
//
//  Created by Alexander Herman on 10/7/21.
//

import UIKit
import PencilKit

class ViewController: UIViewController {
    
    @IBOutlet weak var imgView: UIImageView!
    var canvasView: PKCanvasView!
    var imgForMarkup: UIImage?
    
    var origin: CGPoint?
    var screenSize: CGRect?
    var screenWidth: CGFloat?
    var screenHeight: CGFloat?
    
    override func viewDidLoad() {
        //https://stackoverflow.com/questions/24110762/swift-determine-ios-screen-size
        super.viewDidLoad()
        origin = CGPoint.init(x:0, y:0)
        screenSize = UIScreen.main.bounds
        screenWidth = screenSize!.width
        screenHeight = screenSize!.height
        // Do any additional setup after loading the view.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        self.canvasView = PKCanvasView.init(frame: self.imgView.frame)
        self.canvasView.isOpaque = false
        self.view.addSubview(self.canvasView)
    }
    
}

