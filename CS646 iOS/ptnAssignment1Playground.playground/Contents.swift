p

// Problem 1

func palindromeReader(max: Int) -> Int {
    
    var counter = 0
    var numOfPalindromes = 0
    
    while counter <= max {
        let value: String = String(counter)
        let valueReversed: String = String(value.characters.reverse())
        
        if value == valueReversed {
            numOfPalindromes++
        }
    counter++
    }
    return numOfPalindromes
}

//palindromeReader(1000)

// Problem 2

func sumMultiples3_5 (max: Int) -> Int {
    
    var sum = 0
    
    for i in 0..<max {
        if i % 3 == 0 || i % 5 == 0 {
            sum += i
        }
        if i % 15 == 0 {
            sum -= i
        }
    }
    return sum
}

//sumMultiples3_5(20)
//sumMultiples3_5(18)

// Problem 3

func patternCount(text: String, pattern: String) -> Int {
    
    let patternLength = pattern.characters.count - 1
    let textLength = text.characters.count
    var currentPosition = 0;
    var counter = 0;
    
    while currentPosition < textLength - 1 {
        let endPlace = patternLength + currentPosition
        
        if endPlace < textLength {
            let textStartIndex = text.startIndex.advancedBy(currentPosition)
            let textEndIndex = text.startIndex.advancedBy(endPlace)
            let subStringRange = textStartIndex...textEndIndex
            let subString = text.substringWithRange(subStringRange)
        
            if subString == pattern {
                counter++;
            }
        }
        currentPosition++;
    }
    return counter
}

//patternCount("aaaaa", pattern: "aa")

// Problem 4

func popularClasses(arguments: [Set<String>]...) -> Set<String> {
    var finalSet = Set<String>()
    var tempSet = Set<String>()
    var tempSetTwo = Set<String>()
    
    for argument in arguments {
        let indexSize = argument.count
        for index in 0..<indexSize {
            tempSet = argument[index]
            if index == 0 {
                finalSet = tempSet
            }
            else {
                tempSetTwo = finalSet
                finalSet = tempSetTwo.intersect(tempSet)
            }
        }
    }
    return finalSet
}

//let studentA: Set = ["CS101", "CS237", "CS520"]
//let studentB: Set = ["CS101", "Math245", "CS237"]
//let studentC: Set = ["CS237", "CS560"]
//let studentD: Set = ["CS237", "CS560", "Math189"]
//popularClasses([studentA, studentB, studentC])

// Problem 5

func average(numbers: [Int]) -> Double? {
    var returnValue:Double?
    var sum = 0.0
    var count = 0
    count = numbers.count
    
    if count > 0 {
        for number in numbers {
            sum += Double(number)
        }
        returnValue = sum/Double(count)
        return returnValue
    }
    else {
        return returnValue
    }
}

//average([2, 3, 7])

// Problem 6

func average2(numbers: [Int?]) -> Double? {
    
    var returnValue:Double?
    var sum = 0
    var count = 0
    count = numbers.count
    
    if count > 0 {
        for case let number? in numbers {
            sum += number
        }
        returnValue = Double(sum)/Double(count)
        return returnValue
    }
    else {
        return returnValue

    }

}

//average2([nil, 5, 9, nil, 10])

// Problem 7

func digitDistribution(numbers: [Int]) -> Dictionary<Int,Int> {
    var returnDictionary = Dictionary<Int,Int>()
    var arrayCounter = [Int](count: 10, repeatedValue: 0)
    
    for number in numbers {
        let tempNumberString = String(number)
        for character in tempNumberString.characters {
            let stringConvert = String(character)
            let arrayIndex = Int(stringConvert)
            arrayCounter[arrayIndex!] += 1
        }
    }
    for index in 0..<arrayCounter.count {
            if arrayCounter[index] != 0 {
                returnDictionary[index] = arrayCounter[index]
            }
    }
    return returnDictionary
}

//digitDistribution([112, 24, 15])

// Problem 8

func digitDistribution(numbers: [Int], count: String) -> Dictionary<Int,Int> {
    var returnDictionary = Dictionary<Int,Int>()
    var arrayCounter = [Int](count: 10, repeatedValue: 0)
    
    for number in numbers {
        let tempNumberString = String(number)
        for character in tempNumberString.characters {
            let stringConvert = String(character)
            let arrayIndex = Int(stringConvert)
            arrayCounter[arrayIndex!] += 1
        }
    }
    for index in 0..<arrayCounter.count {
        if arrayCounter[index] != 0 {
            if  arrayCounter[index] >= Int(count) {
                returnDictionary[index] = arrayCounter[index]
            }
        }
    }
    return returnDictionary
}

//digitDistribution([112, 24, 15, 78], count: "2")

// Problem 9

func digitDistribution2(numbers: [Int], count: String = "2") -> Dictionary<Int,Int> {
    var returnDictionary = Dictionary<Int,Int>()
    var arrayCounter = [Int](count: 10, repeatedValue: 0)
    
    for number in numbers {
        let tempNumberString = String(number)
        for character in tempNumberString.characters {
            let stringConvert = String(character)
            let arrayIndex = Int(stringConvert)
            arrayCounter[arrayIndex!] += 1
        }
    }
    for index in 0..<arrayCounter.count {
        if arrayCounter[index] != 0 {
            if  arrayCounter[index] >= Int(count) {
                returnDictionary[index] = arrayCounter[index]
            }
        }
    }
    return returnDictionary
}

//digitDistribution2([100, 205, 689, 430, 11, 24])
//digitDistribution2([100, 205, 689, 430, 11, 24], count: "3")

// Problem 10

func digitDistribution3(count: Int) ->(([Int]) -> Dictionary<Int,Int>) {
    func digitDistribution3Internal (numbers: [Int]) -> (Dictionary<Int,Int>) {
        var returnDictionary = Dictionary<Int,Int>()
        var arrayCounter = [Int](count: 10, repeatedValue: 0)
        
        for number in numbers {
            let tempNumberString = String(number)
            for character in tempNumberString.characters {
                let stringConvert = String(character)
                let arrayIndex = Int(stringConvert)
                arrayCounter[arrayIndex!] += 1
            }
        }
        for index in 0..<arrayCounter.count {
            if arrayCounter[index] != 0 {
                if  arrayCounter[index] >= count {
                    returnDictionary[index] = arrayCounter[index]
                }
            }
        }
        return returnDictionary
    }
    return digitDistribution3Internal
}

//let testA = digitDistribution3(1)
//testA([112, 24, 15, 78, 55])
//let testB = digitDistribution3(5)
//testB([555, 666, 757, 891, 111, 092, 883, 109, 234, 5463, 1234, 234236, 13523])
//let testC = digitDistribution3(2)
//testC([112, 24, 15])
