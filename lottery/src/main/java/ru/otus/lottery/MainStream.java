package ru.otus.lottery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

/*
https://github.com/vitaly-chibrikov/otus_java_2018_01

удалите строку .map(line -> line.substring(0, line.indexOf("@")))
пишите параметром запуска файл source.csv
запускайте
*/

public class MainStream {
    public static void main(String[] args) throws IOException {
        String salt_otus = "gr_buza\u200B\"учение -\uFEFF свет, а неучение - чуть свет и на работу\"\n" +
                "\n" +
                "Hunan Abrahamyan\u200BЗа\uFEFF державу обидно\n" +
                "\n" +
                "Dmitry Syusin\u200Bна душе всё\uFEFF спокойно)\n" +
                "\n" +
                "Александр Александрович\u200B\"Вы\uFEFF - соль земли\" Иисус\n" +
                "\n" +
                "Филипп Ануфриев\u200Bбалерина крутится лавеха мутится\uFEFF\n" +
                "\n" +
                "Dmitry Lyutenko\u200BShow\uFEFF Must Go ON!!\n" +
                "\n" +
                "Никита Абраменко\u200Bя вообще питонист пожелайте мне удачи на доде по\uFEFF питоне)";

        String salt = "Миллер Фридрих\u200Bхм..я должен\uFEFF выйграть\n" +
                "\n" +
                "Alinka021296\u200Bмногозначительный комментарий\uFEFF\n" +
                "\n" +
                "Hunan Abrahamyan\u200BНе\uFEFF сыпь мне соль на рану\n" +
                "\n" +
                "Миллер Фридрих\u200Bend\uFEFF\n" +
                "\n" +
                "Evgeny Ageev\u200B\"Слабые с доступными,\uFEFF сильные с достойными\"\n" +
                "\n" +
                "Никита Абраменко\u200Bпитон\uFEFF лучше джавы\n" +
                "\n" +
                "Миллер Фридрих\u200Bбольше\uFEFF не вставим";

        Files.lines(Paths.get(args[0]))
                .map(String::trim)
                .map(line -> line.substring(0, line.indexOf("@")))
                .map(line -> line + "\t" + salt)
                .sorted(Comparator.comparingLong(String::hashCode))
                .map(line -> line.hashCode() + "\t" + line.replace(salt, ""))
                .forEach(System.out::println);
    }
}
