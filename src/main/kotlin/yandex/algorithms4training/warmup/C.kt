package yandex.algorithms4training.warmup

/**
 * C. Путешествие по Москве
 *
 * Мэрия Москвы основательно подготовилась к празднованию тысячелетия города в 2147 году, построив под столицей
 * бесконечную асфальтированную площадку, чтобы заменить все существующие в городе автомобильные дороги. В память
 * о кольцевых и радиальных дорогах разрешили двигаться по площадке только двумя способами:
 * - В сторону точки начала координат или от неё. При этом из точки начала координат разрешено двигаться в любом
 * направлении.
 * - Вдоль окружности с центром в начале координат и радиусом, который равен текущему расстоянию до начала координат.
 * Двигаться вдоль такой окружности разрешается в любом направлении (по или против часовой стрелки).
 *
 * Вам, как ведущему программисту ответственной инстанции поручено разработать модуль, который будет определять
 * кратчайший путь из точки A, с координатами (xA, yA) в точку B с координатами (xB, yB). Считайте, что менять
 * направление движения можно произвольное количество раз, но оно должно всегда соответствовать одному из двух
 * описанных выше вариантов.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.*

fun main(args: Array<String>) {
    travelInMoscow()
}

//7:10 - (20мин перерыв) - 9:20 (1ч50мин) > OK (260ms, 16.68Mb)
fun travelInMoscow(){
    val input = BufferedReader(FileReader("input.txt"))
    val (xA, yA, xB, yB) = input.readLine().split(" ").map { it.toDouble() }

    //Определяем радиусы
    val radiusA = sqrt(abs(xA) * abs(xA) + abs(yA) * abs(yA))
    val radiusB = sqrt(abs(xB) * abs(xB) + abs(yB) * abs(yB))

    val angleA = atan2(xA, yA) * 180 / Math.PI
    val angleB = atan2(xB, yB) * 180 / Math.PI

    //определяем угол между точками
    val tempAngle = abs(angleA - angleB)
    val angle = if (tempAngle < 180) tempAngle else 360 - tempAngle

    //Длина дуги: L = 2πr (α/360)
    val arcLength = 2 * Math.PI * min(radiusA, radiusB) * (angle / 360)

    //определяем кратчайший путь (по дуге и радиусу или по двум радиусам)
    val way1 = radiusA + radiusB
    val way2 = arcLength + abs(radiusA - radiusB)

    val ans = String.format("%.12f", min(way1, way2)).replace(',', '.')

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}


/*fun testAngle(xA: Double, yA:Double, xB:Double, yB:Double){
import java.math.RoundingMode
import java.text.DecimalFormat

    testAngle(-3.0, -3.0, 3.0, 3.0)
    testAngle(1.0, 2.5, 3.0, 3.0)

    testAngle(-3.0, -3.0, 0.0, 0.0)
    testAngle(1.0, 2.5, 0.0, 0.0)
    //cos(α) = (1*3 + 2.5*3) / (sqrt(1*1 + 2.5*2.5) * sqrt(3*3 + 3*3)) ≈ 0.91914503001

    val radiusA = sqrt( (abs(xA) * abs(xA) + abs(yA) * abs(yA)) )

    val radiusB = sqrt( (abs(xB) * abs(xB) + abs(yB) * abs(yB)) )
    println("$radiusA - $radiusB")

    val cos = round((xA * xB + yA * yB) / (sqrt(xA*xA + yA*yA) * sqrt(xB*xB + yB*yB))) // (radiusA * radiusB)
    val aCos = acos(cos)
    val angle = aCos * 180 / Math.PI
    println("1 cos: $cos, aCos: $aCos, angle: $angle")


    val cos2 =  (xA * xB + yA * yB) / (radiusA * radiusB)
    val roundOff = String.format("%.9f", cos2).replace(',', '.')
    //println(roundOff)
    val aCos2 = acos(roundOff.toDouble())
    val angle2 = aCos2 * 180 / Math.PI
    println("2 cos: $cos2, aCos: $aCos2, angle: $angle2")


    val atan = atan2(xA, yA)
    val angle3 = atan * 180 / Math.PI
    println("3 atan: $atan, angle: $angle3")

    println("-".repeat(10))
}*/