package leetcode

import java.util.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFun(testAsk)
}

//1970. Last Day Where You Can Still Cross - Hard - https://leetcode.com/problems/last-day-where-you-can-still-cross/

private val testAsk = arrayOf(
    Triple(Pair(2,2), "[[1,1],[2,1],[1,2],[2,2]]", 2),
    Triple(Pair(2,2), "[[1,1],[1,2],[2,1],[2,2]]", 1),
    Triple(Pair(3,3), "[[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]", 3),
    Triple(Pair(6,2), "[[4,2],[6,2],[2,1],[4,1],[6,1],[3,1],[2,2],[3,2],[1,1],[5,1],[5,2],[1,2]]", 3),
    Triple(Pair(4,3), "[[3,2],[2,1],[1,3],[1,2],[3,3],[2,2],[4,3],[1,1],[2,3],[4,1],[3,1],[4,2]]", 3),
    Triple(Pair(2,6), "[[1,4],[1,3],[2,1],[2,5],[2,2],[1,5],[2,4],[1,2],[1,6],[2,3],[2,6],[1,1]]", 8),
    Triple(Pair(2,6), "[[1,6],[2,2],[1,5],[1,2],[2,5],[2,3],[2,6],[2,1],[1,3],[1,4],[1,1],[2,4]]", 9),
    //Pair(intArrayOf(0), listOf(listOf(0))),
    //Triple(intArrayOf(), ,)
)
private fun testFun(ask: Array<Triple<Pair<Int, Int>, String, Int>>) {

    for (i in ask) {
        //println(i.second)
        val str = i.second.replace("],[", ".").drop(2).dropLast(2).split(".")
        val arr: Array<IntArray> = Array(str.size){ IntArray(2){0} }

        str.forEachIndexed() { index, it ->
            val a = it.split(",")
            arr[index] = intArrayOf(a[0].toInt(),a[1].toInt())
        }

        val time = measureTimeMillis {
            val answer = latestDayToCross1970v2(i.first.first, i.first.second, arr)
            println("${answer == i.third} :: in: [${i.first.first}-${i.first.second}], " +
                    "correctAnswer: ${i.third}, ans: $answer") //contentEquals - Array
            //println("${answer == i.second} :: in: [${i.first.joinToString(",")}], target: ${i.second}, " +
            //        "correctAnswer: ${i.third}, ans: $answer") //contentEquals - Array
        }
        println("time: $time")
    }
}

//1970v2 - 22:45 - 1:00 - notOK - все равно не все тесты проходит (остановился на 84)
private fun latestDayToCross1970v2(row: Int, col: Int, cells: Array<IntArray>): Int {
    //рисуем карту с отступом по краям,
    //значение каждой клетки на карте равно количество не нулевых клеток по соседству (максимум = 4)
    //по краям 0 - препятствия\непроходимые клетки или вода
    val map = Array(row + 2){IntArray(col + 2){4}}

    map[1] = IntArray(col + 2){3}
    map[map.size - 2] = IntArray(col + 2){3}


    map.forEachIndexed() { index, _ ->
        map[index][0] = 0
        map[index][1] = 3
        map[index][col + 1] = 0
        map[index][col] = 3
    }

    map[map.size - 1] = IntArray(col + 2){0}
    map[0] = IntArray(col + 2){0}

    map[1][1] = 2
    map[1][col] = 2
    map[row][1] = 2
    map[row][col] = 2

    /*println("map")
    map.forEach {
        println(it.joinToString(","))
    }*/

    //счетчик оставшихся клеток в линии, если в линии не осталось клеток то пути точно нет
    val mapCount = IntArray(row) {0}
    mapCount.forEachIndexed() { index, _ ->
        mapCount[index] = map[index + 1].sum()
    }

    //счетчик дней
    var dayCount = 0

    foo@while (true) {
        //println()
        //println("start - mapCount: ${mapCount.joinToString(",")}, dayCount: $dayCount")
        //получаем клетку с водой
        val cell = cells[dayCount]

        //val cellsChanged: Deque<IntArray> = LinkedList()
        //cellsChanged.add(cell)
        //проверяем изменения от добавления клетки с водой или непроходимой клетки

        if (cellWater(cell, mapCount, map)) break@foo

        //все норм
        dayCount++
        /*println("End - map")
        map.forEach {
            println(it.joinToString(","))
        }
        println("End - mapCount: ${mapCount.joinToString(",")}, dayCount: $dayCount")*/
    }
    return dayCount
}

