package converter;

public class FractionalConverter {
    private final int sourceRadix;
    private final String number;
    private final int targetRadix;
    private double convertedDecimalNumber;
    private StringBuilder convertedNumber;

    public FractionalConverter(int sourceRadix, String number, int targetRadix) {
        this.sourceRadix = sourceRadix;
        this.targetRadix = targetRadix;
        this.number = number;
        convertedDecimalNumber = 0;
        convertedNumber = new StringBuilder();
    }

    public void convert() {
        numberToDecimal();
        if (number.contains(".")) {
            fractionToDecimal();
        }
        decimalIntegerPartToRadix();
        if (number.contains(".")) {
            decimalFractionToRadix();
        }
    }

    public void numberToDecimal() {
        if (number.contains(".")) {
            String[] split = number.split("\\.");
            integerPartToDecimal(split[0]);
        } else {
            integerPartToDecimal(number);
        }
    }

    public void integerPartToDecimal(String part) {
        if (sourceRadix == 1) {
            convertedDecimalNumber = number.length();
        } else {
            convertedDecimalNumber = Integer.parseInt(part, sourceRadix);
        }
    }

    public void fractionToDecimal() {
        String[] splitFraction = number.split("\\.");
        String fractionPart = splitFraction[1];
        double fractionSum = 0;
        for (int i = 0; i < fractionPart.length(); i++) {
            int digit = Character.digit(fractionPart.charAt(i), sourceRadix);
            fractionSum += (double) digit / Math.pow(sourceRadix, (i + 1));
        }
        convertedDecimalNumber += fractionSum;
    }

    public void decimalIntegerPartToRadix() {
        int decimalIntegerPart = (int) convertedDecimalNumber;
        if (targetRadix == 1) {
            for (int i = 0; i < Integer.parseInt(number); i++) {
                convertedNumber.append(1);
            }
        } else {
            String convertedIntegerPart = Integer.toString(decimalIntegerPart, targetRadix);
            convertedNumber.append(convertedIntegerPart);
        }
    }

    public void decimalFractionToRadix() {
        convertedNumber.append('.');
        double fractionPart = convertedDecimalNumber - ((int) convertedDecimalNumber);
        int digitCount = number.length();
        for (int i = 0; i < digitCount; i++) {
            double result = fractionPart * targetRadix;
            if ((int) result > 9) {
                convertedNumber.append(Character.forDigit((int) result, targetRadix));
            } else {
                convertedNumber.append((int) result);
            }
            fractionPart = result - ((int) result);
        }
    }

    public String getConvertedNumber() {
        String convertedString = convertedNumber.toString();
        String result = "";
        if (number.contains(".")) {
            String[] split = convertedString.split("\\.");
            if (split[1].length() > 5) {
                result += split[0];
                result += ".";
                for (int i = 0; i < 5; i++) {
                    result += split[1].charAt(i);
                }
                return result;
            }
        }
        return convertedString;
    }
}
