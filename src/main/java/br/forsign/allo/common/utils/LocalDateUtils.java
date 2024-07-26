package br.forsign.allo.common.utils;

import br.forsign.allo.common.error.BusinessException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@UtilityClass
public class LocalDateUtils {

    public static final long DAY_MILLISECONDS = 86_400_000L;

    public static final String ISO_DATE = "yyyy-MM-dd";

    public static final String BR_DATE_PATTERN = "dd/MM/yyyy";

    public static final String BR_SHORT_DATE_PATTERN = "ddMMyy";

    public static final String BR_MONTH_YEAR_PATTERN = "MM/yyyy";

    public static final String BR_DAY_MONTH_PATTERN = "dd/MM";

    public static final String BR_DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

    public static final String NUMBERS_ONLY_DATE_PATTERN = "yyyyMMdd";

    public static final String NUMBERS_ONLY_BR_DATE_PATTERN = "ddMMyyyy";

    public static final String NUMBER_ONLY_BR_MONTH_YEAR_DATE_PATTERN = "MMyyyy";

    public static final String NUMBERS_ONLY_DATETIME_PATTERN = "yyyyMMddHHmmss";

    public static final String NUMBERS_ONLY_TIME_PATTERN = "HHmmss";

    public LocalDate fromExcelDate(String stringValue){
        try{
            double value = Double.parseDouble(stringValue);
            int wholeDays = (int) Math.floor(value);

            int startYear = 1900;
            int dayAdjust = -1; // Excel thinks 2/29/1900 is a valid date, which it isn't
            if (wholeDays < 61) {
                // Date is prior to 3/1/1900, so adjust because Excel thinks 2/29/1900 exists
                // If Excel date == 2/29/1900, will become 3/1/1900 in Java representation
                dayAdjust = 0;
            }
            return LocalDate.of(startYear, 1, 1).plusDays((long) wholeDays + dayAdjust - 1);
        }catch(Exception e){
            throw new BusinessException("Data inválida. Formato esperado: DD/MM/AAAA");
        }
    }

    public String toBrazilianDateString(LocalDate data){
        return LocalDateUtils.format(data, BR_DATE_PATTERN);
    }


    public String toBrazilianDateTimeString(LocalDateTime data){
        return LocalDateUtils.format(data, BR_DATETIME_PATTERN);
    }

    public String toBrazilianDateString(LocalDateTime date){
        return LocalDateUtils.format(date, BR_DATE_PATTERN);
    }

    public String toDateNumbersOnly(LocalDate data){
        return LocalDateUtils.format(data, NUMBERS_ONLY_DATE_PATTERN);
    }

    public String toDateNumbersOnly(LocalDateTime data){
        return LocalDateUtils.format(data, NUMBERS_ONLY_DATETIME_PATTERN);
    }

    public String toDateNumbersBROnly(LocalDate data){
        return LocalDateUtils.format(data, NUMBERS_ONLY_BR_DATE_PATTERN);
    }

    public static String toDateNumbersBRMonthYearOnly(LocalDate data) {
        return LocalDateUtils.format(data, NUMBER_ONLY_BR_MONTH_YEAR_DATE_PATTERN);
    }

    public LocalDate fromBrazilianDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BR_DATE_PATTERN);
        return LocalDate.parse(data, formatter);
    }

    public LocalDate fromBrazilianShortDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BR_SHORT_DATE_PATTERN);
        return LocalDate.parse(data, formatter);
    }

    public String toTimeNumbersOnly(LocalDateTime data){
        return LocalDateUtils.format(data, NUMBERS_ONLY_TIME_PATTERN);
    }

    private String format(LocalDate date, String pattern) {
        if(date == null)
            return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    private String format(LocalDateTime date, String pattern) {
        if(date == null)
            return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    public LocalDateTime toDataInicial(LocalDate data){
        if(data == null) return null;

        return data.atTime(00,00);
    }

    public LocalDateTime toDataFinal(LocalDate data){
        if(data == null) return null;

        return data.atTime(23, 59, 59);
    }

    public LocalDate fromNumbersOnly(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(NUMBERS_ONLY_DATE_PATTERN);
        return LocalDate.parse(data, formatter);
    }

    public LocalDate fromBRNumbersOnly(String data){
        if(data.length() == 7)
            data = "0".concat(data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(NUMBERS_ONLY_BR_DATE_PATTERN);
        return LocalDate.parse(data, formatter);
    }

    public static String toBrazilianDateDayMonthString(LocalDate data) {
        return LocalDateUtils.format(data, BR_DAY_MONTH_PATTERN);
    }

    public LocalDate fromIsoDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO_DATE);
        return LocalDate.parse(data, formatter);
    }

    public static String toMonthYearString(LocalDate data) {
        return LocalDateUtils.format(data, BR_MONTH_YEAR_PATTERN);
    }
}