private fun cellWater(cell:  IntArray, mapCount: IntArray, map: Array<IntArray>): Boolean {
    //println("\n cellWater, cell: ${cell[0]}-${cell[1]}")
    //значение клетки
    val cellValue = map[cell[0]][cell[1]]

    //println("mapCount: ${mapCount.joinToString(",")}, cellValue: $cellValue")
    //меняем значение в клетке на 0 - непроходимая клетка
    map[cell[0]][cell[1]] = 0

    //вычитаем значение клетки из счетчика
    mapCount[cell[0] - 1] -= cellValue
    /*println("map")
    map.forEach {
        println(it.joinToString(","))
    }
    println("mapCount: ${mapCount.joinToString(",")}")*/

    //проверяем не закончились ли клетки в линии - если закончились пути нет
    if (mapCount[cell[0] - 1] <= 0) return true

    /*//меняем соседние клетки
    val adjacentCells = arrayOf(
        cell[0] to cell[1] - 1, //west
        cell[0] to cell[1] + 1, //east
        cell[0] - 1 to cell[1], //north
        cell[0] + 1 to cell[1]  //south
    )

    //список для проверки соседних клеток
    val adjacentCellsChanged: Deque<Pair<Int, Int>> = LinkedList()

    for (i in adjacentCells) {

        val adjacentCellValue = map[i.first][i.second]
        println("i: $i, value: $adjacentCellValue")

        if (adjacentCellValue > 1) {
            //уменьшаем значение этой соседней клетки на 1
            map[i.first][i.second]-- // -= 1

            //println("mapCount 00: ${mapCount[i.first - 1]}")
            mapCount[i.first - 1]-- // -= 1
            //println("mapCount: ${mapCount[i.first - 1]}")
            if (mapCount[i.first - 1] <= 0) return true

            //добавляем в список для проверки
            adjacentCellsChanged.add(i.first to i.second)
        }
    }*/

    val adjacentCell = adjacentCell(cell, mapCount, map)

    if (adjacentCell.second) {
        return true
    }
    else {
        //проверяем соседние клетки, остался ли после этого путь
        if (cellCheck(adjacentCell.first, mapCount, map)) return true
    }

    return false
}
//уменьшаем соседние клетки
private fun adjacentCell(cell: IntArray, mapCount: IntArray, map: Array<IntArray>): Pair<Deque<Pair<Int, Int>>, Boolean> {
    //println("\n adjacentCell, cell: ${cell[0]}-${cell[1]}")
    //println("adj - mapCount: ${mapCount.joinToString(",")}")
    //меняем соседние клетки
    val adjacentCells = arrayOf(
        cell[0] to cell[1] - 1, //west
        cell[0] to cell[1] + 1, //east
        cell[0] - 1 to cell[1], //north
        cell[0] + 1 to cell[1]  //south
    )

    //список для проверки соседних клеток
    val adjacentCellsChanged: Deque<Pair<Int, Int>> = LinkedList()

    for (i in adjacentCells) {

        val adjacentCellValue = map[i.first][i.second]
        //println("i: $i, value: $adjacentCellValue")
        //println("adj - mapCount: ${mapCount.joinToString(",")}")

        if (adjacentCellValue > 1) {
            //println ("if > 1, ${map[i.first][i.second]}, ${mapCount[i.first - 1]}")
            //уменьшаем значение этой соседней клетки на 1
            map[i.first][i.second]-- // -= 1

            //уменьшаем счетчик линии на 1
            //println("mapCount 00: ${mapCount[i.first - 1]}")
            mapCount[i.first - 1] = mapCount[i.first - 1] - 1 // -= 1
            /*println("adj - map")
            map.forEach {
                println(it.joinToString(","))
            }
            println("adj - mapCount: ${mapCount.joinToString(",")}")*/
            //проверяем не закончилась ли линия
            if (mapCount[i.first - 1] <= 0) return adjacentCellsChanged to true

            //добавляем в список для проверки
            adjacentCellsChanged.add(i.first to i.second)
        }
    }
    /*println("map")
    map.forEach {
        println(it.joinToString(","))
    }
    println("mapCount: ${mapCount.joinToString(",")}")*/

    return adjacentCellsChanged to false
}
//проверяем клетку на тупик
private fun cellCheck(cells: Deque<Pair<Int, Int>>, mapCount: IntArray, map: Array<IntArray>): Boolean {
    //println("\n cellCheck")
    //значение клетки
    while (cells.isNotEmpty()) {
        val cell = cells.pollFirst()
        val cellValue = map[cell.first][cell.second]
        //println("cellCheck - while, cell: ${cell.first}-${cell.second}, value: $cellValue")

        //проверяем тупики
        if (cellValue == 1) {
            //это либо вход/выход, либо тупик - тогда его тоже надо сделать непроходимым/0
            if (cell.first != 1 && cell.first != map.size - 2) {
                //println("if = 1 -> 0")
                //это не вход и не выход, значит тупик -> делаем 0
                map[cell.first][cell.second] = 0

                //уменьшаем счетчик линии на 1
                mapCount[cell.first - 1]-- // = mapCount[cell.first - 1] - 1
                //println("mapCount: ${mapCount.joinToString(",")}")

                //проверяем не закончилась ли линия
                if (mapCount[cell.first - 1] <= 0) return true

                //!! Рекурсия
                //клетка ведет в тупик, добавляем ее в список на проверку
                val adjacentCell = adjacentCell(intArrayOf(cell.first,cell.second), mapCount, map)

                if (adjacentCell.second) {
                    return true
                }
                else {
                    //проверяем соседние клетки, остался ли после этого путь
                    if (cellCheck(adjacentCell.first, mapCount, map)) return true
                }


                //cellsChanged.add(intArrayOf(i.first, i.second))

            }
            else { //иначе это вход-выход
                //println("if = 1 -> вход-выход")
                //проверяем есть ли от этой клетки путь дальше вниз

                if (cell.first == 1) {
                    //println("вход -> 1-${cell.second}, ${map[2][cell.second]}")
                    if (map[2][cell.second] == 0) {
                        //это вход, тогда

                        map[1][cell.second] = 0
                        mapCount[0]-- // = mapCount[1] - 1
                        //println("mapCount: ${mapCount.joinToString(",")}")
                        //проверяем не закончилась ли линия
                        if (mapCount[0] <= 0) return true

                        //клетка ведет в тупик, добавляем ее в список на проверку
                        //!! Рекурсия
                        //клетка ведет в тупик, добавляем ее в список на проверку
                        val adjacentCell = adjacentCell(intArrayOf(cell.first,cell.second), mapCount, map)

                        if (adjacentCell.second) {
                            return true
                        }
                        else {
                            //проверяем соседние клетки, остался ли после этого путь
                            if (cellCheck(adjacentCell.first, mapCount, map)) return true
                        }
                    }
                }
                //если это выход то ничего не делаем


                //клетка ведет в тупик, добавляем ее в список на проверку
                //cellsChanged.add(intArrayOf(i.first, i.second))
            }
            /*println("map")
            map.forEach {
                println(it.joinToString(","))
            }
            println("mapCount: ${mapCount.joinToString(",")}")*/
        } //остальные клетки не трогаем
    }
    return false
}

