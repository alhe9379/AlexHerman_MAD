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
class ViewController: UIViewController, PKToolPickerObserver, ImagePickerDelegate {
    func didSelect(image: UIImage?) {
        guard let image = image else {
            return
        }
        self.imgView.image = image
    }
    
    
    @IBOutlet weak var imgView: UIImageView!
    @IBOutlet weak var ImagePickerButton: UIButton!
    var imagePicker: ImagePicker!
    
    var canvasView: PKCanvasView!
    var imgForMarkup: UIImage?
    var toolPicker = PKToolPicker.init()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        self.canvasView = PKCanvasView.init(frame: self.imgView.frame)
        self.canvasView.isOpaque = false
        self.canvasView.frame = CGRect.init( x: imgView.frame.origin.x, y: imgView.frame.origin.y, width: imgView.frame.width, height: imgView.frame.height)
        self.view.addSubview(self.canvasView)
        
        //https://medium.com/flawless-app-stories/getting-started-with-pencilkit-on-ios-13-a4bda3323fd8
        toolPicker.setVisible(true, forFirstResponder: canvasView)
        toolPicker.addObserver(canvasView)
        toolPicker.addObserver(self)
        canvasView.becomeFirstResponder()
        self.canvasView.drawingPolicy = .anyInput
        
    }
      
    //This is entirely ripped from that dudes github... :(
    //Time to figure out how tf it works and make it work myself
    @IBAction func imagePickerButton(_ sender: UIButton) {
        self.imagePicker = ImagePicker(presentationController: self, delegate: self)
        self.imagePicker.present(from: sender)
    }
    
    
}
