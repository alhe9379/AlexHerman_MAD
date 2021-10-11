//
//  ImagePicker.swift
//  Project1
//
//  Created by Alexander Herman on 10/10/21.
//

import UIKit

public protocol VideoPickerDelegate: AnyObject {
    func didSelect(image: UIImage?)
}

open class VideoPicker: NSObject {

    private let pickerController: UIImagePickerController
    private weak var viewController: UIViewController?
    private weak var delegate: VideoPickerDelegate?

    public init(viewController: UIViewController, delegate: VideoPickerDelegate) {
        self.pickerController = UIImagePickerController()

        super.init()

        self.viewController = viewController
        self.delegate = delegate
    
        self.pickerController.delegate = self
        self.pickerController.allowsEditing = true
        self.pickerController.mediaTypes = ["public.movie"]
    }
    

    public func present(from sourceView: UIView) {
        self.pickerController.sourceType = .photoLibrary
        self.viewController?.present(self.pickerController, animated: true)
        
    }
    
    private func pickerController(_ controller: UIImagePickerController, didSelect image: UIImage?) {
        controller.dismiss(animated: true, completion: nil)
        
        self.delegate?.didSelect(image: image)
    }
}

extension VideoPicker: UIImagePickerControllerDelegate {
    
    //https://github.com/theswiftdev/tutorials/tree/master/iOS/Pickers
    public func imagePickerController(_ picker: UIImagePickerController,
                                      didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]) {
        guard let image = info[.editedImage] as? UIImage else {
            return self.pickerController(picker, didSelect: nil)
        }
        self.pickerController(picker, didSelect: image)
    }
}

extension VideoPicker: UINavigationControllerDelegate {
    
}
