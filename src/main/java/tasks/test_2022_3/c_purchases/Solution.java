package tasks.test_2022_3.c_purchases;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Внимание - подвязаны файлы в папке resources
 *
 * Вместо writer будем выводить на консоль,
 * но возможность вывода в файл оставляем
 *
 * jsonString = scanner.nextLine()
 * для парсинга json забираем первую строку из input.txt
 *
 * (класс проверен примером)
 */
public class Solution {
    private static String linkInput;
    private static String linkOutput;
    private static String jsonString;

    /**
     * Здесь проводим первичные файловые операции
     * и забираем строку json из файла для
     * дальнейшего разбора
     */
    private static void fileOperation() throws IOException {
        linkInput = Solution.class.
                getResource("/test_2022_3_c/input.txt").getPath();
        linkOutput = Solution.class.
                getResource("/test_2022_3_c/output.txt").getPath();

        FileReader reader = new FileReader(linkInput);
        // FileWriter writer= new FileWriter(linkOutput);
        Scanner scanner = new Scanner(reader);
        jsonString = scanner.nextLine();
    }

    private static String nameContains;
    private static int priceGreaterThan;
    private static int priceLessThan;
    private static Date dateBefore;
    private static Date dateAfter;
    private static DateFormat dateParser = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Работаем со свойствами из входящего файла
     */
    public static void workProperty() throws IOException, java.text.ParseException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(linkInput));
        nameContains = properties.getProperty("NAME_CONTAINS");
        priceGreaterThan = Integer.parseInt(properties.getProperty("PRICE_GREATER_THAN"));
        priceLessThan = Integer.parseInt(properties.getProperty("PRICE_LESS_THAN"));

        String dateB = properties.getProperty("DATE_BEFORE");
        dateBefore = dateParser.parse(dateB);
        String dateA = properties.getProperty("DATE_AFTER");
        dateAfter = dateParser.parse(dateA);
    }


    /**
     * Обработка json строки json.simple
     * Сравнение результатов с Property
     *
     * Не забываем подключить в pom
     *
     * В input вначале квадратные скобки, значит начинаем парсить с массива
     * JSONArray array = (JSONArray) parser.parse(jsonString);
     */
    public static void workJson() throws ParseException, java.text.ParseException {
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(jsonString);
        List<JSONObject> list = new ArrayList<>();
        for (Object object: array) {
            JSONObject jsonObject = (JSONObject) object;
            long id = (long)jsonObject.get("id");
            String name = (String)jsonObject.get("name");
            String lowerName = name.toLowerCase(Locale.ROOT);
            long price = (long)jsonObject.get("price");
            Date date = dateParser.parse((String)jsonObject.get("date"));

            if (lowerName.contains(nameContains)
                && (price >= priceGreaterThan && price <= priceLessThan)
                && (date.before(dateBefore) || date.equals(dateBefore))
                && (date.after(dateAfter) || date.equals(dateAfter))) {
                list.add(jsonObject);
            }
        }
        list.sort(Comparator.comparing(o -> o.get("id").toString()));
        System.out.println(list);
    }

    public static void main(String[] args) throws Exception {
        fileOperation();
        workProperty();
        workJson();
    }
}