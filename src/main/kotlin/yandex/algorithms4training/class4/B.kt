package yandex.algorithms4training.class4

/**
 * B. Затерянный мир //
 *
 * Территория зоопарка Юрского периода «Затерянный мир» представляет собой решётку N × N, в каждой клетке которой
 * находится вольер для динозавра. Директор зоопарка Степан Савельев планирует расселить в зоопарке N динозавров.
 * Вольеры отделены друг от друга невысоким забором. Сотрудникам зоопарка известно, что динозавр не покидает пределов
 * своего вольера, и не ломает забор, если он не видит на территории парка других динозавров.
 *
 * Зрительный аппарат у динозавров таков, что он видит всех динозавров, которые находятся на одной строке, на одном
 * столбце или на одной диагонали с ним. Если же динозавр видит другого ящера, то ломает забор и вступает в борьбу.
 *
 * Директор зоопарка не хочет терпеть убытки, поэтому просит вас посчитать количество способов так расселить динозавров
 * в зоопарке, чтобы никакой ящер не видел остальных динозавров.
 */

import yandex.runTest
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.Stack

fun main(args: Array<String>) {
    lostWorld()
    //runTest("lostWorld", examplesB, ::lostWorld)
}

private val examplesB = arrayOf(
    Pair("0", "0"),
    Pair("1", "1"),
    Pair("2", "0"),
    Pair("3", "0"),
    Pair("4", "2"),
    Pair("8", "92"), //1
    //Pair("12", "2"),
)

// 14:50 - (перерыв ~30мин) - 18:30 (3ч10мин) > OK (472ms, 39.17Mb)
private fun lostWorld(){
    //задача про ферзей
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] //N ≤ 10 //maximum dinosaurs


    val ans = if (n <= 1) "$n"
    else {
        val illegalRows = HashSet<Int>()
        val illegalColumns = HashSet<Int>()
        val illegalDiagonalsA = HashSet<Int>()
        val illegalDiagonalsB = HashSet<Int>()

        moveColumn(-1 to -1, n, illegalRows, illegalColumns, illegalDiagonalsA, illegalDiagonalsB).toString()
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}


private fun moveColumn(dinosaur: Pair<Int, Int>,
                       sizeGrid: Int,
                       illegalRows: HashSet<Int>,
                       illegalColumns: HashSet<Int>,
                       illegalDiagonalsA: HashSet<Int>,
                       illegalDiagonalsB: HashSet<Int>
) : Int {

    var counterOfAllDinosaurPlacementOptions = 0

    //если выставляем последнего динозавра, то ""обнуляем" счетчик (возвращаем до максимума)
    //иначе добавляем в стек новые клетки, проверяя можем ли их поставить и добавляем новые заблокированные
    //строки, столбцы и диагонали
    if (sizeGrid - 1 == dinosaur.second) {
        counterOfAllDinosaurPlacementOptions++
    }
    else {
        val stackNext = Stack<Pair<Int, Int>>()
        repeat(sizeGrid) {stackNext.add(it to dinosaur.second + 1)} //Row to Column




        while (stackNext.isNotEmpty()) {
            val dinosaurNext = stackNext.pop()

            if (checkingDinosaurPlacement(dinosaurNext, illegalRows, illegalColumns, illegalDiagonalsA, illegalDiagonalsB)
            ) {
                //добавляем ограничения
                illegalRows.add(dinosaurNext.first)
                illegalColumns.add(dinosaurNext.second)
                illegalDiagonalsA.add(dinosaurNext.first + dinosaurNext.second)
                illegalDiagonalsB.add(dinosaurNext.first - dinosaurNext.second)


                counterOfAllDinosaurPlacementOptions +=
                    moveColumn(dinosaurNext, sizeGrid, illegalRows, illegalColumns, illegalDiagonalsA, illegalDiagonalsB)

                //в конце ограничения удаляем
                illegalRows.remove(dinosaurNext.first)
                illegalColumns.remove(dinosaurNext.second)
                illegalDiagonalsA.remove(dinosaurNext.first + dinosaurNext.second)
                illegalDiagonalsB.remove(dinosaurNext.first - dinosaurNext.second)
            }
        }
    }

    return counterOfAllDinosaurPlacementOptions
}

private fun checkingDinosaurPlacement(dinosaur: Pair<Int, Int>,
                                      illegalRows: HashSet<Int>,
                                      illegalColumns: HashSet<Int>,
                                      illegalDiagonalsA: HashSet<Int>,
                                      illegalDiagonalsB: HashSet<Int>
) : Boolean {
    var ans = true

    if (illegalRows.contains(dinosaur.first) ||
        illegalColumns.contains(dinosaur.second) ||
        illegalDiagonalsA.contains(dinosaur.first + dinosaur.second) ||
        illegalDiagonalsB.contains(dinosaur.first - dinosaur.second)
    ) {
        ans = false
    }
    return ans
}
