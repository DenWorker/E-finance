package ru.denvip700.telegram_bot.algoritms;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class ForJob {
    public static void main(String[] args) {

        int[] arr1 = {100, 100, 1, 2, 2, 2, 2, 1, 11};
        System.out.println(findFirstUniqueElement(arr1));

        /////////////////////////////////////////////////////////////

        String str1 = "MVAAVM";
        String str2 = "MVAVM";
        String str3 = "GGGGG";
        String str4 = "GGGAGGG";

        System.out.println(isPalindrome(str1));
        System.out.println(isPalindrome(str2));
        System.out.println(isPalindrome(str3));
        System.out.println(isPalindrome(str4));

        /////////////////////////////////////////////////////////////

        int[] arr2 = {-7, -3, 0, 2, 5};
        System.out.println(Arrays.toString(sortedSquares(arr2)));

        ///////////////////////////////////////////////////////////

        int[] arr3 = {100, 100, 1, 2, 2, 2, 2, 1, 11};
        printElementsByCount(arr3, 2);

        System.out.println(factorial(5));
        System.out.println(fibonacci(3));

    }

    public static int findFirstUniqueElement(int[] arr) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int j : arr) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        return map.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no elements"))
                .getKey();
    }

    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static int[] sortedSquares(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int pos = n - 1;

        while (left <= right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];

            if (leftSquare > rightSquare) {
                result[pos--] = leftSquare;
                left++;
            } else {
                result[pos--] = rightSquare;
                right--;
            }
        }
        return result;
    }

    public static void printElementsByCount(int[] array, int n) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int value : array) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        map.entrySet().stream()
                .filter(e -> e.getValue() == n)
                .forEach(e -> System.out.println(e.getKey()));
    }

    public static int factorial(int n) {
        return n == 1 ? 1 : n * factorial(n - 1);
    }

    public static int fibonacci(int number) {
        if (number <= 0) return 0;
        if (number == 1) return 1;

        int prev = 1;
        int curr = 1;
        for (int i = 3; i <= number; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
