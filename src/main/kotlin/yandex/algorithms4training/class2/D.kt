package yandex.algorithms4training.class2

/**
 * D. Кубики в зеркале //Cubes in the mirror
 *
 * Привидение Петя любит играть со своими кубиками. Он любит выкладывать их в ряд и разглядывать свое творение.
 * Недавно друзья решили подшутить над Петей и поставили в его игровой комнате зеркало. Известно, что привидения
 * не отражаются в зеркале, а кубики отражаются. Теперь Петя видит перед собой N цветных кубиков, но не знает,
 * какие из этих кубиков настоящие, а какие — отражение в зеркале. Выясните, сколько кубиков может быть у Пети.
 * Петя видит отражение всех кубиков в зеркале и часть кубиков, которая находится перед ним. Часть кубиков может
 * быть позади Пети, их он не видит.
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter


fun main(args: Array<String>) {
    //cubesInTheMirrorV2()
    runTest("cubesInTheMirror", examplesD, ::cubesInTheMirrorV2, )
}

private val examplesD = arrayOf(
    Pair("6 2\n" + "1 1 2 2 1 1", "3 5 6"), //1
    Pair("3 30\n" + "7 9 9", "3"), //6
    Pair("1 1\n" + "1", "1"),
    Pair("2 1000000\n" + "1000000 1000000", "1 2"),
    Pair("7 1000000\n" + "1 2 2 3 3 2 2", "7"),
    Pair("3 6\n" + "1 2 3", "3"),
    Pair("3 256\n" + "1 2 2", "3"),
    //Pair("12", "2"),
)

// OK (1.361s, 146.63Mb)
private fun cubesInTheMirrorV2(){
    val input = BufferedReader(FileReader("input.txt"))
    //1 ≤ N ≤ 1000000, 1 ≤ M ≤ 1000000
    val (n, m) = input.readLine().trim().split(" ").map { it.toInt() }

    val s = input.readLine().trim().split(" ").map { it.toInt() }
    val sNormal = listOf(0) + s
    val sRev = listOf(0) + s.asReversed()

    //заранее рассчитываем хеши подстрок
    val size = sNormal.size
    val p = powIa(10, 9) + 7 //10.0.pow(9) + 7
    val x = m + 1
    val hNormal = LongArray(size) {0}
    val xNormal = LongArray(size) {0}
    xNormal[0] = 1L

    for (i in 1 until size) {
        hNormal[i] = (hNormal[i - 1] * x + sNormal[i]) % p
        xNormal[i] = (xNormal[i - 1] * x) % p
    }

    val hRev = LongArray(size) {0}
    val xRev = LongArray(size) {0}
    xRev[0] = 1L

    for (i in 1 until size) {
        hRev[i] = (hRev[i - 1] * x + sRev[i]) % p
        xRev[i] = (xRev[i - 1] * x) % p
    }

    val list = mutableListOf<Int>()
    list.add(size - 1)

    val half = if (size % 2 != 0)  size / 2 else (size / 2) - 1
    for (i in 1 .. half) {
        if (compareStrRev(i, i, size - 1 - i, hNormal, xNormal, hRev, xRev, p)) list.add(size - 1 - i)
    }

    val ans = list.reversed().joinToString(" ")

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

private fun compareStrRev(len: Int, stringStartA: Int = 0, stringStartB: Int, hNormal: LongArray, xNormal: LongArray, hRev: LongArray, xRev: LongArray, p: Int) : Boolean {
    //val stringStartA = 0
    val a = (hNormal[stringStartA + len] + hRev[stringStartB] * xNormal[len]) % p
    val b = (hRev[stringStartB + len] + hNormal[stringStartA] * xRev[len]) % p
    //println("$a == $b, ${a == b}")
    return a == b
}
private fun powIa(num: Int, degree: Int) : Int {
    if (degree == 0) return 1

    var ans = 1
    repeat(degree) { ans *= num }

    return ans
}


// BACKUP String version
private val examplesDBackup = arrayOf(
    Pair("6 256\n" + "a a b b a a", "3 5 6"),
    Pair("7 256\n" + "b a a c c a a", "7"),
    Pair("3 256\n" + "b a c", "3"),
    Pair("3 256\n" + "a b b", "3"),
)
private fun cubesInTheMirrorBackup(){
    val input = BufferedReader(FileReader("input.txt"))
    //1 ≤ N ≤ 1000000, 1 ≤ M ≤ 1000000
    val (n, m) = input.readLine().trim().split(" ").map { it.toInt() }

    val s = input.readLine().trim().split(" ")//.map { it.toInt() }
    val sNormal = listOf(" ") + s
    val sRev = listOf(" ") + s.asReversed()
    //println("  s:$sNormal \n sR:$sRev")
    var answer = ""

    //заранее рассчитываем хеши подстрок
    //val s1 = " $s"
    val size = sNormal.size
    val p = powIa(10, 9) + 7 //10.0.pow(9) + 7
    val x = m + 1
    val hNormal = LongArray(size) {0}
    val xNormal = LongArray(size) {0}
    xNormal[0] = 1L

    val a = sNormal[0][0]

    for (i in 1 until size) {
        hNormal[i] = (hNormal[i - 1] * x + sNormal[i][0].code) % p
        xNormal[i] = (xNormal[i - 1] * x) % p
    }
    //println(hNormal.joinToString(" "))
    //println(xNormal.joinToString(" "))

    val hRev = LongArray(size) {0}
    val xRev = LongArray(size) {0}
    xRev[0] = 1L

    for (i in 1 until size) {
        hRev[i] = (hRev[i - 1] * x + sRev[i][0].code) % p
        xRev[i] = (xRev[i - 1] * x) % p
    }
    //println(hRev.joinToString(" "))
    //println(xRev.joinToString(" "))


    //val tt = compareStrRev(3, 0, 0, hNormal, xNormal, hRev, xRev, p)

    val list = mutableListOf<Int>()


    //var equal = true
    var len = size - 1
    list.add(len)

    //println(" -len:$len")

    for (i in 1 until (size - 1) / 2) {
        len -= i
        if (compareStrRev(len, i, i, hNormal, xNormal, hRev, xRev, p)) list.add(len)
        //else println(" else")
        //println(" i:$i, len:$len")
    }
    /*while (equal && len-- > 0) {

    }*/
    val ans = list.reversed().joinToString(" ")
    //println(ans)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}