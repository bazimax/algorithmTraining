package yandex.algorithm2022upsolving

import yandex.writeFile
import java.io.BufferedReader
import java.io.FileReader

/**
 * B. Необычное деление
 *
 */

//21:40 - 1:03 (3ч20мин) > notOK частичное решение (2.066s, 35.38Mb, не прошел тест 4)
fun main(args: Array<String>) {
    unusualDivision()
}

fun unusualDivision(){
    val megaArrAnswers = megaArrOfCorrectAnswers()
    val arrCorrectDividers = arrOfPossibleCorrectAnswer()

    val input = BufferedReader(FileReader("input.txt"))
    val t = input.readLine().split(" ").map { it.toInt() }[0]

    var answer = ""

    repeat(t) {
        val (n, b) = input.readLine().split(" ").map { it.toLong() }
        val divider = b.toInt()
        val correctDivider = arrCorrectDividers[divider]

        answer = if (divider != 1) {
            val tempAnswer = calculate(n, divider, megaArrAnswers[correctDivider])
            if (answer == "") "$tempAnswer" else "$answer\n$tempAnswer"
        } else {
            if (answer == "") "$n" else "$answer\n$n"
        }
    }

    //println(answer)
    writeFile(answer, "output.txt")
}
fun unusualDivisionV2(){
    //быстрее не стало, только памяти больше занимает
    val megaArrAnswers = megaArrOfCorrectAnswers()
    val arrCorrectDividers = arrOfPossibleCorrectAnswer()

    val input = BufferedReader(FileReader("input.txt"))
    val t = input.readLine().split(" ").map { it.toInt() }[0]

    val arrInput: Array<LongArray> = Array(t){ longArrayOf(0,0) }

    repeat(t) { item ->
        val (n, b) = input.readLine().split(" ").map { it.toLong() }
        arrInput[item] = longArrayOf(n, b)
    }

    var answer = ""

    arrInput.forEach {
        val n = it[0]
        val divider = it[1].toInt()
        val correctDivider = arrCorrectDividers[divider]

        answer = if (divider != 1) {
            val tempAnswer = calculate(n, divider, megaArrAnswers[correctDivider])
            if (answer == "") "$tempAnswer" else "$answer\n$tempAnswer"
        } else {
            if (answer == "") "$n" else "$answer\n$n"
        }
    }

    //println(answer)
    writeFile(answer, "output.txt")
}

private fun calculate(num: Long, divider: Int, megaArrAnswers: LongArray): Long{

    val numToList = num.toString().map { it.digitToInt()}//.toIntArray()

    var numLength = num.toString().length

    var sum = 0L

    repeat (numLength) {
        //Считаем количество возможных вариантов начиная с максимального. Пример 999 > 99 > 9
        val number = numToList[it]
        val factor = number / divider

        //var calcFactor = 0L
        if (numLength > 1 && number != 0) {
            val calcFactor = megaArrAnswers[numLength - 2]

            sum += if (factor == 0) calcFactor else (factor * calcFactor) + 1

        }
        else if (numLength == 1){
            sum += factor
        }

        //println("number: $number, numLength: $numLength, factor: $factor, calcFactor: $calcFactor, sum: $sum")
        numLength--
    }

    return sum
}

private fun arrOfPossibleCorrectAnswer(): IntArray {
    //набор максимальных возможных правильных ответов для каждого делителя от 0 до 9
    //set of maximum possible correct answers for each divisor 0-9
    //0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    return intArrayOf(-1, 9, 4, 3, 2, 1, 1, 1, 1, 1)
}
private fun megaArrOfCorrectAnswers(): Array<LongArray>{
    //набор ответов для каждого посчитанного делителя (0-0; для 5,6,7,8,9 - 1; 4 - 2; 3 - 3; 2 - 4) от 1 до 18 степени
    //вычисление:
    //val map = calcSumCorrectAnswers(3, 18)
    //map.forEach { print("${it.value}, ") }
    return arrayOf(
        longArrayOf(0),
        longArrayOf(1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143),
        longArrayOf(2, 8, 26, 80, 242, 728, 2186, 6560, 19682, 59048, 177146, 531440, 1594322, 4782968, 14348906,
            43046720, 129140162, 387420488),
        longArrayOf(3, 15, 63, 255, 1023, 4095, 16383, 65535, 262143, 1048575, 4194303, 16777215, 67108863, 268435455,
            1073741823, 4294967295, 17179869183, 68719476735),
        longArrayOf(4, 24, 124, 624, 3124, 15624, 78124, 390624, 1953124, 9765624, 48828124, 244140624, 1220703124,
            6103515624, 30517578124, 152587890624, 762939453124, 3814697265624)
    )
}

/*
//Вычисления
private fun calcSumCorrectAnswers(num: Int, degree: Int): HashMap<Int, Long>{
    val map = HashMap<Int, Long>()
    var sum = 0L

    repeat(degree) {
        val tempSum = num * pow(1L + num, it)
        sum += tempSum

        map[it + 1] = sum
    }
    return map
}
private fun pow(num: Long, degree: Int) : Long {
    if (degree == 0) return 1

    var answer = 1L
    repeat(degree){
        answer *= num
    }
    return answer
}
private fun setOfPossibleCorrectAnswer(): HashMap<Int, Int>{
    //набор максимальных возможных правильных ответов для каждого делителя
    //set of maximum possible correct answers for each divisor
    val map = HashMap<Int, Int>()

    //Вычисление
    //for (i in 1..9) println("i: $i, ${9 / i}")

    //map[1] = 9
    map[2] = 4
    map[3] = 3
    map[4] = 2
    map[5] = 1
    map[6] = 1
    map[7] = 1
    map[8] = 1
    map[9] = 1
    return map
}
*/





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