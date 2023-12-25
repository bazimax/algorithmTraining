package yandex.algorithm2022upsolving

import yandex.OUTPUT_FILE
import yandex.writeFile
import java.io.BufferedReader
import java.io.FileReader
import java.util.PriorityQueue
import kotlin.math.min

//D. Макс и синтез аминокислот

fun main(args: Array<String>) {
    maxAndAminoAcidSynthesisV2()
}
fun maxAndAminoAcidSynthesis(){
    //не работает
    val input = BufferedReader(FileReader("input.txt"))

    val (n, l) = input.readLine().split(" ").map { it.toInt() }

    val baseAminoAcid = "A".repeat(l)

    val arrAminoAcids: Array<Pair<String, Int>> = Array(n + 1){"" to Int.MAX_VALUE}


    arrAminoAcids[0] = baseAminoAcid to Int.MAX_VALUE

    repeat(n){
        val aminoAcid = input.readLine()
        arrAminoAcids[it + 1] = aminoAcid to Int.MAX_VALUE
    }

    var countSteps = 0

    arrAminoAcids.forEach { println("${it.first}-${it.second}") }

    arrAminoAcids.forEachIndexed { index, p1 ->
        var minStep = p1.second
        println("- ${p1.first} $minStep")
        arrAminoAcids.forEach { p2 ->
            if (p2.first != p1.first) {
                val step = minStepToConvertSymbols(p1.first, p2.first)
                //println("-- $step")
                minStep = min(minStep, step)
            }
        }
        arrAminoAcids[index] = p1.first to minStep
        println("= ${arrAminoAcids[index].first}-${arrAminoAcids[index].second}")
    }
    arrAminoAcids.forEach { println("${it.first}-${it.second}") }

    arrAminoAcids.forEach {
        countSteps += it.second
    }

    writeFile("$countSteps", OUTPUT_FILE)

    println(countSteps)
}

//18:00 - 19:50 (1ч50мин) > OK (1.215s, 26.50Mb, 10 баллов)
fun maxAndAminoAcidSynthesisV2(){
    //работает
    val input = BufferedReader(FileReader("input.txt"))

    val (n, l) = input.readLine().split(" ").map { it.toInt() }

    var pqAminoAcids = PriorityQueue<Pair<String, Int>>{a, b -> a.second - b.second}

    val baseAminoAcid = "A".repeat(l)

    repeat(n){
        val aminoAcid = input.readLine()
        val minStep = minStepToConvertSymbols(baseAminoAcid, aminoAcid)
        pqAminoAcids.add(aminoAcid to minStep)
    }

    var countSteps = 0

    while(pqAminoAcids.isNotEmpty()) {
        val testedAminoAcid = pqAminoAcids.poll()
        countSteps += testedAminoAcid.second

        //println("+ $testedAminoAcid, countSteps: $countSteps, sizePQ: ${pqAminoAcids.size}")

        val pqTemp = PriorityQueue<Pair<String, Int>>{a, b -> a.second - b.second}

        repeat(pqAminoAcids.size) {
            //проверяем каждый элемент - достаем из очереди, проверяем и возвращаем с новым\старым значением шага
            //согласно очереди
            val tempAmino = pqAminoAcids.poll()
            val step = minStepToConvertSymbols(testedAminoAcid.first, tempAmino.first)
            val minStep = min(step, tempAmino.second)
            pqTemp.add(tempAmino.first to minStep)

            //println("-- ${pqAminoAcids.peek()}")
        }

        pqAminoAcids = pqTemp

        //println("- ${pqAminoAcids.peek()}")
    }

    writeFile("$countSteps", "output.txt")

    //println(countSteps)
}

private fun minStepToConvertSymbols(p1: String, p2: String): Int{
    var count = 0
    p1.forEachIndexed { index, c ->
        if (c != p2[index]) {
            when(c) {
                'A' -> if(p2[index] == 'G') count += 2 else count++
                'C' -> if(p2[index] == 'T') count += 2 else count++
                'G' -> if(p2[index] == 'A') count += 2 else count++
                'T' -> if(p2[index] == 'C') count += 2 else count++
            }
        }
    }
    //println("p1: $p1, p2: $p2 - $count")
    return count
}