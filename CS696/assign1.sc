import scala.io.Source

// #1
def sum_multiples_3_5( n:Int ) : Int = {
    val a = 0
    var sum:Int = 0

    for( a <- 1 until n){
      if( (a % 3 == 0) || (a % 5 == 0) ) {
        if (a % 15 != 0) {
          sum = sum + a
        }
      }
    }
    return sum
  }
sum_multiples_3_5(20)
sum_multiples_3_5(500)

// #2
def patternCount(string: String, pattern: String): Int = {
  val patternLength = pattern.length()
  val i = 0
  var counter:Int = 0
  for (i <- 0 to string.length()) {
    if (i + patternLength < string.length()+ 1){
      val subString = string.substring(i, i + patternLength)
      if (subString == pattern) {
        counter += 1
      }
    }
  }
  return counter
}
patternCount("abababa","aba")
patternCount("aaaaa","aa")
patternCount("Abcde","abc")
patternCount("abcde","abc")

// #3
def product(s : String): Int = {
  var unicodeSum:Int = 0
  val stringCleaned = s.replaceAll("[^a-zA-Z]+", "")
  unicodeSum = stringCleaned.product.toInt
  return unicodeSum
}
product("Hello")
product("12")

// #4
def noDuplicates(ints : Array[Int]): Array[Int] = {
  val arrayOfInts = ints.distinct
  return arrayOfInts
}

val test1 = Array(1, 2, 3, 1, 3, 6, 6)
val test2 = Array(1, 2, 3, 1, 3, 6, 6, 6, 7, 2, 5, 9, 8, 8, 5, 1, 9)
noDuplicates(test1)
noDuplicates(test2)

// #5
def floatArrayError(): Double = {
  val i:Int = 0
  var error:Double = 0
  val arrayOfDoubles = new Array[Double] (1000000)
  for (i <- 1 until 1000000 ) {
    arrayOfDoubles(i) = 0.00001f
  }
  val floatVersion:Float = (arrayOfDoubles.sum).toFloat
  error = (math.abs((arrayOfDoubles.sum) - floatVersion))/floatVersion
  return error
}
floatArrayError()

// #6
def accurateSummation() = {
  val firstSum = (0.0001f + 8000.0f)
  val firstExpectedValue = 8000.0 + 0.0001
  println("First sum was: " + firstSum + " . But was expected to be: " + firstExpectedValue)
  val firstError = (math.abs(firstSum - firstExpectedValue))/ firstExpectedValue
  println("The margin of error was: " + firstError)

  val secondSum = (90000000 + 0.000000001)
  val secondA:BigDecimal = BigDecimal(0.000000001)
  val secondB:BigDecimal = BigDecimal(90000000)
  val secondExpectedValue:BigDecimal = secondA + secondB

  println("Second sum was: " + secondSum + " . But was expected to be: " + f"$secondExpectedValue%8.9f")
  val secondError = ((secondSum - secondExpectedValue).abs)/ secondExpectedValue
  println("The margin of error was: " + secondError)
}
accurateSummation()

// #7
def randomInts(n:Int, range:Int): Array[Int] = {
  val r = new scala.util.Random(range)
  val arrayOfInts = new Array[Int] (n)
  val i:Int = 0
  for (i <- 0 until n) {
    arrayOfInts(i) = r.nextInt(range)
  }
  return arrayOfInts
}
randomInts(10, 100)
randomInts(30, 7)
randomInts(5, 22)

// #8 NOT DONE
def writingFile() = {
  import java.io._
  val i: Int = 0
  val j: Int = 0
  val k: Int = 0
  val range = Int.MaxValue/2
  val medianA: Int = 0
  val medianB: Int = 0
  val medianC: Int = 0
  var string1000: String = ""
  var string100000: String = ""
  var string10000000: String = ""
  val r = new scala.util.Random()

  val pw1000 = new PrintWriter("E:\\Schoolwork\\CS696\\assignment1\\src\\main\\scala\\1000.txt")
  val pw100000 = new PrintWriter("E:\\Schoolwork\\CS696\\assignment1\\src\\main\\scala\\100000.txt")
  val pw10000000 = new PrintWriter("E:\\Schoolwork\\CS696\\assignment1\\src\\main\\scala\\10000000.txt")

  for (i <- 0 until 1000) {
    val fillerA = r.nextInt(range)
    pw1000.write(fillerA.toString + "\r\n")
  }

  for (j <- 0 until 100000) {
    val fillerB = r.nextInt(range)
    pw100000.write(fillerB.toString + "\r\n")
  }

  for (k <- 0 until 10000000) {
    val fillerC = r.nextInt(range)
    pw10000000.write(fillerC.toString + "\r\n")
  }

  pw1000.close
  pw100000.close
  pw10000000.close
}

