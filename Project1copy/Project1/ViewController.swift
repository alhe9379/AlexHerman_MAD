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
//https://stackoverflow.com/questions/25448879/how-do-i-take-a-full-screen-screenshot-in-swift
class ViewController: UIViewController, PKToolPickerObserver, ImagePickerDelegate, VideoPickerDelegate {
    func didSelect(image: UIImage?) {
        guard let image = image else {
            return
        }
        self.imgView.image = image
    }
    
    
    @IBOutlet weak var imgView: UIImageView!
    @IBOutlet weak var ImagePickerButton: UIButton!
    var imagePicker: ImagePicker!
    var videoPicker: VideoPicker!
    
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
      
    //@IBAction func imagePickerButton(_ sender: UIButton) {
    @IBAction func imagePickerButton(_ sender: UIButton) {
    self.imagePicker = ImagePicker(viewController: self, delegate: self)
        self.imagePicker.present(from: sender)
    }
    
    //Cant test because simulator does not have any videos
    //@IBAction func videoPickerButton(_ sender: UIButton) {
    @IBAction func videoPickerButton(_ sender: UIButton) {
    self.videoPicker = VideoPicker(viewController: self, delegate: self)
        self.videoPicker.present(from: sender)
    }
    
    //@IBAction func saveButton(_ sender: UIButton) {
    @IBAction func saveButton(_ sender: UIButton) {
        screenShotMethod()
    }
    
    //https://stackoverflow.com/questions/25448879/how-do-i-take-a-full-screen-screenshot-in-swift
    //https://stackoverflow.com/questions/39283807/how-to-take-screenshot-of-portion-of-uiview
    func screenShotMethod() {
        //Create the UIImage
        UIGraphicsBeginImageContext(imgView.frame.size)
        view.layer.render(in: UIGraphicsGetCurrentContext()!)
        let image = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        //Save it to the camera roll
        UIImageWriteToSavedPhotosAlbum(image!, nil, nil, nil)
    }
}


