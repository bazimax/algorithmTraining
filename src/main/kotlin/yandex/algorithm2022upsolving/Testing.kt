package yandex.algorithm2022upsolving

import yandex.*

fun main(args: Array<String>) {
    //runTest("checkeredBoard", examplesA(), ::checkeredBoard)
    //runTest("almostSquareNumbers", examplesC(), ::almostSquareNumbers)
    runTest("unusualDivision", examplesB(), ::unusualDivision)
    //runTest("maxAndAminoAcidSynthesis", examplesD(), ::maxAndAminoAcidSynthesisV2)
}
private fun examplesA(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("8 2",
            "Yes\n" +
                    "1 2 1 2 1 2 1 2\n" +
                    "2 1 2 1 2 1 2 1\n" +
                    "1 2 1 2 1 2 1 2\n" +
                    "2 1 2 1 2 1 2 1\n" +
                    "1 2 1 2 1 2 1 2\n" +
                    "2 1 2 1 2 1 2 1\n" +
                    "1 2 1 2 1 2 1 2\n" +
                    "2 1 2 1 2 1 2 1"),
        Pair("2 1", "No"),
        Pair("3 3",
            "Yes\n" +
                    "1 3 2\n" +
                    "3 2 1\n" +
                    "2 1 3"),
        Pair("1 3", "No"),
        Pair("5 3", "No"),
        Pair("1 1",
            "Yes\n" +
                    "1"),
        Pair("5 3", "No"),
        Pair("7 7",
            "Yes\n" +
                    "1 7 2 4 5 3 6\n" +
                    "3 6 1 7 2 5 4\n" +
                    "5 4 3 6 1 2 7\n" +
                    "2 7 5 4 3 1 6\n" +
                    "1 6 2 7 5 3 4\n" +
                    "3 4 1 6 2 5 7\n" +
                    "5 7 3 4 1 2 6"),
        //Pair("12", "2"),
    )
}
private fun examplesB(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("4\n" +
                "10 1\n" +
                "36 3\n" +
                "11 2\n" +
                "1000000000000000000 7",
            "10\n" +
                    "6\n" +
                    "4\n" +
                    "262143"),
        //Pair("12", "2"),
    )
}
private fun examplesC(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("98", "2"),
        Pair("10", "3"),
        Pair("6", "1"),
        Pair("4", "2"),
        Pair("2022", "2"),
        //Pair("12", "2"),
    )
}
private fun examplesD(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("3 5\n" +
                "GCAGT\n" +
                "ACAGA\n" +
                "GCAGA",
            "6"),
        Pair("8 12\n" +
                "CGGCCGCAACTC\n" +
                "GGATGTTTGCTG\n" +
                "TTGAAACGTACG\n" +
                "GCTGCGGGGGTC\n" +
                "AGGTACTCACAA\n" +
                "ACATATTTCTGG\n" +
                "GGAGTGGAGGTC\n" +
                "AACACGTAGCCC",
            "72"),
        //Pair("12", "2"),
    )
}

private fun examplesX(): Array<Pair<String, String>> {
    return arrayOf(
        //Pair("12", "2"),
    )
}

private fun runTest(nameFun: String, examples: Array<Pair<String, String>>, function: () -> Unit){
    println(" $nameFun")
    repeat(examples.size) {
        writeExample(input = examples[it].first, correctAnswer = examples[it].second)

        function()
        val answer = readFile(OUTPUT_FILE)
        val correctAnswer = readFile(CORRECT_ANSWER_FILE)
        println(answer == correctAnswer)
        //println(readFile(OUTPUT))
    }
}
private fun writeExample(input: String, correctAnswer: String){
    writeFile(input, INPUT_FILE)
    writeFile(correctAnswer, CORRECT_ANSWER_FILE)
    writeFile("DEFAULT", OUTPUT_FILE)
}
