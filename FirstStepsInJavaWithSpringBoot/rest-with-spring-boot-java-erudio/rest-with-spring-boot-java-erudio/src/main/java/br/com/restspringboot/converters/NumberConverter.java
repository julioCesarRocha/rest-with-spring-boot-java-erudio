package br.com.restspringboot.converters;

import static java.util.Objects.isNull;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) {
        if (isNull(strNumber)) return 0D;

        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if (isNull(strNumber)) return false;

        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
