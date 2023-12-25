package tinkoff.eternalcontest

/**
 * 1 задание
 *
 * Костя подключен к мобильному оператору «Мобайл». Абонентская плата Кости составляет A рублей в месяц.
 * За эту стоимость Костя получает B мегабайт интернет-трафика. Если Костя выйдет за лимит трафика,
 * то каждый следующий мегабайт будет стоить ему C рублей.
 *
 * Костя планирует потратить D мегабайт интернет-трафика в следующий месяц. Помогите ему сосчитать,
 * во сколько рублей ему обойдется интернет.
 */

//import tinkoff.runTest
import java.util.*

fun main(args: Array<String>) {
    internetTraffic()
    //runTest("internetTraffic", examplesA, ::internetTrafficForTesting)
}

private val examplesA = arrayOf(
    Pair("100  10  12  15", "160"),
    Pair("100  10  12  1", "100"),
    //Pair("12", "2"),
)

private fun internetTraffic(){
    val scan = Scanner(System.`in`)
    val pay = scan.nextInt() // A - Абонентская плата - рубли
    val limit = scan.nextInt() // B - Интернет-трафик - мегабайты
    val overLimit = scan.nextInt() // C - Свыше лимита, за каждый мегабайт - рубли
    val totalMb = scan.nextInt() // D - Планирует потратить - мегабайты

    val answer = if (totalMb > limit) ((totalMb - limit) * overLimit) + pay else pay

    println(answer)
}

// BACKUP with LOGS for TEST
private fun internetTrafficForTesting(str: String) : String{
    /*val scan = Scanner(System.`in`)
    val pay = scan.nextInt() // A - Абонентская плата - рубли
    val limit = scan.nextInt() // B - Интернет-трафик - мегабайты
    val overLimit = scan.nextInt() // C - Свыше лимита, за каждый мегабайт - рубли
    val totalMb = scan.nextInt() // D - Планирует потратить - мегабайты*/

    val (pay, limit, overLimit, totalMb) = str.split("  ").map { it.toInt() }

    val answer = if (totalMb > limit) ((totalMb - limit) * overLimit) + pay else pay

    println(answer)
    return answer.toString()
}
