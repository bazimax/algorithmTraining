package yandex.cup2023android

import yandex.INPUT_FILE
import yandex.OUTPUT_FILE
import yandex.readFile
import yandex.writeFile
import java.io.BufferedReader
import java.io.FileReader
import java.util.*

/**
 * Группа уличных художников «Цветы жизни» создаёт масштабные картины, высаживая несколько видов растений на улицах
 * города. Во время их цветения группа снимает результат с высоты птичьего полета и выкладывает полученные фотографии
 * в сеть.
 *
 * Свою новую работу художники задумали такой, чтобы её можно было наблюдать с воздуха в любое время года.
 * Картина в течение года будет меняться, так как в зависимости от сезона цветут разные растения.
 *
 * Специально под эту задачу художники подобрали k видов растений. Для каждого из видов растений они определили период
 * начала цветения (start(i),end(i)) и его продолжительность flowering−period(i). i-е растение может начать цвести
 * равновероятно в любой из дней в промежутке от start(i) до end(i) включительно, а flowering−period(i) указывает на то,
 * сколько дней после начала цветёт этот вид растения.
 *
 * Главное — картина должна цвести круглый год. В году ровно t дней. На основе данных о выведенных растениях определите,
 * получится ли у художников осуществить задуманное.
 *
 * Формат ввода
 * В первой строке находятся два целых числа k, t (1≤k≤2∗10^5,1≤t≤10^18). В следующих k строках содержатся по 3 числа,
 * i -я строка содержит данные об i -м растении: start(i),end(i),flowering−period(i)
 * (1≤start(i),end(i),flowering−period(i)≤t)
 *
 * Формат вывода
 * В единственной строке выведите "Yes" если задумка художников реализуема, и "No" в противном случае.
 *
 * Пример 1
 * Ввод
 * 5 10
 * 4 6 7
 * 2 3 3
 * 1 1 4
 * 2 3 1
 * 2 4 5
 * Вывод Yes
 *
 * Пример 2
 * 3 10
 * 1 5 10
 * 2 3 4
 * 2 6 5
 * Вывод No
 */

/**
 * Не проходит 10 тест (ML - ограничение 64мб, тест >78мб)
 *
 * Я так предполагаю что дело в hashSet (ML - 10тест) - при больших числах много места занимает.
 * - Наверно, тут поможет хранение данных отрезками (но не знаю сильно ли это поможет при коротких отрезках)
 * - Возможно, хранение всех данных в input занимает много места и лучше пользоваться консольным вводом.
 *
 * v5 - по идее должен работать
 */

fun main(args: Array<String>) {
    flowersOfLifeV5()
    yandex.runTest("123", examplesP(), ::flowersOfLifeV5)
}

private fun examplesP(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("5 10\n" +
                "4 6 7\n" +
                "2 3 3\n" +
                "1 1 4\n" +
                "2 3 1\n" +
                "2 4 5",
            "Yes"),
        Pair("3 10\n" +
                "1 5 10\n" +
                "2 3 4\n" +
                "2 6 5",
            "No"),
        Pair("1 2\n" +
                "1 1 1",
            "No"),
        Pair("1 1\n" +
                "1 1 1",
            "Yes"),
        Pair("2 1\n" +
                "1 1 1\n" +
                "1 1 1",
            "Yes"),
        Pair("2 23232445443434343\n" +
                "1 23232445443434343 23232445443434343\n" +
                "1 1 1",
            "No"),
        //Pair("12", "2"),
    )
}


fun flowersOfLife() {
    //не проходил 5 тест (RE) - скорее всего большое число - поправил long, теперь наверно на 10 тесте падает
    val input = readFile(INPUT_FILE)
    val k: Long = input[0].split(" ")[0].toLong()
    val t: Long = input[0].split(" ")[1].toLong()

    val setFlowering = hashSetOf<Int>()

    val list = mutableListOf<List<Long>>()
    repeat(k.toInt()) {

        val flower = input[it + 1].split(" ").map { item -> item.toLong() }
        list.add(flower)
    }

    list.forEach {
        val startFlowering = it[1]
        val endFlowering = it[0] + it[2] - 1
        for (i in startFlowering..endFlowering) {
            if (i <= t) setFlowering.add(i.toInt())
        }
    }

    val answer = if (setFlowering.size.toLong() == t) "Yes" else "No"
    writeFile(answer, OUTPUT_FILE)
}

