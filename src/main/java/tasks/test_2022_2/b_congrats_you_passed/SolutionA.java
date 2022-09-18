package tasks.test_2022_2.b_congrats_you_passed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *  limitVacancies - названия вакансий
 *  и максимальное количество кандидатов на каждую вакансию
 *
 *  infoCandidates - название вакансии
 *  и список кандидатов и их успехи
 *  (класс протестирован на примерах и работает)
 */
public class SolutionA {
    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
    private static final Map<String, Byte> limitVacancies = new HashMap<>();
    private static final Map<String, ArrayList<Info>> infoCandidates = new HashMap<>();

    /**
     * Класс для хранения информации о кандидате
     * name - имя
     * numTask - количество сделанных задач
     * penalty - штраф
     */
    public static class Info {
        String name;
        Byte numTask;
        int penalty;

        public Info(String name, Byte numTask, int penalty) {
            this.name = name;
            this.numTask = numTask;
            this.penalty = penalty;
        }
    }

    /**
     * Ввод информации о вакансиях
     * vacancy - информация о вакансии в виде строки с запятыми
     * n - число открытых вакансий - numberOfVacancies;
     * s - название вакансии - jobTitle;
     * m - максимальное число кандидатов - maxCandidates;
     */
    private static void inputInfoAboutVacancies() throws IOException {
        byte numberOfVacancies = Byte.parseByte(reader.readLine());
        for (byte i = 0; i < numberOfVacancies; i++) {
            String vacancy = reader.readLine();
            String [] arr = vacancy.split(",");
            String jobTitle = arr[0];
            byte maxCandidates = Byte.parseByte(arr[1]);
            limitVacancies.put(jobTitle, maxCandidates);
        }
    }

    /**
     * Ввод информации о кандидатах
     * k - число кандидатов - numberOfCandidates
     * candidate - информация о кандидате в виде строки с запятыми
     * c - строковый идентификатор (имя) - nameOfCandidate
     * q - название вакансии - jobTitle
     * r - количество решенных задач - numTask
     * p - штраф - penalty - (кто меньше получил штраф - получает большую оценку)
     * current - добавляем список кандидатов к вакансии
     */
    private static void inputInfoAboutCandidates() throws IOException {
        byte numberOfCandidates = Byte.parseByte(reader.readLine());
        for (byte i = 0; i < numberOfCandidates; i++) {
            String candidate = reader.readLine();
            String[] arr = candidate.split(",");
            String nameOfCandidate = arr[0];
            String jobTitle = arr[1];
            byte numTask = Byte.parseByte(arr[2]);
            int penalty = 1000 - Integer.parseInt(arr[3]);

            ArrayList<Info> current =
                    infoCandidates.getOrDefault(jobTitle, new ArrayList<>());
            current.add(new Info(nameOfCandidate, numTask, penalty));
            infoCandidates.put(jobTitle, current);
        }
    }

    /**
     * Выбор кандидатов
     * accepted - допущенные кандидаты
     *
     * entry - переводим Map в Set
     *
     * list.sort - сортировка объектов Info
     * 1) по количеству решенных задач
     * 2) по штрафу
     *
     * limit - меньшее число из количества
     * вакансий и количества кандидатов
     *
     */
    private static void chooseCandidate() {
        ArrayList<String> acceptedCandidate = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Info>> entry : infoCandidates.entrySet()){
            ArrayList<Info> list = entry.getValue();
            list.sort((o1, o2) -> {
                if (o1.numTask != o2.numTask) {
                    return o2.numTask - o1.numTask;
                }
                if (o1.penalty != o2.penalty) {
                    return o2.penalty - o1.penalty;
                }
                return o2.name.compareTo(o1.name);
            });
            String jobTitle = entry.getKey();
            int limit = Math.min(limitVacancies.get(jobTitle), list.size());
            while (limit-- > 0) {
                acceptedCandidate.add(list.get(limit).name);
            }
        }
        acceptedCandidate.sort(Comparator.naturalOrder());
        for (String element: acceptedCandidate){
            System.out.println(element);
        }
    }

    public static void main(String[] args) throws IOException {
        inputInfoAboutVacancies();
        inputInfoAboutCandidates();
        chooseCandidate();
    }
}