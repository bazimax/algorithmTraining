package tinkoff

import yandex.*
import kotlin.math.max
import kotlin.system.measureTimeMillis

fun runTest(nameFun: String,
            examples: Array<Pair<String, String>>,
            testedFunction: (String) -> String,
            numberTest: Int = -1,
            timeTestRepeat: Int = 1,
            printAnswer: Boolean = true
){
    println(" $nameFun _started")

    var maxTime = 0L
    var allTrue = true

    if (numberTest == -1) {

        repeat(examples.size) {

            var answer: String
            val correctAnswer = examples[it].second

            val time = measureTimeMillis { answer = testedFunction(examples[it].first) }
            maxTime = max(maxTime, time)



            if (answer != correctAnswer) allTrue = false
            println("$it ${answer == correctAnswer} : " + timeMillisFormat(time))

        }
        println("----------\n" + "$allTrue : " + timeMillisFormat(maxTime) + "\n")

    }
    else {
        var answer = ""
        repeat(timeTestRepeat) {

            //writeExample(input = examples[numberTest].first, correctAnswer = examples[numberTest].second)


            val time = measureTimeMillis { answer = testedFunction(examples[numberTest].first) }//measureTimeMillis(testedFunction)
            maxTime = max(maxTime, time)

            val correctAnswer = examples[numberTest].second

            println("x${it + 1} ${answer == correctAnswer} : " + timeMillisFormat(time))

        }
        if (timeTestRepeat > 1) {

            println("----------\n" + "maxTime : " + timeMillisFormat(maxTime))
        }

        println()
        if (printAnswer) println(answer)
    }
}


//blank solution
/*
import tinkoff.runTest
import java.util.*

fun main(args: Array<String>) {
    catsInArt()
    runTest("catsInArt", examplesX, ::catsInArtForTesting)
}

private val examplesX = arrayOf(
    Pair("12", "2"),
    Pair("12", "2"),
    //Pair("12", "2"),
)

//0:00 - 0:00 (0ч0мин) > notOK частичное решение (285ms, 20.96Mb, не прошел тест 19)
//0:00 - 0:00 (0мин) поправил > OK (274ms, 21.00Mb, 6 баллов)
private fun catsInArt(){
    val scan = Scanner(System.`in`)
    val a = scan.nextInt()

    println("")
}
private fun catsInArtForTesting(str: String) : String {
    return ""
}
*/



//console input
/*
import java.util.*
val scan = Scanner(System.`in`)
val k = scan.nextInt()

while (scan.hasNext()) {
    sum += scan.nextInt()
}
*/

//file input / output
/*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

val input = BufferedReader(FileReader("input.txt"))
val (n, l) = input.readLine().trim().split(" ").map { it.toInt() }
var answer = ""


val output = BufferedWriter(FileWriter("output.txt"))
output.write(answer)
output.flush()
 */