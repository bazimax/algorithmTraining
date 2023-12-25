package yandex.algorithms4training.warmup

/**
 * E. Средний уровень //Medium level
 *
 * В группе учатся n студентов, каждый из которых имеет свой рейтинг a(i). Им нужно выбрать старосту; для этого
 * студенты хотят выбрать старосту таким образом, чтобы суммарный уровень недовольства группы был минимальный.
 * Если выбрать j-го старостой, то уровень недовольства i-го студента равен ∣a(i) − a(j)|.
 * Например, если в группе есть три студента с рейтингами 1, 3 и 4 и в качестве старосты выбирают второго,
 * то уровень недовольства группы будет равен |1 − 3| + |3 − 3| + |4 − 3| = 3 . Вычислите уровень недовольства группы
 * при выборе каждого из студентов старостой.
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*
import kotlin.math.abs
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    // mediumLevelV5()
    // timeMillisRun(::mediumLevelV5, 3)
    /*val arr = arrayOf("12", "1", "0")
    println(arr[0].toInt() + arr[1].toInt())*/
    /*runTest("mediumLevel",
        arrayOf(readFile(INPUT_FILE).toString() to readFile(CORRECT_ANSWER_FILE).toString()),
        ::mediumLevelV3, 0
    )*/
}

// OK (0.547s, 40.67Mb) //!!?? задача решена (в контесте ОК), но тесты он не проходит - надо разобраться c Input.txt
fun mediumLevelV5(){

    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().toInt()
    val text = input.readText()
    val arr = text.substring(0, text.length - 1).split(" ")//.map { it.toInt() }.toIntArray()

    //Первый проход. Справа налево. Определяем сумму всех недовольных справа от нынешнего старосты
    var rightSum = 0
    val arrR = IntArray(arr.size)

    for (i in arr.lastIndex downTo 0) {

        val headman = arr[i].toInt()
        arrR[i] = rightSum - ( (arr.lastIndex - i) * headman ) //rightSum - minArea

        rightSum += headman
    }

    //Второй проход. Слева направо. Определяем сумму всех недовольных слева от нынешнего старосты
    //И тут же записываем значение сумму найденного значения и записанным с первого прохода
    //var answer = ""
    var leftSum = 0

    val ansArr = IntArray(arr.size)

    arr.forEachIndexed { index, i ->

        val headman = i.toInt()

        val leftArea = (headman * index) - leftSum //maxArea - leftSum
        ansArr[index] = leftArea + arrR[index]
        //answer = "$answer${leftArea + arrR[index]} "

        leftSum += headman
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ansArr.joinToString(" "))
    output.flush()
}

// 18:07 - 18:25 (18мин) > notOK частичное решение (1.085s, 34.45Mb, не прошел тест 7 TL)
fun mediumLevel(){
    //медленное решение O(n^2)
    val input = BufferedReader(FileReader("input.txt"))
    input.readLine().split(" ").map { it.toInt() }
    val arrA = input.readLine().split(" ").map { it.toInt() }.toIntArray()

    var answer = ""

    arrA.forEach { headman ->
        var levelOfGroupDissatisfaction = 0
        arrA.forEach { student ->
            levelOfGroupDissatisfaction += abs(student - headman)
        }
        answer = "$answer$levelOfGroupDissatisfaction "
    }

    //answer = answer.trim() //нет необходимости

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

// - 19:50 > notOK частичное решение (1.075s, 34.65Mb, не прошел тест 7 TL)
fun mediumLevelV2(){
    val input = BufferedReader(FileReader("input.txt"))
    input.readLine()
    val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray()

    var levelOfGroupDissatisfaction = 0

    //недовольство для первого старосты
    arr.forEach { student ->
        levelOfGroupDissatisfaction += abs(student - arr[0])
    }

    var answer = ""

    val size = arr.size - 1
    var prevHeadman = arr[0]
    arr.forEachIndexed { index, headman ->

        val margin = headman - prevHeadman //разница по высоте между нынешним и предыдущим старостой
        val l = (index - 1) * margin //прибавляем
        val r = (size - index) * margin //убавляем
        levelOfGroupDissatisfaction = levelOfGroupDissatisfaction + l - r

        answer = "$answer$levelOfGroupDissatisfaction "
        prevHeadman = headman
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

// 19:55 - 20:21 > частичное решение (1.064s, 36.64Mb, не прошел тест 7 TL)
fun mediumLevelV3(){
    //через площади

    val input = BufferedReader(FileReader("input.txt"))
    input.readLine()
    val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray()

    //Первый проход. Справа налево. Определяем сумму всех недовольных справа от нынешнего старосты
    var rightSum = 0
    val arrR = IntArray(arr.size)

    for (i in arr.lastIndex downTo 0) {

        val headman = arr[i]
        arrR[i] = rightSum - ( (arr.lastIndex - i) * headman ) //rightSum - minArea

        rightSum += headman
    }

    //Второй проход. Слева направо. Определяем сумму всех недовольных слева от нынешнего старосты
    //И тут же записываем значение сумму найденного значения и записанным с первого прохода
    var answer = ""
    var leftSum = 0

    arr.forEachIndexed { index, headman ->

        val leftArea = (headman * index) - leftSum //maxArea - leftSum
        answer = "$answer${leftArea + arrR[index]} "

        leftSum += headman
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

// 5:05 - 6:16 (от v3a до v5) //answer = "$answer${leftArea + arrR[index]} " - очень медленная строка
fun mediumLevelV3a(){
    //через площади

    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().toInt()
    val text = input.readText()
    val arr = text.substring(0, text.length - 1).split(" ")//.map { it.toInt() }.toIntArray()

    //Первый проход. Справа налево. Определяем сумму всех недовольных справа от нынешнего старосты
    var rightSum = 0
    val arrR = IntArray(arr.size)

    for (i in arr.lastIndex downTo 0) {

        val headman = arr[i].toInt()
        arrR[i] = rightSum - ( (arr.lastIndex - i) * headman ) //rightSum - minArea

        rightSum += headman
    }

    //Второй проход. Слева направо. Определяем сумму всех недовольных слева от нынешнего старосты
    //И тут же записываем значение сумму найденного значения и записанным с первого прохода
    var answer = ""
    var leftSum = 0

    arr.forEachIndexed { index, i ->

        val headman = i.toInt()

        val leftArea = (headman * index) - leftSum //maxArea - leftSum
        answer = "$answer${leftArea + arrR[index]} "

        leftSum += headman
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}
fun mediumLevelV4(){
    //console input //import java.util.*

    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    var next = n

    val arr = IntArray(n)

    //Первый проход. Слева направо. Определяем сумму всех недовольных слева от нынешнего старосты
    val arrL = IntArray(arr.size)
    var leftSum = 0

    var count = 0

    while (next-- > 0) {
        val headman = scan.nextInt()
        arrL[count] = (headman * count) - leftSum
        leftSum += headman

        arr[count] = headman
        count++
    }
    //scan.close()

    //Второй проход. Справа налево. Определяем сумму всех недовольных справа от нынешнего старосты
    var rightSum = 0
    val arrR = IntArray(arr.size)

    for (i in arr.lastIndex downTo 0) {

        val headman = arr[i]
        arrR[i] = rightSum - ( (arr.lastIndex - i) * headman ) //rightSum - minArea

        rightSum += headman
    }

    var ans = ""

    arrL.forEachIndexed { index, i ->
        ans = "$ans${i + arrR[index]} "
    }
}

