package yandex.algorithm2022upsolving

import yandex.writeFile
import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

//C. Почти квадратные числа

fun main(args: Array<String>) {
    almostSquareNumbers()
}

//19:00 - (15 мин перерыв) - 20:20 (1ч) > notOK - частичное решение - не проходит 4 тест, скорее всего там Long
//(очень медленный вариант)
fun almostSquareNumbers(){

    val input = BufferedReader(FileReader("input.txt"))

    val number = input.readLine().split(" ").map { it.toLong() }[0]

    var minCount = Int.MAX_VALUE

    val sqrtInt = sqrt(number.toDouble()).toLong()

    //println("num: $number, sqrtInt: $sqrtInt")
    var count = 0
    /*var iCount = sqrtInt
    while (iCount > 0) {
        val iAlmostSquare = iCount * (iCount + 1)
        println("--i: $iCount, iAlmostSquare: $iAlmostSquare, ${number - iAlmostSquare}")
        val tempCount1 = if (iAlmostSquare <= number) checkAlmostSquare(iAlmostSquare) else -1
        val tempCount2 = if (iAlmostSquare <= number) checkAlmostSquare(number - iAlmostSquare) else -1
        println("--i: $iCount, tempCount: $tempCount1 $tempCount2")

        if (tempCount1 != -1 && tempCount2 != -1 ) minCount = min(minCount, tempCount1 + tempCount2 )

        println("-i: $iCount, minCount: $minCount, ++${count++}")
        iCount /= 2
    }*/

    for (i in sqrtInt downTo 1) {
        val iAlmostSquare = i * (i + 1)
        //println("--i: $i, iAlmostSquare: $iAlmostSquare, ${number - iAlmostSquare}")
        val tempCount1 = if (iAlmostSquare <= number) checkAlmostSquare(iAlmostSquare) else -1
        val tempCount2 = if (iAlmostSquare <= number) checkAlmostSquare(number - iAlmostSquare) else -1

        if (tempCount1 != -1 && tempCount2 != -1 ) minCount = min(minCount, tempCount1 + tempCount2 )
        //println("--i: $i, tempCount: $tempCount1 $tempCount2, count: ${tempCount1 + tempCount2} - min: $minCount")
    }
    writeFile("$minCount", "output.txt")
}
private fun checkAlmostSquare(number: Long) : Int {

    if (number == 0L) return 0
    if (number == 1L || number < 0) return -1


    var answer = 0
    var sqrtInt = sqrt(number.toDouble()).toInt()


    loop@ while (sqrtInt > 0) {

        val iAlmostSquare = sqrtInt * (sqrtInt + 1)
        //println(" + sqrtInt: $sqrtInt, i: $iAlmostSquare, number: $number")

        if (iAlmostSquare <= number) {
            val check = checkAlmostSquare(number - iAlmostSquare)

            answer = if(check == -1) -1 else 1 + check

            //println(" + answer: $answer - Break")
            break@loop
        }
        else {
            sqrtInt--
        }
    }
    return answer
}

/*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

const val INPUT = "input.txt"
const val OUTPUT = "output.txt"

fun main(args: Array<String>) {
catsInArt()
}
fun catsInArt(){}

fun readFile(pathToInputFile: String): MutableList<String>{

val file = BufferedReader(FileReader(pathToInputFile))

val listString = mutableListOf<String>()
file.lines().forEach { listString.add(it) }

return listString
}

fun writeFile(string: String, pathToOutputFile: String){

val output = BufferedWriter(FileWriter(pathToOutputFile))

output.write(string)
output.flush()
}
*/