package converter;

import java.util.Scanner;

public class Launcher {
    private static int sourceRadix;
    private static String number;
    private static int targetRadix;

    public static void launch() {
        if (readInput()) {
            FractionalConverter fc = new FractionalConverter(sourceRadix, number, targetRadix);
            fc.convert();
            System.out.println(fc.getConvertedNumber());
        } else {
            System.out.println("error: wrong input");
        }
    }

    private static boolean readInput() {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return false;
        }
        sourceRadix = scanner.nextInt();
        if (!scanner.hasNext()) {
            return false;
        }
        number = scanner.next();
        if (!scanner.hasNextInt()) {
            return false;
        }
        targetRadix = scanner.nextInt();
        return validateInput();
    }

    private static boolean validateInput() {
        if (sourceRadix > Character.MAX_RADIX || targetRadix > Character.MAX_RADIX) {
            return false;
        } else if (sourceRadix < 1 || targetRadix < 1) {
            return false;
        }
        if (!number.matches("[0-9a-zA-Z.]+")) {
            return false;
        }
        return true;
    }
}
