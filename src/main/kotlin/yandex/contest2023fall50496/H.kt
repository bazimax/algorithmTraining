package yandex.contest2023fall50496

import yandex.INPUT
import yandex.OUTPUT
import yandex.readFile
import yandex.writeFile

class H {

    /**
     * H. Доставка от Валли
     *
     *
     * Робот-курьер Валли получил задание доставить посылку из пункта А в пункт Б. Весь маршрут Валли пролегает
     * вдоль оживленной дороги с множеством перекрестков под названием Млечный Путь. Пункт А находится слева от
     * Млечного Пути, а пункт Б — справа. Валли очень не нравится переходить дорогу по пешеходному переходу.
     * Хорошо, что у него есть карта и он успел ее изучить. Помогите Валли определить минимальное количество
     * пешеходных переходов, которыми ему придется воспользоваться.
     *
     * Формат ввода
     * Единственная строка содержит описание Млечного Пути между точками А и Б. Длина строки не превосходит 10^5
     * символов. Каждый символ строки может быть одной из трех цифр — 1, 2 или 3:
     * - цифра 1 означает, что есть дорога, впадающая в Млечный Путь слева;
     * - цифра 2 — есть дорога, впадающая в Млечный Путь справа;
     * - цифра 3 — есть дороги, впадающие в Млечный Путь слева и справа в одном месте.
     *
     * Формат вывода
     * Выведите одно число - минимальное количество пешеходных переходов, которыми придется воспользоваться Валли.
     *
     * Пример
     *
     * Ввод 113122321
     * Вывод 5
     */

    fun main(args: Array<String>) {
        pathForWALLE()
    }
}

fun pathForWALLE(){
    //notOK - не успел -

    val path = readFile(INPUT)[0]
    println(path)

    var pathLeft = 1
    var pathRight = 1

    var sumCrossroads = 1
    var answer = 1

    path.forEachIndexed { index, road ->
        println("road: $road")

        when (road){
            '1' -> pathLeft += 1
            '2' -> pathRight += 1
            '3' -> {
                pathLeft += 1
                pathRight += 1
            }
        }
        if (index >= path.length - 1) {
            answer = sumCrossroads
            println("1 - answer: $answer, sumCrossroads: $sumCrossroads, pathRight: $pathRight, pathLeft: $pathLeft")
        }
        else {
            println("2 - answer: $answer, sumCrossroads: $sumCrossroads, pathRight: $pathRight, pathLeft: $pathLeft")
            val max = Math.max(pathRight, pathLeft)
            val min = Math.min(pathRight, pathLeft)
            if (max - min >= 2 ) {
                println("3 - answer: $answer, sumCrossroads: $sumCrossroads, pathRight: $pathRight, pathLeft: $pathLeft")
                sumCrossroads = min
                pathRight = sumCrossroads + 1
                pathLeft = sumCrossroads + 1
            }
            else {
                //val max = Math.max(pathRight, pathLeft)
                println("4 - answer: $answer, sumCrossroads: $sumCrossroads, pathRight: $pathRight, pathLeft: $pathLeft")

                sumCrossroads = 1 + Math.min(pathRight, pathLeft)
            }
        }
    }
    answer = sumCrossroads

    writeFile(answer.toString(), OUTPUT)
}