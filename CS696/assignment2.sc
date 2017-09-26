//#1
def sum_multiples_3_5( n:Int ) : Int = {
  var returnValue:Int = 0
  if (n <= 0)
    return returnValue
  val x = List.range(1,n)
  returnValue = x.filter(_%3 == 0).filterNot(_%15== 0).fold(0)(_+_)
  returnValue += x.filter(_%5 == 0).filterNot(_%15== 0).fold(0)(_+_)
  println(x.filter(_%3 == 0).filterNot(_%15== 0))
  println(x.filter(_%5 == 0).filterNot(_%15== 0))
  return returnValue
}
sum_multiples_3_5(25)

//#2
def pattern_count(string: String, pattern: String): Int = {
  var returnValue:Int = 0
  if (pattern == "" || string == "")
    return returnValue
  val stringList:List[String] = string.sliding(pattern.length()).toList
  val count:Int= stringList.filter(_ == pattern).count(_== pattern)
  println(stringList)
  returnValue = count
  return returnValue
}

pattern_count("abababa", "aba")
pattern_count("aaaaa", "aa")
pattern_count("Abcde","abc")
pattern_count("abcde","abc")
pattern_count("abcde","")
pattern_count("abcde","") == 0

//#3 ** N as a bigint??
def factorialReduce(n:BigInt) : BigInt = {
  var returnValue:BigInt = 0
  if (n < 1)
    return 1
  returnValue = (n to 1 by -1).reduceLeft(_ * _)

  return returnValue
}
// STILL QUESTIONABLE
factorialReduce(0)
factorialReduce(1)
factorialReduce(2)
factorialReduce(3)
factorialReduce(10)

//#4
def collectionAndMap (arrayStrings:Array[String], someMap:Map[String,Int]): Array[Int] = {
  var returnArray = Array[Int]()
  if (arrayStrings.isEmpty || someMap.isEmpty)
    return returnArray
  returnArray = arrayStrings collect someMap
  return returnArray
}
collectionAndMap(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5))

//#5

import scala.util.Random
def sampleNoRepeats (range:Range, n:Int): List[Int] = {
  var returnList = List[Int](0)
  if (range.isEmpty || n <= 0 || n > range.toList.length)
    return returnList
  returnList = Random.shuffle(range.toList).take(n)
  return returnList
}

sampleNoRepeats(1 to 10, 3)

//#6
def sampleError(): BigDecimal = {
  val r = new scala.util.Random()
  val arrayOfInts = Array.fill(100000)(BigDecimal(r.nextInt(50000)))
  val M:BigDecimal = arrayOfInts.sum/arrayOfInts.length
  var returnValue:BigDecimal = 0.0
  val testArray = Random.shuffle(arrayOfInts.toList).take(100)
  val N:BigDecimal = testArray.sum/testArray.length
  println(M)
  println(N)
  returnValue = (((N - M).abs) / M) * 100
  return returnValue
}
sampleError()

//#7
def sampleRandomA(): BigDecimal = {
  var returnValue:BigDecimal = 0.0
  val r = new scala.util.Random()
  val arrayOfInts = Array.fill(100000)(BigDecimal(r.nextInt(50000)))
  val realMean = arrayOfInts.sum/arrayOfInts.length
  //val realStd = scala.math.sqrt((arrayOfInts.map(_-realMean).map(x => x*x).sum/100000).doubleValue())
  val sampler:List[List[BigDecimal]] = List.fill(1000)(Random.shuffle(arrayOfInts.toList).take(100))
  val samplerMeans:List[BigDecimal] = sampler.map(_.sum/100).sorted
  //val sampleStd = sampler.map(_.map(_-samplerMeans.map(x=> x*x).sum/100))

  var rValue:BigDecimal = 0.0
  var topIndex = samplerMeans.length/2.toInt
  var bottomIndex = ((samplerMeans.length/2)-1).toInt
  var rValueRange = (bottomIndex to topIndex).length
  var topValue = samplerMeans(topIndex) - realMean
  var bottomValue = realMean - samplerMeans(bottomIndex)

  while(rValueRange <= .95*samplerMeans.length){
    println("RANGE LENGTH: " + rValueRange + " TOP R: " + topValue + " BOT R: " + bottomValue + " TOP INDEX: " + topIndex + " BOT INDEX: " + bottomIndex)
    if((samplerMeans(topIndex)-realMean).abs < (realMean - samplerMeans(bottomIndex)).abs){
      rValue = realMean - samplerMeans(bottomIndex)
    }
    else {rValue = samplerMeans(topIndex)-realMean}
    bottomIndex-=1
    topIndex+=1
    rValueRange = (bottomIndex to topIndex).length
    topValue = (samplerMeans(topIndex)-realMean)
    bottomValue = (realMean - samplerMeans(bottomIndex))
  }



  return returnValue
}
sampleRandomA()