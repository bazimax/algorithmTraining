package leetcode

/**
 * https://leetcode.com/bazimax/
 *
 * Задачи решались в большинстве случаев двигаясь по этому списку - https://www.techinterviewhandbook.org/grind75
 *
 *
 * -------------------------
 * Большинство задач имеют структуру (имя\описание - примеры - решение).
 *
 *
 * 542. 01 Matrix - Medium - https://leetcode.com/problems/01-matrix/description/ -
 * номер и название задачи, сложность, ссылка на задачу
 *
 * val examples (testAsk) - набор тестовых значений (если нет, то простой набор примеров)
 * fun testing (testFun) - функция запускающая тестирование (если нет, то простой набор примеров)
 *
 * //542 > 16:33 - 18:44 (~2ч11мин) > OK (fast 60%, memory 54%) -
 * номер задачи > время начала и конца разработки (и примерное~ общее время) >
 * пройден тест (OK) или нет (notOK) и результат (по скорости, по памяти) >
 * примечание (если есть).
 *
 * fun updateMatrix - сама основная функция (если в конце приписка V2 - то это другой вариант этой же функции).
 *
 * fun walk - доп. функции и т.д.
 * -------------------------
 *
 *
 * Пояснения по некоторым комментариям
 *
 * //IN WORK - в работе
 * //test - отладка
 * //backup - полезная для меня информация, которая в коде не нужна и может быть спокойно удалена.
 * //the right decision, but not mine - правильное, но не мое решение (проверяю и учусь).
 * //not mine decision - изначально не мое решение, которое переработал под себя
 */

//Список для доработки: 15, 110, 238, 239
//while медленнее for; for медленнее forEach (while << for << forEach)