fun flowersOfLifeV2() {
    //не проходит 10 тест (ML - ограничение 64мб, тест >78мб)

    val input = readFile("input.txt")
    val k: Long = input[0].split(" ")[0].toLong()
    val t: Long = input[0].split(" ")[1].toLong()

    val setFlowering = hashSetOf<Int>()

    repeat(k.toInt()) {

        val flower = input[it + 1].split(" ").map { item -> item.toLong() }

        val startFlowering = flower[1]
        val endFlowering = flower[0] + flower[2] - 1
        for (i in startFlowering..endFlowering) {
            if (i <= t) setFlowering.add(i.toInt())
        }
        //println("it: $it, set = $setFlowering")
    }

    //println(setFlowering)

    val answer = if (setFlowering.size.toLong() == t) "Yes" else "No"
    writeFile(answer, "output.txt")
}

fun flowersOfLifeV3() {
    //использует меньше памяти, но с большими числами все равно работать будет скорее всего плохо
    //?? setFlowering.size.toLong()

    val input = BufferedReader(FileReader("input.txt"))

    val (k, t) = input.readLine().split(" ").map { it.toLong() }

    val setFlowering = hashSetOf<Long>()

    repeat(k.toInt()) {
        val (
            possibleStartFlowering,
            reliableStartFlowering,
            floweringPeriod
        ) = input.readLine().split(" ").map { it.toLong() }

        val reliableEndFlowering = possibleStartFlowering + floweringPeriod - 1
        for (i in reliableStartFlowering..reliableEndFlowering) {
            if (i <= t) setFlowering.add(i)
        }
    }

    input.close()

    //!!есть вопросы к setFlowering.size.toLong()
    val answer = if (setFlowering.size.toLong() == t) "Yes" else "No"
    writeFile(answer, "output.txt")
}

fun flowersOfLifeV4console() {
    /*
для консоли
5 10
4 6 7
2 3 3
1 1 4
2 3 1
2 4 5
---
3 10
1 5 10
2 3 4
2 6 5
*/
    //console input
    val scan = Scanner(System.`in`)

    val k = scan.nextInt()
    val t = scan.nextLong()

    val setFlowering = hashSetOf<Long>()

    repeat(k) {


        val possibleStartFlowering = scan.nextInt()
        val trueStartFlowering = scan.nextInt() //possibleEndFlowering
        val floweringPeriod = scan.nextLong()

        val trueEndFlowering = possibleStartFlowering + floweringPeriod
        for (i in trueStartFlowering..trueEndFlowering) {
            if (i <= t) setFlowering.add(i)
        }
    }
    scan.close()


    val answer = if (setFlowering.size.toLong() == t) "Yes" else "No"
    //writeFile(answer, "output.txt")
    print(answer)
}

fun flowersOfLifeV5(){
    //Возможно самое оптимальное решение, хоть и медленнее flowersOfLifeV2

    val input = BufferedReader(FileReader("input.txt"))

    val (kLong, t) = input.readLine().split(" ").map { it.toLong() }
    val k = kLong.toInt()

    val pq = PriorityQueue<LongArray>(k){ p1, p2 -> p1[0].toInt() - p2[0].toInt() }

    repeat(k) {
        val (
            possibleStartFlowering,
            reliableStartFlowering,
            floweringPeriod
        ) = input.readLine().split(" ").map { it.toLong() }

        val reliableEndFlowering = possibleStartFlowering + floweringPeriod - 1

        pq.add(longArrayOf(reliableStartFlowering, reliableEndFlowering))

    }

    input.close()

    var answer = "Yes"
    val currentT = longArrayOf(1, 1)

    /*repeat(k) {
        //В худшем случае (при первом неправильном варианте) пройдет по всем растениям
        val value = pq.poll()
        //println("=== ${value[0]} - ${value[1]}")

        if (answer == "Yes" && value[0] >= currentT[0] && value[0] <= currentT[1]) {
            currentT[1] = max(currentT[1], value[1])
        }
        else answer = "No"
    }
    */

    /*pq.forEach {
        //forEach - выдает результаты не по порядку
        //В худшем случае (при первом неправильном варианте) пройдет по всем растениям

        //println("++ ${it[0]} - ${it[1]}")
        if (answer == "Yes" && it[0] >= currentT[0] && it[0] <= currentT[1]) {
            currentT[1] = max(currentT[1], it[1])
            //println("ans: $answer, curr: ${currentT[0]}-${currentT[1]}")
        }
        else answer = "No"
    }
    */

    for (i in 1..k) {
        //в худшем случае пройдет по всем растениям - будет медленнее forEach
        val value = pq.poll()
        if (value[0] >= currentT[0] && value[0] <= currentT[1]) {
            currentT[1] = max(currentT[1], value[1])
        }
        else if (currentT[0] == 1L && currentT[1] == t) break
        else {
            answer = "No"
            break
        }
    }
    if (currentT[1] != t) answer = "No"

    writeFile(answer, "output.txt")
}

private fun max(a: Long, b: Long): Long{
    return if (a >= b) a else b
}

