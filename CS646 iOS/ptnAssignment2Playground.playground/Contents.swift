// Paul Truong Nguyen
// Red ID: 810922821

import Foundation

// Problem 1

enum Currency {
    case euro, usDollar, indianRupee, mexicanPeso
    var code: String {
        switch self {
        case .euro:
            return "EUR"
        case .usDollar:
            return "USD"
        case .indianRupee:
            return "INR"
        case .mexicanPeso:
            return "MXN"
        }
    }
    
    var symbol: String {
        switch self {
        case .euro:
            return "\u{20AC}"
        case .usDollar:
            return "\u{24}"
        case .indianRupee:
            return  "\u{20B9}"
        case .mexicanPeso:
            return "\u{24}"
        }
    }
    
    var exchangeRate: Double {
        switch self {
        case .euro:
            return 0.8904
        case .usDollar:
            return 1
        case .indianRupee:
            return 66.7
        case .mexicanPeso:
            return 18.88
        }
    }
}

//let eruo = Currency.euro.symbol
//let indi = Currency.indianRupee.code
//let usc = Currency.usDollar.exchangeRate


// Problem 2

struct Money {
    var amount: Double
    var typeCurrency: Currency
    
    static func +(first: Money, second: Money) -> Money{
        var returnMoney: Money
        var finalAmount: Double
        var tempAmountFirst: Double
        var tempAmountSecond: Double
        tempAmountFirst = first.amount / first.typeCurrency.exchangeRate           // First's Amount to USD
        tempAmountSecond = second.amount / second.typeCurrency.exchangeRate        // Second's Amount to USD
        finalAmount = (tempAmountFirst + tempAmountSecond) * first.typeCurrency.exchangeRate
        returnMoney = Money(amount: finalAmount, typeCurrency: first.typeCurrency)
        
        return returnMoney
    }
    
    static func -(first: Money, second: Money) -> Money{
        var returnMoney: Money
        var finalAmount: Double
        var tempAmountFirst: Double
        var tempAmountSecond: Double
        tempAmountFirst = first.amount / first.typeCurrency.exchangeRate           // First's Amount to USD
        tempAmountSecond = second.amount / second.typeCurrency.exchangeRate        // Second's Amount to USD
        finalAmount = (tempAmountFirst - tempAmountSecond) * first.typeCurrency.exchangeRate
        returnMoney = Money(amount: finalAmount, typeCurrency: first.typeCurrency)
        
        return returnMoney
    }
    
    func description() -> String {
        var returnDescription: String
        
        returnDescription = typeCurrency.symbol + String(describing: amount) + " " + typeCurrency.code
        
        return returnDescription
    }
}

//let money = Money(amount: 500, typeCurrency: Currency.usDollar)
//let test1 = Money(amount: 700, typeCurrency: Currency.mexicanPeso)
//let test2 = Money(amount: 700, typeCurrency: Currency.indianRupee)
//let test3 = test1 + test2
//let test4 = test1 - test2
//test3.description()
//test4.description()
//money.description()

// Problem 3

extension String {
    func phoneFormat() -> String {
        var cleanedString: String = ""
        for char in self.characters {
            let characterReplacing = "\(char)"
            if characterReplacing == " " || characterReplacing == "-" {
                cleanedString = cleanedString + ""
            }
            else {
                cleanedString = cleanedString + characterReplacing
            }
        }
        var phoneArray = [Character]()
        phoneArray = Array(cleanedString.characters)
        phoneArray.insert("-", at: 6)
        phoneArray.insert("-", at: 3)
        return String(phoneArray)
    }
}

//"6195946191".phoneFormat()
//"619 594 6191".phoneFormat()
//"619 5946191".phoneFormat()
//"619-594-6191".phoneFormat()

// Problem 4

struct PhoneNumber {
    var phoneNumber: String
    var phoneType: PhoneType
    
    enum PhoneType {
        case mobile, home, work, main, homeFax, workFax, pager, other
        var type: String {
            switch self {
            case .mobile:
                return "mobile"
            case .home:
                return "home"
            case .work:
                return "work"
            case .main:
                return "main"
            case .homeFax:
                return "home fax"
            case .workFax:
                return "work fax"
            case .pager:
                return "pager"
            case .other:
                return "other"
            }
        }
    }
    
