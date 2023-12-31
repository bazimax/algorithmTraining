package yandex

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.max
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

/**
 * Byte	    8	-128	                            127                                     10^2
 * Short	16	-32768	                            32767                                   10^4
 * Int	    32	-2,147,483,648 (-2^31)	            2,147,483,647 (2^31 - 1)                10^9
 * Long	    64	-9,223,372,036,854,775,808 (-2^63)	9,223,372,036,854,775,807 (2^63 - 1)    10^18
 *
 * ULong > 10^18
 */

const val INPUT_FILE = "input.txt"
const val OUTPUT_FILE = "output.txt"
const val CORRECT_ANSWER_FILE = "files/correctAnswer.txt"

fun readFile(pathToInputFile: String = INPUT_FILE): MutableList<String>{

    val file = BufferedReader(FileReader(pathToInputFile))

    val listString = mutableListOf<String>()
    file.lines().forEach { listString.add(it) }

    return listString
}
fun writeFile(string: String, pathToOutputFile: String = OUTPUT_FILE){

    val output = BufferedWriter(FileWriter(pathToOutputFile))

    output.write(string)
    output.flush()
}

fun nanoTimeRun(function: () -> Unit, repeat: Int = 1) {
    repeat(repeat) {
        val time = measureNanoTime(function)//measureNanoTime { function() }

        val ns = time % 1000000
        val msTime = time / 1000000
        val ms = msTime % 1000
        val s = msTime / 1000

        val zeroMS = "0".repeat(3 - ms.toString().length)
        val zeroNS = "0".repeat(6 - ns.toString().length)

        var ans = ""

        ans += if (s > 0L) {
            "$s." + "$zeroMS$ms." + "$zeroNS$ns" + "ns"
        }
        else if (ms > 0L) {
            "$ms." + "$zeroNS$ns" + "ns"
        }
        else {
            "$ns" + "ns"
        }

        println(ans)
    }
    println("----------")
}
fun timeMillisRun(function: () -> Unit, repeat: Int = 1) {
    repeat(repeat) {
        val time = measureTimeMillis(function)

        val ms = time % 1000
        val s = time / 1000

        var ans = ""

        ans += if (s > 0L) {
            val zero = "0".repeat(3 - ms.toString().length)
            "$s.$zero$ms" + "s"
        }
        else {
            "$ms" + "ms"
        }

        println(ans)
    }
    println("----------")
}

fun nanoTimeFormat(time: Long) : String {

    val ns = time % 1000000
    val msTime = time / 1000000
    val ms = msTime % 1000
    val s = msTime / 1000

    val zeroMS = "0".repeat(3 - ms.toString().length)
    val zeroNS = "0".repeat(6 - ns.toString().length)

    var ans = ""

    ans += if (s > 0L) {
        "$s." + "$zeroMS$ms." + "$zeroNS$ns" + "ns"
    }
    else if (ms > 0L) {
        "$ms." + "$zeroNS$ns" + "ns"
    }
    else {
        "$ns" + "ns"
    }

    return ans
}
fun timeMillisFormat(time: Long) : String {

    val ms = time % 1000
    val s = time / 1000

    var ans = ""

    ans += if (s > 0L) {
        val zero = "0".repeat(3 - ms.toString().length)
        "$s.$zero$ms" + "s"
    }
    else {
        "$ms" + "ms"
    }

    return ans
}


fun runTest(nameFun: String,
            examples: Array<Pair<String, String>>,
            testedFunction: () -> Unit,
            numberTest: Int = -1,
            timeTestRepeat: Int = 1,
            printAnswer: Boolean = false
){
    println(" $nameFun _started")

    var maxTime = 0L
    var allTrue = true

    if (numberTest == -1) {

        repeat(examples.size) {

            writeExample(input = examples[it].first, correctAnswer = examples[it].second)

            val time = measureTimeMillis(testedFunction)
            maxTime = max(maxTime, time)

            val answer = readFile(OUTPUT_FILE)
            val correctAnswer = readFile(CORRECT_ANSWER_FILE)

            if (answer != correctAnswer) allTrue = false
            println("$it ${answer == correctAnswer} : " + timeMillisFormat(time))

        }
        println("----------\n" + "$allTrue : " + timeMillisFormat(maxTime) + "\n")

    }
    else {
        repeat(timeTestRepeat) {

            writeExample(input = examples[numberTest].first, correctAnswer = examples[numberTest].second)

            val time = measureTimeMillis(testedFunction)
            maxTime = max(maxTime, time)

            val answer = readFile(OUTPUT_FILE)
            val correctAnswer = readFile(CORRECT_ANSWER_FILE)

            println("x${it + 1} ${answer == correctAnswer} : " + timeMillisFormat(time))

        }
        if (timeTestRepeat > 1) {

            println("----------\n" + "maxTime : " + timeMillisFormat(maxTime))
        }

        println()
        if (printAnswer) println(readFile(OUTPUT_FILE).forEach { println(it) })
    }
}

