package yandex.contest2023fall50496

import yandex.INPUT
import yandex.OUTPUT
import yandex.CORRECT_ANSWER
import yandex.readFile
import yandex.writeFile



fun main(args: Array<String>) {
    //runTest("lowBattery", examples1F(), ::lowBattery)
    //runTest("gooseFinder", examples2G(), ::gooseFinder)
    runTest("pathForWALLE", examples3H(), ::pathForWALLE)
    //runTest("logRecovery", examples4I(), ::logRecovery)
}

private fun examples1F(): Array<Pair<String, String>>{
    return arrayOf(
        Pair("3\n" +
                "1 4 5",
            "10"),
        //Pair("", "")
    )
}
private fun examples2G(): Array<Pair<String, String>>{
    return arrayOf(
        Pair("3 1\n" +
                "android\n" +
                "gooseberries are very tasty\n" +
                "ios\n" +
                "the goose\n" +
                "android\n" +
                "goose is the coolest",
            "2\n" +
                    "android\n" +
                    "ios"),
        Pair("3 2\n" +
                "android\n" +
                "gooseberries are very tasty\n" +
                "ios\n" +
                "the goose\n" +
                "android\n" +
                "goose is the coolest",
            "0"),
        //Pair("", "")
    )
}
private fun examples4I(): Array<Pair<String, String>>{
    return arrayOf(
        Pair("4 3\n" +
                "a b c d\n" +
                "a\n" +
                "a b\n" +
                "b c\n" +
                "a c",
            "1"),
        Pair("5 5\n" +
                "main reader webpage profile alice\n" +
                "main\n" +
                "main reader\n" +
                "reader main\n" +
                "webpage profile\n" +
                "profile alice\n" +
                "alice main",
            "3"),
        //Pair("", ""),
    )
}
private fun examples3H(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("113122321", "5"),
        Pair("11122112231", "6"),
        Pair("1", "1"),
        Pair("12", "2"),
    )
}

private fun runTest(nameFun: String, examples: Array<Pair<String, String>>, function: () -> Unit){
    println(" $nameFun")
    repeat(examples.size) {
        writeExample(input = examples[it].first, correctAnswer = examples[it].second)

        function()
        val answer = readFile(OUTPUT)
        val correctAnswer = readFile(CORRECT_ANSWER)
        println(answer == correctAnswer)
        //println(readFile(OUTPUT))
    }
}
private fun writeExample(input: String, correctAnswer: String){
    writeFile(input, INPUT)
    writeFile(correctAnswer, CORRECT_ANSWER)
    writeFile("DEFAULT", OUTPUT)
}