def avgAndMedian() = {
  def average(arr: Array[BigInt]): BigInt = {
    var avg: BigInt = 0
    avg = (arr.sum)/(arr.length)
    return avg
  }

  def median(arr: Array[BigInt]): BigInt = {
    var median: BigInt = 0
    val (topHalf, bottomHalf) = arr.sorted.splitAt(arr.length/2)
    if(arr.length % 2 == 0){
     median = ((topHalf.last + bottomHalf.head)/2)
    }
    else {
      median = bottomHalf.head
    }
    return median
  }

  val fileA = "E:\\Schoolwork\\CS696\\assignment1\\src\\main\\scala\\1000.txt"
  val fileB = "E:\\Schoolwork\\CS696\\assignment1\\src\\main\\scala\\100000.txt"
  val fileC = "E:\\Schoolwork\\CS696\\assignment1\\src\\main\\scala\\10000000.txt"

  val arrayforA = (Source.fromFile(fileA).getLines()).toArray
  val intArrayA = arrayforA.map(BigInt(_))

  val timeAvgAStart = System.nanoTime
  val avgA = average(intArrayA)
  val timeAvgA = System.nanoTime() - timeAvgAStart

  val timeMedianAStart = System.nanoTime
  val medianA = median(intArrayA)
  val timeMedianA = System.nanoTime() - timeMedianAStart
  println("Average for " + fileA + ": average: " + avgA + ", timing: (In nano seconds) " + timeAvgA)
  println("Median for " + fileA + ": median: " + medianA + " , timing: (In nano seconds) " + timeMedianA)
  println()

  val arrayforB = (Source.fromFile(fileB).getLines()).toArray
  val intArrayB = arrayforB.map(BigInt(_))

  val timeAvgBStart = System.nanoTime
  val avgB = average(intArrayB)
  val timeAvgB = System.nanoTime() - timeAvgBStart

  val timeMedianBStart = System.nanoTime
  val medianB = median(intArrayB)
  val timeMedianB = System.nanoTime() - timeMedianBStart
  println("Average for " + fileB + ": average: " + avgB + ", timing: (In nano seconds) " + timeAvgB)
  println("Median for " + fileB + ": median: " + medianB + " , timing: (In nano seconds) " + timeMedianB)
  println()

  val arrayforC = (Source.fromFile(fileC).getLines()).toArray
  val intArrayC = arrayforC.map(BigInt(_))

  val timeAvgCStart = System.nanoTime
  val avgC = average(intArrayC)
  val timeAvgC = System.nanoTime() - timeAvgCStart

  val timeMedianCStart = System.nanoTime
  val medianC = median(intArrayC)
  val timeMedianC = System.nanoTime() - timeMedianCStart
  println("Average for " + fileC + ": average: " + avgC + ", timing: (In nano seconds) " + timeAvgC)
  println("Median for " + fileC + ": median: " + medianC + " , timing: (In nano seconds) " + timeMedianC)

}
//writingFile()
avgAndMedian()





// #9
def minmax(values: Array[Int]): (Int, Int) = {
  val minValue = values.reduceLeft(_ min _)
  val maxValue = values.reduceLeft(_ max _)
  return (minValue, maxValue)
}
minmax(Array(1, 2, 3, 1, 3, 6, 6))
minmax(Array(1, 2, 3, 1, 3, 6, 6, 6, 7, 2, 5, 9, 8, 8, 5, 1, 9))


// #10
class Car(val manufacturer:String, val modelName:String, val modelYear:Int = -1, var licensePlate:String = "") {

  def this(manufacturer: String, modelName: String) {
    this(manufacturer, modelName, -1, "")
  }

  def this(manufacturer: String, modelName: String, modelYear: Int) {
    this(manufacturer, modelName, modelYear, "")
  }

  def this(manufacturer: String, modelName: String, licensePlate: String) {
    this(manufacturer, modelName, -1, licensePlate)
  }

  override def toString: String = {
    return "%d %s %s, License Plate: %s".format(modelYear, manufacturer, modelName, licensePlate)
  }
}

val testCar1 = new Car("Honda", "Civic", 2003, "ABCD123")
testCar1.toString
val testCar2 = new Car("Honda", "Civic")
testCar2.toString
val testCar3 = new Car("Honda", "Civic", 2003)
testCar3.toString
val testCar4 = new Car("Honda", "Civic", "ABCD123")
testCar4.toString