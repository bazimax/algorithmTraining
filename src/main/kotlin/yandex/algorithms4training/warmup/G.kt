package yandex.algorithms4training.warmup

/**
 * G. Кролик учит геометрию //Bunny is learning geometry
 *
 * Кролики очень любопытны. Они любят изучать геометрию, бегая по грядкам. Наш кролик как раз такой. Сегодня он решил
 * изучить новую фигуру — квадрат.
 * Кролик бегает по грядке — клеточному полю N × M клеток. В некоторых из них посеяны морковки, в некоторых нет.
 * Помогите кролику найти сторону квадрата наибольшей площади, заполненного морковками полностью.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    //bunnyIsLearningGeometryV3()
}

//20:20 - 21:10 (50мин) > notOK (4.054s, 40.49Mb, не прошел тест 11) //медленное решение TL
fun bunnyIsLearningGeometry(){
    val input = BufferedReader(FileReader("input.txt"))
    val (n, m) = input.readLine().split(" ").map { it.toInt() } // (1 <= n, m <= 1000)
    //println("n: $n, m:$m")

    val arrNM = Array(n) { IntArray(m) }

    repeat(n) { item ->
        arrNM[item] = input.readLine().split(" ").map { it.toInt() }.toIntArray()
        //println(arrNM[item].joinToString(" "))
    }

    var answer = 0

    arrNM.forEachIndexed { i, arrN ->
        arrN.forEachIndexed { j, ints ->
            //если в этой клетке есть морковь и возможный квадрат с морковью больше уже найденного
            //начинаем смотреть соседние клетки и увеличивать квадрат

            //println("- i: $i, j: $j, arrNM: ${arrNM[i][j]}")
            if (arrNM[i][j] == 1 &&
                n - i > answer &&
                m - j > answer ) {

                answer = searchSquare(i, j, arrNM, answer)
                //println("- answer: $answer")
            }
        }
    }

    //println(answer)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer.toString())
    output.flush()
}
private fun searchSquare(n: Int, m: Int, map: Array<IntArray>, currentCarrotSquare: Int): Int{
    var carrotSquare = 0

    var checkCarrot = true

    while (checkCarrot) {
        carrotSquare++

        //проверка на выход за границы массива
        if (n + carrotSquare <= map.size - 1 && m + carrotSquare <= map[0].size - 1) {

            //проверяем соседние клетки по вертикали
            repeat(carrotSquare) {
                if (map[n + it][m + carrotSquare] == 0) checkCarrot = false
            }

            //проверяем клетки по горизонтали + 1 (угловую клетку)
            repeat(carrotSquare + 1) {
                if (map[n + carrotSquare][m + it] == 0) checkCarrot = false
            }
        }
        else checkCarrot = false
    }

    return max(currentCarrotSquare, carrotSquare)
}
//21:10 - 23:15 (2ч5мин) //хоть и проходит до 13 теста - есть грубая ошибка (examplesG[4])
fun bunnyIsLearningGeometryV2(){
    //построчная проверка
    val input = BufferedReader(FileReader("input.txt"))
    val (n, m) = input.readLine().split(" ").map { it.toInt() } // (1 <= n, m <= 1000)

    val prevArr = Array(m){ IntArray(2) }

    var maxCarrotSquareSize = 0

    repeat(n) { _ ->
        val nextArr = input.readLine().split(" ").map { it.toInt() }.toIntArray()

        nextArr.forEachIndexed { index, i ->
            if (i == 0) {
                prevArr[index] = intArrayOf(0, 0)
                //print("${prevArr[index][0]}/${prevArr[index][1]}, ")
            }
            else {
                if (index - 1 >= 0) prevArr[index] = intArrayOf(prevArr[index - 1][0] + 1, prevArr[index][1] + 1)
                else prevArr[index] = intArrayOf(1, prevArr[index][1] + 1)

                //print("${prevArr[index][0]}/${prevArr[index][1]}, ")

                val carrotSquareSize = min(prevArr[index][0], prevArr[index][1])
                maxCarrotSquareSize = max(maxCarrotSquareSize, carrotSquareSize)

            }
        }
        //println()

    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(maxCarrotSquareSize.toString())
    output.flush()
}

//23:15 - 0:07 (52мин) не прошел 14 тест
fun bunnyIsLearningGeometryV3(){
    val input = BufferedReader(FileReader("input.txt"))
    val (n, m) = input.readLine().split(" ").map { it.toInt() } // (1 <= n, m <= 1000)

    val prevArr = IntArray(m)

    var maxCarrotSquareSize = 0

    repeat(n) { _ ->
        val nextArr = input.readLine().split(" ").map { it.toInt() }.toIntArray()

        nextArr.forEachIndexed { index, i ->
            //записываем новой значение учитывая предыдущее
            //(получается максимальное значение единиц идущих подряд по вертикали)
            prevArr[index] = if (i == 0) 0 else prevArr[index] + 1
        }

        prevArr.forEachIndexed { index, i ->
            //если значение вертикали больше уже найденного квадрата, проверяем все ближайшие вертикали
            if (i > maxCarrotSquareSize) {
                var tempCarrotSquareSize = 1
                var l = index + 1
                while (l <= m - 1 && prevArr[l] >= i && tempCarrotSquareSize < i) {
                    tempCarrotSquareSize++
                    l++
                }
                maxCarrotSquareSize = max(maxCarrotSquareSize, tempCarrotSquareSize)
            }
        }

    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(maxCarrotSquareSize.toString())
    output.flush()
}

//0:10 - 0:50 (40мин) - не прошел 4 тест
fun bunnyIsLearningGeometryV3a(){
    val input = BufferedReader(FileReader("input.txt"))
    val (n, m) = input.readLine().split(" ").map { it.toInt() } // (1 <= n, m <= 1000)

    val prevArr = IntArray(m)

    var maxCarrotSquareSize = 0

    repeat(n) { _ ->
        val nextArr = input.readLine().split(" ").map { it.toInt() }.toIntArray()

        nextArr.forEachIndexed { index, i ->
            //записываем новой значение учитывая предыдущее
            //(получается максимальное значение единиц идущих подряд по вертикали)
            prevArr[index] = if (i == 0) 0 else prevArr[index] + 1
        }

        prevArr.forEachIndexed { index, i ->
            //если значение вертикали больше уже найденного квадрата, проверяем все ближайшие вертикали
            if (i > maxCarrotSquareSize) {
                var tempCarrotSquareSize = 1

                var maxPossibleCarrotSquare = i
                var l = index + 1

                var check = true
                while (check && l < m) {
                    if (prevArr[l] >= i) {
                        tempCarrotSquareSize++
                    }
                    else if (prevArr[l] in (tempCarrotSquareSize + 1) until i) {
                        maxPossibleCarrotSquare = prevArr[l]
                        tempCarrotSquareSize++
                    }
                    else {
                        check = false
                    }
                    l++

                    if (maxPossibleCarrotSquare == tempCarrotSquareSize) check = false
                }

                maxCarrotSquareSize = max(maxCarrotSquareSize, tempCarrotSquareSize)
            }
        }

    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(maxCarrotSquareSize.toString())
    output.flush()
}

//3:40 - 4:05 (25мин) > OK (0.877s, 38.66Mb)
fun bunnyIsLearningGeometryV4(){
    val input = BufferedReader(FileReader("input.txt"))
    val (n, m) = input.readLine().split(" ").map { it.toInt() } // (1 <= n, m <= 1000)

    var prevArr = input.readLine().split(" ").map { it.toInt() }.toIntArray()
    var maxCarrotSquareSize = prevArr.maxOf { it }

    repeat(n - 1) { _ ->
        val nextArr = input.readLine().split(" ").map { it.toInt() }.toIntArray()

        for (i in 1 until m) {
            if (nextArr[i] != 0) {
                nextArr[i] = intArrayOf(nextArr[i - 1], prevArr[i], prevArr[i - 1]).minOf { it } + 1
                maxCarrotSquareSize = max(maxCarrotSquareSize, nextArr[i])
            }
        }
        prevArr = nextArr
        //println("${prevArr.joinToString(" ")} : $maxCarrotSquareSize")
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(maxCarrotSquareSize.toString())
    output.flush()
}

fun bunnyIsLearningGeometryV2backup(){
    //принтер - построчная проверка
    val input = BufferedReader(FileReader("input.txt"))
    val (n, m) = input.readLine().split(" ").map { it.toInt() } // (1 <= n, m <= 1000)
    //println("n: $n, m:$m")

    val prevArr = IntArray(m) {0}

    var maxCarrotSquareSize = 0 //carrotSquareSize

    repeat(n) { _ ->
        val nextArr = input.readLine().split(" ").map { it.toInt() }.toIntArray()

        nextArr.forEachIndexed { index, i ->
            if (i == 0) prevArr[index] = 0
            else {
                prevArr[index] += 1



                var carrotSquareSize = 1
                var previewIndex = index - 1
                var count = prevArr[index] - 1 //Возможный размер квадрата который хотим проверить. 1 не проверяем
                println("- else prev: ${prevArr[index]}, next: $i, cSS: $carrotSquareSize, prevIndex: $previewIndex, count: $count ")

                //var currentM = currentArr[index]
                /*while (
                    previewIndex >= 0 &&
                    prevArr[previewIndex] >= prevArr[index] - 1 &&
                    count != 0)
                {
                    print("- while ")
                    if (previewIndex > 0) {
                        carrotSquareSize++
                        print("- if $carrotSquareSize ")
                    }
                    previewIndex--
                    count--
                }*/

                maxCarrotSquareSize = max(maxCarrotSquareSize, carrotSquareSize)
            }
        }
        println("prev: ${prevArr.joinToString(" ")} next: ${nextArr.joinToString(" ")} max: $maxCarrotSquareSize")
        //println(arrNM[item].joinToString(" "))
    }


    //var carrotSquare = 0

    //val perspectiveSquares = IntArray(m)



    println(maxCarrotSquareSize)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(maxCarrotSquareSize.toString())
    output.flush()
}
