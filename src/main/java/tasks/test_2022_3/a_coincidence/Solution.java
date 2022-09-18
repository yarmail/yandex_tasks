package tasks.test_2022_3.a_coincidence;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * P - plagiarism - плагиат
 * S - suspicious - подозрительный
 * I - innocent - невиновный
 * Выражение
 * charAlis[i] = (char) ((int) charAlis[i] | 32)
 * делает из большой буквы маленькую
 * (как я понимаю, сложение битовых массивов)
 */
public class Solution {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void check(String stringAlis, String stringZeliboba) {
        char[] charAlis = stringAlis.toCharArray();
        char[] charZeliboba = stringZeliboba.toCharArray();
        int length = charAlis.length;
        char[] result = new char[length];

        // "plagiarism" check
        for (int i = 0; i < length; i++) {
            if (charZeliboba[i] == charAlis[i]) {
                result[i] = 'P';
                charAlis[i] = (char) ((int) charAlis[i] | 32);
            }
        }

        // "innocent" check
        for (int i = 0; i < length; i++) {
            if (result[i] != 'P') {
                for (int j = 0; j < charAlis.length; j++) {
                    if (charZeliboba[i] == charAlis[j]) {
                        charAlis[j] = (char) ((int) charAlis[j] | 32);
                        result[i] = 'S';
                        break;
                    }
                }
            }
        }

        // "suspicious" check
        for (int i = 0; i < length; i++) {
            if (result[i] == '\0') {
                result[i] = 'I';
            }
        }

        for (char c : result) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        check("CLOUD", "CUPID");
        check("ALICE", "ELIBO");
        check("ABCBCYA", "ZBBACAA");
    }
}