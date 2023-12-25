package yandex.algorithm2022upsolving

import yandex.writeFile
import java.io.BufferedReader
import java.io.FileReader
import java.util.PriorityQueue

/**
 * A. Клетчатая доска
 *
 * Решение, корректно работающее в ограничениях 1≤k≤3, будет оценено в 3 балла.
 * Полное решение будет оценено в 6 баллов (включая 3 балла за подзадачу выше).
 *
 * Вася записался на кружок деревообработки. Уже завтра ему нужно принести первое собственное изделие — шахматную доску.
 * Но «папа у Васи силен в математике», поэтому посоветовал сформулировать задание шире — сделать доску размера n×n и
 * раскрасить клетки в k цветов.
 *
 * Определите, можно ли раскрасить клетки доски n×n в k цветов так, чтобы
 * - Количество клеток каждого цвета было равно n^2 / k.
 * - Никакие две соседние по сторонам клетки не были окрашены в один цвет.
 *
 * Формат ввода
 * В единственной строке записаны два целых числа n и k (1 ≤ n, k ≤ 10).
 *
 * Формат вывода
 * Если раскраску доски, которую предложил папа Васи, получить невозможно, то выведите в единственной строке значение
 * No, иначе в первой строке выведите значение Yes, а в следующих n строках выведите по n целых чисел cij.cij(1≤cij≤k) —
 * цвет, в который Васе следует раскрасить клетку в i-м ряду и j-м столбце.
 *
 * Если подходящих раскрасок несколько, выведите любую из них.
 */

/**
Пример 1
Ввод
8 2

Вывод
Yes
1 2 1 2 1 2 1 2
2 1 2 1 2 1 2 1
1 2 1 2 1 2 1 2
2 1 2 1 2 1 2 1
1 2 1 2 1 2 1 2
2 1 2 1 2 1 2 1
1 2 1 2 1 2 1 2
2 1 2 1 2 1 2 1

Пример 2
Ввод
2 1

Вывод
No

Пример 3
Ввод
3 3

Вывод
Yes
1 2 1
2 3 2
3 1 3

 */

fun main(args: Array<String>) {
    checkeredBoard()
}

//16:10 - 17:50 (1ч40мин) > NotOK частичное решение (285ms, 20.96Mb, не прошел тест 19)
//17:50 - 18:10 (20мин) поправил > OK (274ms, 21.00Mb, 6 баллов) нужен был break@loop
fun checkeredBoard(){
    val input = BufferedReader(FileReader("input.txt"))

    val (n, k) = input.readLine().split(" ").map { it.toInt() }


    val chessBoard: Array<IntArray> = Array(n){ IntArray(n){0} }

    if ((n * n) % k != 0) {
        //если цветов не поровну
        writeFile("No", "output.txt")
    }
    else {
        val pq = PriorityQueue<Pair<Int, Int>>{p1, p2 -> p2.second - p1.second}

        val nColor = (n * n) / k

        repeat(k) {
            pq.add(it + 1 to nColor)
        }

        var checkPaintCell = true

        var answer = "Yes"

        for (i in 0 until n) {
            for (j in 0 until n) {
                val lockedColorSet = checkLockedColor(i, j, chessBoard)

                val tempListColorForPQ = mutableListOf<Pair<Int,Int>>()

                loop@ while (pq.isNotEmpty()) {
                    //раскрашиваем клетку
                    val color = pq.poll()

                    if (!lockedColorSet.contains((color.first))) {
                        chessBoard[i][j] = color.first

                        tempListColorForPQ.add(color.first to color.second - 1)
                        break@loop
                    }
                    else {
                        tempListColorForPQ.add(color.first to color.second)
                    }
                }
                if (chessBoard[i][j] == 0) checkPaintCell = false
                tempListColorForPQ.forEach { pq.add(it) }
                tempListColorForPQ.removeAll(tempListColorForPQ)
            }

            answer = "$answer\n${chessBoard[i].joinToString(" ")}"

        }

        if (!checkPaintCell) answer = "No"
        writeFile(answer, "output.txt")
    }
}

private fun checkLockedColor(i: Int, j:Int, arr: Array<IntArray>): HashSet<Int>{
    //проверяем соседние клетки (левую и верхнюю) и составляем список цветов, которые нельзя использовать
    val set = HashSet<Int>()

    if (j - 1 >= 0) set.add(arr[i][j - 1]) //left
    if (i - 1 >= 0) set.add(arr[i - 1][j]) //up
    return set
}