//1970 > 17:30 - 20:50 - notOK - нужны правки - 22:44 - не все работает, надо переписать
private fun latestDayToCross1970(row: Int, col: Int, cells: Array<IntArray>): Int {
    //рисуем карту с отступом по краям,
    //значение каждой клетки на карте равно количество не нулевых клеток по соседству (максимум = 4)
    //по краям 0 - препятствия\непроходимые клетки или вода
    //val map: Array<IntArray> = Array(row){ IntArray(col){0} }
    val map = Array(row + 2){IntArray(col + 2){4}}

    map[1] = IntArray(col + 2){3}
    map[map.size - 2] = IntArray(col + 2){3}
    /*println()
    map.forEach {
        println(it.joinToString(","))
    }*/

    map.forEachIndexed() { index, _ ->
        map[index][0] = 0
        map[index][1] = 3
        map[index][col + 1] = 0
        map[index][col] = 3
    }

    map[map.size - 1] = IntArray(col + 2){0}
    map[0] = IntArray(col + 2){0}

    map[1][1] = 2
    map[1][col] = 2
    map[row][1] = 2
    map[row][col] = 2

    println("map")
    map.forEach {
        println(it.joinToString(","))
    }

    //счетчик оставшихся клеток в линии, если в линии не осталось клеток то пути точно нет
    val mapCount = IntArray(row) {0}
    mapCount.forEachIndexed() { index, _ ->
        mapCount[index] = map[index + 1].sum()
    }
    //println("mapCount: ${mapCount.joinToString(",")}")

    //счетчик дней
    var dayCount = 0
    //проверка есть ли путь, если нет то завершаем все функции
    //var pathHave = true

    foo@while (true) {
        println()
        val cell = cells[dayCount]
        val cellsChanged: Deque<IntArray> = LinkedList()
        cellsChanged.add(cell)
        //проверяем изменения от добавления клетки с водой или непроходимой клетки
        while (cellsChanged.isNotEmpty()) {
            val coord = cellsChanged.pollFirst()
            //запоминаем значение на карте

            val tempCellValue = map[coord[0]][coord[1]]
            println("\n    row-col: ${coord[0]}-${coord[1]}, tempCellValue: $tempCellValue")

            //вычитаем из счетчика, проверяем не закончились ли клетки в линии
            println("mapCount 0: ${mapCount[coord[0] - 1]}")
            mapCount[coord[0] - 1] = mapCount[coord[0] - 1] - tempCellValue
            println("mapCount: ${mapCount[coord[0] - 1]}")
            //если клетки в линии закончились - пути нет, заканчиваем
            if (mapCount[coord[0] - 1] <= 0) break@foo

            //если путь может быть ->
            //записываем новое значение на карту
            map[coord[0]][coord[1]] = 0

            //меняем соседние клетки
            val arrayCells = arrayOf(
                coord[0] to coord[1] - 1, //west
                coord[0] to coord[1] + 1, //east
                coord[0] - 1 to coord[1], //north
                coord[0] + 1 to coord[1]  //south
            )

            for (i in arrayCells) {

                var cellCoord = map[i.first][i.second]
                println("i: $i, value: $cellCoord")

                if (cellCoord > 1) {
                    //просто уменьшаем на 1
                    map[i.first][i.second] -= 1

                    //println("mapCount 00: ${mapCount[i.first - 1]}")
                    mapCount[i.first - 1] = mapCount[i.first - 1] - 1
                    //println("mapCount: ${mapCount[i.first - 1]}")

                }

                cellCoord = map[i.first][i.second]

                if (cellCoord == 1) {
                    //println("if = 1")
                    //это либо вход-выход, либо тупик - тогда его тоже надо сделать непроходимым (=0)
                    if (i.first != 1 && i.first != row) {
                        println("if = 1 -> 0")
                        //это не вход и не выход, значит тупик -> делаем 0
                        map[i.first][i.second] = 0
                        mapCount[i.first - 1] = mapCount[i.first - 1] - 1

                        //клетка ведет в тупик, добавляем ее в список на проверку
                        cellsChanged.add(intArrayOf(i.first, i.second))

                    }
                    //else println("if = 1 -> вход-выход")
                    else { //иначе это вход-выход
                        println("if = 1 -> вход-выход")
                        //проверяем есть ли от этой клетки путь дальше вниз
                        if (i.first == 1) {
                            //если вход
                            println("вход -> 1-${i.second}")
                            //val cellCoordSouth = map[1][i.second]
                            if (map[2][i.second] == 0) map[1][i.second] = 0
                            mapCount[1] = mapCount[1] - 1

                            //клетка ведет в тупик, добавляем ее в список на проверку
                            //cellsChanged.add(intArrayOf(1, i.second))

                            println("map")
                            map.forEach {
                                println(it.joinToString(","))
                            }
                            println("mapCount: ${mapCount.joinToString(",")}, dayCount: $dayCount")
                        }
                        //клетка ведет в тупик, добавляем ее в список на проверку
                        //cellsChanged.add(intArrayOf(i.first, i.second))
                    }
                }
                if (mapCount[coord[0] - 1] <= 0) break@foo
            }

            //если значение не 0 - работаем
            /*if (tempCellValue != 0) {
            }*/
            //else { //если там уже 0, значит обрабатывать нечего, соседние клетки тоже нули }
        }
        //если пути точно нет, выходим из программы
        /*if (!pathHave) break*/

        //если путь возможен, запускаем поиск пути
        /*var checkPathHave = false
        while (!checkPathHave){
            for (i in 1..col - 2) {
                //если есть проход сверху
                if (map[1][i] > 0) {

                }
            }


            checkPathHave = true
        }*/

        dayCount++
        println("map")
        map.forEach {
            println(it.joinToString(","))
        }
        println("mapCount: ${mapCount.joinToString(",")}, dayCount: $dayCount")
    }
    return dayCount
}