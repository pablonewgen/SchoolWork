//
//  FirstViewController.swift
//  PTNAssignment4
//
//  Created by Paul Truong Nguyen on 10/19/16.
//  Copyright © 2016 PaulTruongNguyen. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    @IBOutlet weak var countryPickerView: UIPickerView!
    @IBOutlet weak var foodPickerView: UIPickerView!
    @IBOutlet weak var foodPickedSlider: UISlider!
    
    func configureSlider(maximumValuePassed: Float) {
        foodPickedSlider.minimumValue = 0.0
        foodPickedSlider.maximumValue = maximumValuePassed
        foodPickedSlider.value = 0.0
        foodPickedSlider.isContinuous = true
    }

    @IBAction func sliderValueChanged(_ sender: UISlider) {
        foodPickerView.selectRow(Int(sender.value), inComponent: 0, animated: true)
    }
    
    
    var countryFoodItems: [String] = []
    let pickerCountryDataSource = ["India", "USA", "Mexico"]
    let pickerIndiaFoodDataSource = ["Avakaya", "Pesarattu", "Thukpa",
                                  "Thali", "Litti Chokha", "Maple",
                                  "Palak Paneer", "Rajma-Shawl",
                                  "Vindaloo", "Khaman", "Handva",
                                  "Bisi bele bath", "Pav Bhaji",
                                  "Eromba", "Chungdi Jhola"]
    let pickerUSAFoodDataSource = ["Hot Dog", "Pizza", "Hamburger",
                                  "Clam Chowder", "Succotash",
                                  "Fried Chicken", "Gumbo", "Grits",
                                  "Chitterlings", "Hushpuppies",
                                  "Cobbler"]
    let pickerMexicoFoodDataSource = ["Taco", "Quesadilla", "Pambazo",
                                 "Tamale", "Harache", "Alambre",
                                 "Enchilada", "Panita", "Gordita",
                                 "Tlayuda", "Sincronizada"]

    override func viewDidLoad() {
        super.viewDidLoad()
        self.countryPickerView.dataSource = self;
        self.countryPickerView.delegate = self;
        self.foodPickerView.dataSource = self;
        self.foodPickerView.delegate = self;
        countryFoodItems = pickerIndiaFoodDataSource
        configureSlider(maximumValuePassed: Float(pickerIndiaFoodDataSource.count - 1))
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if pickerView == countryPickerView {
            return pickerCountryDataSource.count
        }
        else if pickerView == foodPickerView {
            return countryFoodItems.count
        }
        else {
            return 0
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if pickerView == countryPickerView {
            return pickerCountryDataSource[row]
        }
        else if pickerView == foodPickerView {
            return countryFoodItems[row]
        }
        else {
            return "none"
        }
    }
    
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if pickerView == countryPickerView {
            if row == 0 {
                countryFoodItems = pickerIndiaFoodDataSource
                configureSlider(maximumValuePassed: Float(pickerIndiaFoodDataSource.count - 1))
                
            }
            else if row == 1 {
                countryFoodItems = pickerUSAFoodDataSource
                configureSlider(maximumValuePassed: Float(pickerUSAFoodDataSource.count - 1))
                
            }
            else if row == 2 {
                countryFoodItems = pickerMexicoFoodDataSource
                configureSlider(maximumValuePassed: Float(pickerMexicoFoodDataSource.count - 1))
                
            }
            else {
                countryFoodItems = []
            }
            foodPickerView.reloadAllComponents()
            foodPickerView.selectRow(0, inComponent: 0, animated: true)
        }
        else if pickerView == foodPickerView {
            if countryFoodItems == pickerIndiaFoodDataSource {
                foodPickedSlider.value = Float(row)
                
            }
            else if countryFoodItems == pickerUSAFoodDataSource {
                foodPickedSlider.value = Float(row)


            }
            else if countryFoodItems == pickerMexicoFoodDataSource {
                foodPickedSlider.value = Float(row)


            }
            else {
                foodPickedSlider.value = 0.0
            }
        }
    }
    
    


}

