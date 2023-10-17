package yandex.contest2023fall50496

import yandex.INPUT
import yandex.OUTPUT
import yandex.readFile
import yandex.writeFile

/**
 * G. Запускайте гуся
 *
 *
 *В последнее время на стримах школы мобильных разработчиков часто стали запускать гусей в чат. Естественно,
 * преподаватели из-за этого могут пропустить важные вопросы. Для борьбы с этим было принято решение ограничивать
 * возможность отправки сообщения тем юзерам, которые хотя бы k раз упомянули в своих сообщениях слово goose.
 * Так как вручную мониторить это достаточно сложно, вас попросили написать программу, которая по перечисленным
 * сообщениям определит список тех пользователей, к кому требуется применить санкции.
 *
 * Формат ввода
 * В первой строке содержится два целых числа n-количество сообщений в чате и k-количество упоминаний гуся для
 * применения санкций (1 ≤ n ≤ 105, 0 ≤ k ≤ 106). В следующих n парах строк содержится: nickname(i) - в первой
 * строке пары. Строка состоящая не более чем из 20 строчных символов английского алфавита. message(i) - строка
 * состоящая не более чем из 100 строчных символов английского алфавита и пробелов. Гарантируется что суммарная
 * длина всех сообщений не превосходит 2*106
 *
 * Формат вывода
 * В первой строке выведите n-количество пользователей попавших под санкции: В следующих n строках выведите
 * в алфавитном порядке nickname пользователей попавших под санкции.
 *
 * Пример 1
 *
 * Ввод
 * 3 1
 * android
 * gooseberries are very tasty
 * ios
 * the goose
 * android
 * goose is the coolest
 *
 * Вывод
 * 2
 * android
 * ios
 *
 * -----
 * Пример 2
 *
 * Ввод
 * 3 2
 * android
 * gooseberries are very tasty
 * ios the goose
 * android
 * goose is the coolest
 *
 * Вывод
 * 0
 */



fun main(args: Array<String>) {
    gooseFinder()
}

fun gooseFinder(){
    //OK - 374ms - все тесты пройдены

    val keyWord = "goose"

    val input = readFile(INPUT)

    val nK = input[0].split(" ")
    val messagesCounter = nK.first().toInt()
    val gooseCounter = nK[1].toInt()

    var count = 1

    val nicknames = HashMap<String, Int>()
    val badUsers = HashSet<String>()

    repeat(messagesCounter) {
        val nickname = input[count]
        val message = input[count + 1]

        if (message.contains("$keyWord ") || message.contains(" $keyWord") || message.contains(" $keyWord ")) {
            if (nicknames[nickname] != null) {
                nicknames[nickname] = nicknames[nickname]!! + 1
            }
            else nicknames[nickname] = 1

            if (nicknames[nickname]!! >= gooseCounter) badUsers.add(nickname)
        }

        count += 2
    }

    val sortedBadUsers = badUsers.sorted()

    var answer = "${badUsers.size}"
    repeat(badUsers.size) {
        answer = "$answer\n${sortedBadUsers[it]}"
    }

    writeFile(answer, OUTPUT)
}