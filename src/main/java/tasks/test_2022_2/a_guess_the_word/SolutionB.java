package tasks.test_2022_2.a_guess_the_word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class SolutionB {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final static Map<Character, Integer> map = new HashMap<>();

    private static void check(String hiddenWord, String playerAttempt) {
        int length = hiddenWord.length();
        for (int i = 0; i < length; i++) {
            char ch = hiddenWord.charAt(i);
            map.compute(ch, (key, value) -> {
                if(value == null) return 1;
                return ++value;
            });
        }

        String[] result = new String[playerAttempt.length()];
        for (int i = 0; i < length; i++) {
            char ch = playerAttempt.charAt(i);
            if (ch == hiddenWord.charAt(i)) {
                result [i] = "correct";
                reduceVal(ch);
            }
        }

        for (int i = 0; i < length; i++) {
            if (result[i] != null) {
                continue;
            }
            String output = "absent";
            char ch = playerAttempt.charAt(i);
            if (map.containsKey(ch)) {
                output = "present";
                reduceVal(ch);
            }
            result[i] = output;
        }

        for (int i = 0; i < length; i++) {
            System.out.println(result[i]);
        }
    }

    private static void reduceVal(char ch) {
        map.compute(ch, (key, value) -> {
            --value;
            if (value == 0) return null;
            return value;
        });
    }

    public static void main(String[] args) {
        check("COVER", "CLEAR");
        check("ABBA", "AAAA");
        check("ABCBC", "BBACA");
    }
}

/*
Примечания

В данном случае для посчета  количества вхождений
символов используется мапа

hiddenWord - загаданное слово
playerAttempt - попытка игрока угадать это слово

---

check()
length - длина обоих слов и загаданного и проверочного

hiddenWord ... map.compute()
добавляем символы слова в мапу для подсчета
количества вхожений этого символа в слово
При добавлении первый раз значение value будет = 0,
т.к. ещё не добавляли - меняем на 1
Если не 0, делаем +1

result[] - массив для хранения результатов сравнения
correct, present...

if (ch == hiddenWord.charAt(i)) ... reduceVal(ch)
Если есть такие буквы и они на тех же позициях -
уменьшаем из значение в счетчике


---

reduceVal()
Уменьшает количество вхождений сиволов

---
 */