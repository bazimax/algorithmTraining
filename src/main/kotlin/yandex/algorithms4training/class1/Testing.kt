package yandex.algorithms4training.class1

import yandex.*

fun main(args: Array<String>) {
    //runTest("partition", examplesA(), ::partition) // OK
    //runTest("partition", examplesA(), ::partitionV2) // OK
    //runTest("quickSort", examplesB(), ::quickSortInput) // notOk TL
    //runTest("quickSort", examplesB(), ::quickSortInputB) // Ok
    runTest("quickSort", examplesB(), ::quickSortInputCFilter) // Ok
    //runTest("merger", examplesC(), ::mergerInput, ) // OK
    //runTest("mergeSort", examplesD(), ::mergeSortInput) // OK
    //runTest("bitSorting", examplesE(), ::bitSortingInput, ) // OK
}

private fun examplesA(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("5\n" +
                "1 9 4 2 3\n" +
                "3",
            "2\n" +
                    "3"),
        Pair("0\n" +
                "\n" +
                "0",
            "0\n" +
                    "0"),
        Pair("1\n" +
                "0\n" +
                "0",
            "0\n" +
                    "1"),
        Pair("5\n" +
                "1 9 4 2 3\n" +
                "10",
            "5\n" +
                    "0"),
        Pair("5\n" +
                "8 9 4 5 3\n" +
                "2",
            "0\n" +
                    "5"),
        //Pair("12", "2"),
    )
}
private fun examplesB(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("5\n" +
                "1 5 2 4 3",
            "1 2 3 4 5"),
        Pair("0\n" +
                "",
            ""),
        Pair("5\n" +
                "1 1 1 1 1",
            "1 1 1 1 1"),
        //Pair("12", "2"),
    )
}
private fun examplesC(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("5\n" +
                "1 3 5 5 9\n" +
                "3\n" +
                "2 5 6",
            "1 2 3 5 5 5 6 9"),
        Pair("1\n" +
                "0\n" +
                "0",
            "0"),
        Pair("0\n" +
                "\n" +
                "1\n" +
                "0",
            "0"),
        //Pair("12", "2"),
    )
}
private fun examplesD(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("5\n" +
                "1 5 2 4 3",
            "1 2 3 4 5"),
        Pair("0\n" +
                "",
            ""),
        Pair("5\n" +
                "1 1 1 1 1",
            "1 1 1 1 1"),
        Pair("10\n" +
                "-1 5 1 4 0 3 2 7 9 11",
            "-1 0 1 2 3 4 5 7 9 11"),
        Pair("5\n" +
                "1 0 1000000000 0 1",
            "0 0 1 1 1000000000"),
        //Pair("12", "2"),
    )
}
private fun examplesE(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("9\n" +
                "12\n" +
                "32\n" +
                "45\n" +
                "67\n" +
                "98\n" +
                "29\n" +
                "61\n" +
                "35\n" +
                "09",
            "Initial array:\n" +
                    "12, 32, 45, 67, 98, 29, 61, 35, 09\n" +
                    "**********\n" +
                    "Phase 1\n" +
                    "Bucket 0: empty\n" +
                    "Bucket 1: 61\n" +
                    "Bucket 2: 12, 32\n" +
                    "Bucket 3: empty\n" +
                    "Bucket 4: empty\n" +
                    "Bucket 5: 45, 35\n" +
                    "Bucket 6: empty\n" +
                    "Bucket 7: 67\n" +
                    "Bucket 8: 98\n" +
                    "Bucket 9: 29, 09\n" +
                    "**********\n" +
                    "Phase 2\n" +
                    "Bucket 0: 09\n" +
                    "Bucket 1: 12\n" +
                    "Bucket 2: 29\n" +
                    "Bucket 3: 32, 35\n" +
                    "Bucket 4: 45\n" +
                    "Bucket 5: empty\n" +
                    "Bucket 6: 61, 67\n" +
                    "Bucket 7: empty\n" +
                    "Bucket 8: empty\n" +
                    "Bucket 9: 98\n" +
                    "**********\n" +
                    "Sorted array:\n" +
                    "09, 12, 29, 32, 35, 45, 61, 67, 98"
        ),
        Pair("1\n" +
                "2",
            "Initial array:\n" +
                    "2\n" +
                    "**********\n" +
                    "Phase 1\n" +
                    "Bucket 0: empty\n" +
                    "Bucket 1: empty\n" +
                    "Bucket 2: 2\n" +
                    "Bucket 3: empty\n" +
                    "Bucket 4: empty\n" +
                    "Bucket 5: empty\n" +
                    "Bucket 6: empty\n" +
                    "Bucket 7: empty\n" +
                    "Bucket 8: empty\n" +
                    "Bucket 9: empty\n" +
                    "**********\n" +
                    "Sorted array:\n" +
                    "2"
        ),
        //Pair("12", "2"),
    )
}

private fun examplesX(): Array<Pair<String, String>> {
    return arrayOf(
        //Pair("12", "2"),
    )
}