    init (phoneNumber: String) {
        self.phoneNumber = phoneNumber
        self.phoneType = PhoneType.home
    }
    
    init(phoneNumber: String, phoneType: PhoneType) {
        self.phoneNumber = phoneNumber
        self.phoneType = phoneType
    }
    
    func isMobile() -> Bool {
        var mobileChecker: Bool = false
        if phoneType.type == "mobile" {
            mobileChecker = true
        }
        return mobileChecker
    }
    
    func isLocal() -> Bool {
        var returnIsLocal: Bool = false
        var areaCodeString: String = ""
        var areaCodeCounter: Int = 0
        for char in phoneNumber.characters {
            let character = "\(char)"
            if areaCodeCounter < 3 {
                areaCodeString = areaCodeString + character
                areaCodeCounter += 1
            }
        }
        if areaCodeString == "619" || areaCodeString == "858" {
            returnIsLocal = true
        }
        return returnIsLocal
    }
    
    func phoneFormatted() -> String {
        return phoneNumber.phoneFormat()
    }
    
    func description() -> String {
        var returnDescription: String
        returnDescription = phoneType.type + ": " + phoneFormatted()
        return returnDescription
    }
}

//let phone1 = PhoneNumber(phoneNumber: "8581819191", phoneType: PhoneNumber.PhoneType.mobile)
//let phone2 = PhoneNumber(phoneNumber: "6191819191", phoneType: PhoneNumber.PhoneType.work)
//let phone3 = PhoneNumber(phoneNumber: "7141819191", phoneType: PhoneNumber.PhoneType.other)
//phone1.isMobile()
//phone1.isLocal()
//phone1.description()
//phone2.isMobile()
//phone2.isLocal()
//phone2.description()
//phone3.isMobile()
//phone3.isLocal()
//phone3.description()

// Problem 5

extension String {
    func asPhoneNumber() -> PhoneNumber? {
        var returnPhoneNumber: PhoneNumber?
        var typeString: String = ""
        var numberString: String = ""
        var type: PhoneNumber.PhoneType = PhoneNumber.PhoneType.other
        var stringPieces: [String] = self.components(separatedBy: ": ")
        if stringPieces.count == 2 {
            typeString = stringPieces[0]
                switch typeString {
                case "mobile":
                    type = PhoneNumber.PhoneType.mobile
                case "home":
                    type = PhoneNumber.PhoneType.home
                case "work":
                    type = PhoneNumber.PhoneType.work
                case "main":
                    type = PhoneNumber.PhoneType.main
                case "homeFax":
                    type = PhoneNumber.PhoneType.homeFax
                case "workFax":
                    type = PhoneNumber.PhoneType.workFax
                case "pager":
                    type = PhoneNumber.PhoneType.pager
                case "other":
                    type = PhoneNumber.PhoneType.other
                default:
                    return returnPhoneNumber
                }
            numberString = stringPieces[1]
            let countofNumbers = Array(numberString.characters).count
            if countofNumbers == 12 {
                returnPhoneNumber = PhoneNumber(phoneNumber: numberString, phoneType: type)
            }
        }
        if returnPhoneNumber?.description() != self {
            return nil
        }
        return returnPhoneNumber
    }
}

//"work: 619-908-2344".asPhoneNumber()
//"mobile: 8888888".asPhoneNumber()
//"work: ".asPhoneNumber()
//"sdasdf:6659809721".asPhoneNumber()
//":".asPhoneNumber()
//"".asPhoneNumber()

// Problem 6

protocol Comparable {
    
}

struct Name: Comparable {
    var firstName: String
    var lastName: String
    
    static func < (lhs: Name, rhs: Name) -> Bool {
        return lhs.lastName < rhs.lastName || (lhs.lastName == rhs.lastName && lhs.firstName < rhs.firstName)
    }
    
    static func == (lhs: Name, rhs: Name) -> Bool {
        return lhs.lastName == rhs.lastName && lhs.firstName == rhs.firstName
    }
}

