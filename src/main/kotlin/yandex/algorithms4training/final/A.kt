package yandex.algorithms4training.final

/**
 * A. Объединение последовательностей //Sequence merging
 *
 * Даны две бесконечных возрастающих последовательности чисел A и B. i-ый член последовательности A равен i^2.
 * I-ый член последовательности B равен i^3.
 *
 * Требуется найти C(x), где C – возрастающая последовательность, полученная при объединении последовательностей A и B.
 * Если существует некоторое число, которое встречается и в последовательности A и в последовательности B, то в
 * последовательность C это число попадает в единственном экземпляре.
 */

import yandex.runTest
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.PriorityQueue

fun main(args: Array<String>) {
    //sequenceMerging()
    runTest("sequenceMerging", examplesA, ::sequenceMerging, )
}

private val examplesA = arrayOf(
    Pair("1", "1"), //1
    Pair("2", "4"), //2
    Pair("4", "9"), //3
    Pair("10", "64"),
    Pair("5", "16"),
    Pair("8", "36"),
    Pair("9", "49"),
    //Pair("12", "2"),
)


private fun sequenceMerging(){
    val input = BufferedReader(FileReader("input.txt"))

    //1 ≤ x ≤ 10^7 // 10^7^2 для А последовательности и 10^7^3 для B последовательности (нужен ULong)
    val x = input.readLine().trim().split(" ").map { it.toInt() }[0]


    //val answer = merger(x)
    val answer = mergerV2(x)


    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer.toString())
    output.flush()
}

private fun powULong(num: Int, degree: Int) : ULong {
    if (degree == 0) return 1u

    var ans: ULong = 1u
    repeat(degree) { ans *= num.toULong() }

    return ans
}


// OK (255ms, 15.37Mb)
private fun mergerV2(x: Int) : ULong {

    var sequenceAValue: ULong = 1u
    var degreeA: Int = 1
    var sequenceBValue: ULong = 1u
    var degreeB: Int = 1

    var lastElementInNewSequence: ULong = 0u

    repeat(x) {
        when  {
            sequenceAValue < sequenceBValue -> {
                lastElementInNewSequence = sequenceAValue
                sequenceAValue = powULong(++degreeA, 2)
            }

            sequenceAValue > sequenceBValue -> {
                lastElementInNewSequence = sequenceBValue
                sequenceBValue = powULong(++degreeB, 3)
            }

            sequenceAValue == sequenceBValue -> {
                lastElementInNewSequence = sequenceAValue
                sequenceAValue = powULong(++degreeA, 2)
                sequenceBValue = powULong(++degreeB, 3)
            }
        }
    }

    return lastElementInNewSequence
}

// 15:00 - 16:27 (1ч27мин) > OK (0.62s, 34.22Mb)
private fun merger(x: Int): ULong {

    val sequenceA = PriorityQueue<ULong>{a,b -> (a - b).toInt()}
    val sequenceB = PriorityQueue<ULong>{a,b -> (a - b).toInt()}

    var lastElementInNewSequence: ULong = 1u


    var count = 1

    var sequenceAi = 1
    var sequenceBi = 1
    var sequenceAPrev: ULong = 1u


    //пока не дошли до C(x) объединяем последовательности
    while (count < x) {
        //Если одна из последовательностей пустая, то заполняем её.
        //При этом учитываем, что последовательности растут неравномерно. Поэтому у каждой свои степени,
        //плюс не храним все последовательности, а только их последние элементы

        if (sequenceA.isEmpty()) {
            sequenceAi++
            sequenceA.add(powULong(sequenceAi, 2))
        }

        if (sequenceB.isEmpty()) {
            sequenceBi++
            sequenceB.add(powULong(sequenceBi, 3))
        }

        //Сравниваем последовательности. Если из какой-то убираем, то физически удаляем этот элемент из этой
        //последовательности, но сохраняем его в sequence"X"Prev. Плюс увеличиваем count
        if (sequenceA.isNotEmpty() &&
            sequenceB.isNotEmpty() &&
            sequenceA.peek() < sequenceB.peek() &&
            sequenceA.peek() > lastElementInNewSequence
        ) {
            sequenceAPrev = sequenceA.poll()
            lastElementInNewSequence = sequenceAPrev
            count++
        }
        if (sequenceA.isNotEmpty() &&
            sequenceB.isNotEmpty() &&
            sequenceA.peek() > sequenceB.peek() &&
            sequenceB.peek() > lastElementInNewSequence
        ) {
            lastElementInNewSequence = sequenceB.poll()
            count++
        }
        if (sequenceA.isNotEmpty() &&
            sequenceB.isNotEmpty() &&
            sequenceA.peek() == sequenceB.peek()) {
            sequenceAPrev = sequenceA.poll()
            lastElementInNewSequence = sequenceAPrev
            count++
        }
        if (sequenceA.isNotEmpty() && sequenceA.peek() == lastElementInNewSequence) sequenceA.poll()
        if (sequenceB.isNotEmpty() && sequenceB.peek() == lastElementInNewSequence) sequenceB.poll()

    }
    return lastElementInNewSequence
}