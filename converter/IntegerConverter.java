package converter;


public class IntegerConverter {
    private final int sourceRadix;
    private final int sourceNumber;
    private final int targetRadix;
    private int convertedNumber;

    public IntegerConverter(int sourceRadix, int sourceNumber, int targetRadix) {
        this.sourceRadix = sourceRadix;
        this.sourceNumber = sourceNumber;
        this.targetRadix = targetRadix;
        convertedNumber = 0;
    }

    public void convert() {
        convertedNumber = convertToDecimal();
        printDecimalInTargetRadix();
    }

    public int convertToDecimal() {
        if (sourceRadix == 10) {
            return sourceNumber;
        } else if (sourceRadix == 1) {
            return convertRadixOneToDecimal();
        }
        return Integer.parseInt(Integer.toString(sourceNumber), sourceRadix);
    }

    public int convertRadixOneToDecimal() {
        return Integer.toString(sourceNumber).length();
    }

    public void printDecimalInTargetRadix() {
        if (targetRadix == 10) {
            System.out.println(sourceNumber);
        } else if (targetRadix == 1) {
            System.out.println(convertDecimalToRadixOne());
        } else {
            String output = Integer.toString(sourceNumber, targetRadix);
            System.out.println(output);
        }
    }

    public int convertDecimalToRadixOne() {
        return Integer.parseInt("1".repeat(Math.max(0, sourceNumber)));
    }


}
