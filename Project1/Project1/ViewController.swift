//
//  ViewController.swift
//  Project1
//
//  Created by Alexander Herman on 10/7/21.
//

import UIKit
import PencilKit


//https://www.raywenderlich.com/12198216-drawing-with-pencilkit-getting-started
//https://medium.com/flawless-app-stories/getting-started-with-pencilkit-on-ios-13-a4bda3323fd8
//https://www.hackingwithswift.com/example-code/media/how-to-choose-a-photo-from-the-camera-roll-using-uiimagepickercontroller
//https://theswiftdev.com/picking-images-with-uiimagepickercontroller-in-swift-5/
//https://github.com/theswiftdev/tutorials/tree/master/iOS/Pickers
class ViewController: UIViewController, PKToolPickerObserver {
    
    @IBOutlet weak var imgView: UIImageView!
    var canvasView: PKCanvasView!
    var imgForMarkup: UIImage?
    var toolPicker = PKToolPicker.init()
    var imagePicker = UIImagePickerController.init()
   
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
        self.canvasView.frame = CGRect.init( x: CGFloat.init(), y: CGFloat.init(), width: screenWidth!, height: screenHeight!)
        self.view.addSubview(self.canvasView)
        
        //https://medium.com/flawless-app-stories/getting-started-with-pencilkit-on-ios-13-a4bda3323fd8
        toolPicker.setVisible(true, forFirstResponder: canvasView)
        toolPicker.addObserver(canvasView)
        toolPicker.addObserver(self)
        canvasView.becomeFirstResponder()
        self.canvasView.drawingPolicy = .anyInput
        
    }
    
}