fun runOneTest(nameFun: String,
               testedFunction: () -> Unit,
               nameFileInput: String,
               nameFileCorrectAnswer: String = "",
               printAnswer: Boolean = false,
               timeTestRepeat: Int = 1
){
    println(" $nameFun _started")

    var maxTime = 0L
    //var allTrue = true

    repeat(timeTestRepeat) {
        val input = readFile("files/$nameFileInput").joinToString("\n")

        val correctAnswerInput = if (nameFileCorrectAnswer == "") ""
        else readFile("files/$nameFileCorrectAnswer").joinToString("\n")

        writeExample(input = input, correctAnswer = correctAnswerInput)

        val time = measureTimeMillis(testedFunction)
        maxTime = max(maxTime, time)

        val answer = readFile(OUTPUT_FILE)
        val correctAnswer = readFile(CORRECT_ANSWER_FILE)

        println("x${it + 1} ${answer == correctAnswer} : " + timeMillisFormat(time))

    }
    if (timeTestRepeat > 1) {

        println("----------\n" + "maxTime : " + timeMillisFormat(maxTime))
    }

    println()
    if (printAnswer) println(readFile(OUTPUT_FILE).forEach { println(it) })

    /*if (numberTest == -1) {

        repeat(examples.size) {

            writeExample(input = examples[it].first, correctAnswer = examples[it].second)

            val time = measureTimeMillis(testedFunction)
            maxTime = max(maxTime, time)

            val answer = readFile(OUTPUT_FILE)
            val correctAnswer = readFile(CORRECT_ANSWER_FILE)

            if (answer != correctAnswer) allTrue = false
            println("$it ${answer == correctAnswer} : " + nanoTimeFormat(time))

        }
        println("----------\n" + "$allTrue : " + nanoTimeFormat(maxTime) + "\n")

    }
    else {

    }*/
}
private fun writeExample(input: String, correctAnswer: String){
    writeFile(input, INPUT_FILE)
    writeFile(correctAnswer, CORRECT_ANSWER_FILE)
    writeFile("DEFAULT", OUTPUT_FILE)
}

fun powInt(num: Int, degree: Int) : Int {
    if (degree == 0) return 1

    var ans = 1
    repeat(degree) { ans *= num }

    return ans
}
fun powDouble(num: Double, degree: Int) : Double {
    if (degree == 0) return 1.0

    var ans = 1.0
    repeat(degree) { ans *= num }

    return ans
}
fun powLong(num: Long, degree: Int) : Long {
    if (degree == 0) return 1

    var answer = 1L
    repeat(degree){
        answer *= num
    }
    return answer
}

//blank solution
/*
import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    catsInArt()
    //runTest("catsInArt", examplesX, ::catsInArt)
}

private val examplesX = arrayOf(
    Pair("12", "2"),
    Pair("12", "2"),
    //Pair("12", "2"),
)

//0:00 - 0:00 (0ч0мин) > notOK частичное решение (000ms, 00.00Mb, не прошел тест 00)
//0:00 - 0:00 (0мин) поправил > OK (000ms, 00.00Mb, 0 баллов)
private fun catsInArt(){
    val input = BufferedReader(FileReader("input.txt"))
    val (n, l) = input.readLine().trim().split(" ").map { it.toInt() }
    var answer = ""


    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}
*/

//console input
/*
import java.util.*
val scan = Scanner(System.`in`)
val k = scan.nextInt()*/
