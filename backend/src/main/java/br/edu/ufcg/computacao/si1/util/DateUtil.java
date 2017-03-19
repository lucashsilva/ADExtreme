package br.edu.ufcg.computacao.si1.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by gustavo on 19/03/17.
 */
public class DateUtil {
    public static LocalDate getDate(String data) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, format);
    }
}
