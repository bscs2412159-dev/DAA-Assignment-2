import java.util.*;

public class Question2BoyerMoore {
    private static final int ALPHABET_SIZE = 256;

    private static void badCharHeuristic(String pattern, int[] badChar) {
        Arrays.fill(badChar, -1);
        for (int i = 0; i < pattern.length(); i++) {
            badChar[pattern.charAt(i)] = i;
        }
    }

    public static void search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        int[] badChar = new int[ALPHABET_SIZE];
        badCharHeuristic(pattern, badChar);

        int shift = 0;
        while (shift <= (n - m)) {
            int j = m - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }

            if (j < 0) {
                System.out.println("Pattern found at index " + shift);
                shift += (shift + m < n) ? m - badChar[text.charAt(shift + m)] : 1;
            } else {
                shift += Math.max(1, j - badChar[text.charAt(shift + j)]);
            }
        }
    }

    public static void main(String[] args) {
        String text = "Insertion sort typically has a smaller constant factor than merge sort";
        String pattern = "sort";

        System.out.println("\nText: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Matches:");
        search(text, pattern);
    }
}
