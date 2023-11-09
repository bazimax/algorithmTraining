package yandex.contest2023fall50496

import yandex.INPUT_FILE
import yandex.OUTPUT_FILE
import yandex.readFile
import yandex.writeFile

/**
 * I. Восстановление логов
 *
 *
 * Мобильный разработчик Вася обнаружил значительный рост числа крешей в своем приложении. Чтобы разобраться в
 * причинах этого, он начал анализировать пользовательские логи перед возникновением крешей. Однако Вася заметил,
 * что некоторые переходы между экранами не были зарегистрированы в логах. Это привело его к вопросу: сколько
 * существует экранов, до которых невозможно восстановить путь на основе имеющихся логов?
 *
 * Формат ввода
 * Первая строка содержит два целых числа: n (1 ≤ n ≤ 10^3) - количество экранов в приложении,
 * m (0 ≤ m ≤ 10^6) - количество записей в логах. Вторая строка содержит n строк, описывающих названия экранов.
 * В третьей строке содержится одна строка - название главного экрана. В следующих m строках перечислены записи
 * в логах. Запись представляет собой две строки: экран, с которого ушел пользователь и экран, на который
 * пользователь попал. Гарантируется, что суммарная длина строк во входных данных не превосходит 10^6
 *
 * Формат вывода
 * Необходимо вывести количество экранов, до которых невозможно восстановить путь по логам.
 *
 * Пример 1
 *
 * Ввод
 * 4 3
 * a b c d
 * a
 * a b
 * b c
 * a c
 *
 * Вывод
 * 1
 *
 * -----
 * Пример 2
 *
 * Ввод
 * 5 5
 * main reader webpage profile alice
 * main
 * main reader
 * reader main
 * webpage profile
 * profile alice
 * alice main
 *
 * Вывод
 * 3
 */

fun main(args: Array<String>) {
    logRecovery()
}

fun logRecovery(){
    //notOK - 241ms - на контесте пройден частично (не прошел 3 тест, что за тест непонятно)

    val input = readFile(INPUT_FILE)

    val nM = input[0].split(" ")
    //val screenCount = nM.first().toInt()
    val logCount = nM[1].toInt()

    val listOfScreenNames = input[1].split(" ")
    val visitedScreens = HashMap<String, Int>()

    val mainScreen = input[2]

    listOfScreenNames.forEach {
        if (it != mainScreen) visitedScreens[it] = 0
    }

    repeat(logCount) {
        val log = input[it + 3].split(" ")
        if (log[0] == mainScreen) visitedScreens[log[1]] = visitedScreens[log[1]]!! + 1
    }

    var answer = 0
    visitedScreens.forEach {
        if (it.value == 0) answer++
    }

    writeFile(answer.toString(), OUTPUT_FILE)
}