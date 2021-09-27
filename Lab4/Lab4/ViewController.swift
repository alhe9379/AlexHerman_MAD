//
//  ViewController.swift
//  Lab4
//
//  Created by Alexander Herman on 9/26/21.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var petNum: UILabel!
    @IBOutlet weak var combinedAge: UITextField!
    @IBOutlet weak var numToBuy: UILabel!
    @IBOutlet weak var stepperOutlet: UIStepper!
    @IBOutlet weak var textFieldOutlet: UITextField!
    


    @IBAction func stepper(_ sender: UIStepper) {
        let alert=UIAlertController(title: "Warning", message: "You have too many or too few pets!!!", preferredStyle: UIAlertController.Style.alert)
        let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
        alert.addAction(cancelAction) //adds the alert action to the alert object
        
        if(stepperOutlet.value == -1){
            present(alert, animated: true, completion: nil)
            stepperOutlet.value = 0
        }
        
        if(stepperOutlet.value == 9){
            present(alert, animated: true, completion: nil)
            stepperOutlet.value = 8
        }
        
        petNum.text = String(format: "%.0f", stepperOutlet.value) + " Pets"
    }
    
    //https://www.hackingwithswift.com/example-code/language/how-to-convert-a-string-to-an-int
    func calc() -> Double{
        let combinedAgeString = textFieldOutlet.text
        return (8 - stepperOutlet.value) * Double(combinedAgeString!)! * 0.2
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    //Cant seem to get this called when expected, just going to have the logic in dismissKeyboard
    //func textFieldDidEndEditing(_ textField: UITextField) {
    //    numToBuy.text = String(calc())
    //}
 
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(UIInputViewController.dismissKeyboard))
        
        view.addGestureRecognizer(tap)
        // Do any additional setup after loading the view.
    }
    
    //https://stackoverflow.com/questions/24126678/close-ios-keyboard-by-touching-anywhere-using-swift
    @objc func dismissKeyboard() {
        //Causes the view (or one of its embedded text fields) to resign the first responder status.
        view.endEditing(true)
        numToBuy.text = String(calc())
    }
    
}