//let testName = Name(firstName: "Jack", lastName: "Nichols")

// Problem 7

class Person {
    var name: Name
    var phoneNumbers: [PhoneNumber] = []
    
    init(name: Name, phoneNumbers: [PhoneNumber]) {
        self.name = name
        self.phoneNumbers = phoneNumbers
    }
    
    func addPhoneNumber(number: String, type: PhoneNumber.PhoneType) {          // default type
        self.phoneNumbers.append(PhoneNumber.init(phoneNumber: number, phoneType: type))
    }
    
    func addPhoneNumber(number: PhoneNumber) {
        self.phoneNumbers.append(number)
    }
    
    func phoneNumber(type: PhoneNumber.PhoneType) -> PhoneNumber? {
        var returnPhoneNumber: PhoneNumber?
        
        for phoneNumber in phoneNumbers {
            if phoneNumber.phoneType == type {
                returnPhoneNumber = phoneNumber
            }
        }
        return returnPhoneNumber
    }
    
    func hasNumber(number: String) -> Bool {
        var returnFlag: Bool = false
        for phoneNumber in phoneNumbers {
            if phoneNumber.phoneFormatted() == number.phoneFormat() {
                returnFlag = true
            }
        }
        return returnFlag
    }
}

//let personTest = Person(name: Name.init(firstName: "Jason", lastName: "BoJason"), phoneNumbers: [])
//let phoneTester = PhoneNumber.init(phoneNumber: "5650908898", phoneType: PhoneNumber.PhoneType.mobile)
//personTest.addPhoneNumber(number: phoneTester)
//personTest.addPhoneNumber(number: "6198581077", type: PhoneNumber.PhoneType.pager)
//personTest.phoneNumber(type: PhoneNumber.PhoneType.pager)
//personTest.hasNumber(number: "5650908898")

// Problem 8

class ContactList {
    var contactList: [Person] = []
    
    init(contactList: [Person]) {
        self.contactList = contactList
    }
    
    func addPerson(person: Person) {
        self.contactList.append(person)
    }
    
    func getPerson(person: Person) -> Name {
        let personName: Name = person.name
        return personName
    }
    
    func orderedByName() -> [Person] {
        var returnPersonsList: [Person]
        returnPersonsList = contactList.sorted(by: { (p1: Person, p2: Person) -> Bool in
            getPerson(person: p1) < getPerson(person: p2)
        })
        return returnPersonsList
    }
    
    func phoneNumberFor(lastName: String) -> [PhoneNumber?] {
        var returnPhoneNumber: [PhoneNumber?] = []
        
        for person in contactList {
            if getPerson(person: person).lastName == lastName {
                returnPhoneNumber = person.phoneNumbers
                break
            }
        }
        return returnPhoneNumber
    }
    
    func nameForNumber(number: String) -> Person? {
        var returnPerson: Person?
        
        for person in contactList {
            if person.hasNumber(number: number) {
                returnPerson = person
            }
        }
        return returnPerson
    }
}

//let newContactListTester = ContactList(contactList: [])
//let jdName = Name(firstName:"Jamie", lastName: "Denners")
//let jdpn1 = PhoneNumber(phoneNumber: "8588888811")
//let jdpn2 = PhoneNumber(phoneNumber: "6199980011", phoneType: PhoneNumber.PhoneType.mobile)
//let jd = Person(name: jdName, phoneNumbers: [jdpn1, jdpn2])
//let pdName = Name(firstName:"Pamie", lastName: "Denners")
//let pdpn1 = PhoneNumber(phoneNumber: "8588889911")
//let pdpn2 = PhoneNumber(phoneNumber: "7149988811", phoneType: PhoneNumber.PhoneType.mobile)
//let pd = Person(name: pdName, phoneNumbers: [pdpn1, pdpn2])
//newContactListTester.addPerson(person: pd)
//newContactListTester.addPerson(person: jd)
//newContactListTester.nameForNumber(number: "8588888811")
//newContactListTester.phoneNumberFor(lastName: "Denners")
//let newOrderedList = newContactListTester.orderedByName()
//for person in newOrderedList {
//    print(person.name)
//}
