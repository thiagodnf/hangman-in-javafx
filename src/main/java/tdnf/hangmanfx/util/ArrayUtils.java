package tdnf.hangmanfx.util;

public class ArrayUtils {

    public static String[] replace(String[] array, String letter) {

        String[] copy = new String[array.length];

        for (int i = 0; i < array.length; i++) {

            if (array[i].isBlank()) {
                copy[i] = " ";
            } else {
                copy[i] = letter;
            }
        }

        return copy;
    }

    public static String join(String[] array, String character) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {

            builder.append(array[i]);

            if (i + 1 != array.length) {
                builder.append(character);
            }
        }

        return builder.toString();

    }

    public static boolean equals(String[] target, String[] current) {

        if (target.length != current.length) {
            return false;
        }

        for (int i = 0; i < target.length; i++) {

            if (!target[i].equals(current[i])) {
                return false;
            }
        }

        return true;
    }

}
