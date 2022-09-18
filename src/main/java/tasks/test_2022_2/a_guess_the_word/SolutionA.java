package tasks.test_2022_2.a_guess_the_word;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class SolutionA {
    private  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static void check(String hiddenWord, String playerAttempt) {

        int length = hiddenWord.length();
        short[] countChar = new short[256];
        for (int i = 0; i < length; i++) {
            ++countChar[hiddenWord.charAt(i)];
        }
        String[] answers = new String[hiddenWord.length()];
        for (int i = 0; i < length; i++) {
            if (hiddenWord.charAt(i) == playerAttempt.charAt(i)) {
                answers[i] = "correct";
                --countChar[playerAttempt.charAt(i)];
            }
        }
        for (int i = 0; i < length; i++) {
            if (answers[i] != null) {
                continue;
            }
            if (countChar[playerAttempt.charAt(i)] > 0) {
                answers[i] = "present";
                --countChar[playerAttempt.charAt(i)];
            }
            else {
                answers[i] = "absent";
            }
        }
        for (int i = 0; i < length; i++) {
            System.out.println(answers[i]);
        }
    }

    public static void main(String[] args) {
        check("COVER", "CLEAR");
        check("ABBA", "AAAA");
        check("ABCBC", "BBACA");
    }
}

/* Примечания

hiddenWord - загаданное слово
playerAttempt - попытка игрока угадать это слово

countChar[256] - массив для подсчета количества вхождения каждого символа
(N ячейки = N символа, значение в ячейке = количество)
256 - количество символов в таблице символов

---

check()
++countChar[hiddenWord.charAt(i)];
Каждую букву загаданного слова загоняем в массив
для подсчета количества вхождений

String[] answers - массив для хранения результатов сравнения
correct, present...

--countChar[playerAttempt.charAt(i)];
уменьшем количетво символов если оно на своем месте
"correct" или найднено, но не насвоем "present"

---

main()
Здесь подготавливаем текст для теста
Или получаем его из Scanner
Нужные варианты проверки раскоментировать

 */