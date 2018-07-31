package com.mbresnan.chapter1;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class ChapterOne {

    public ChapterOne() {
    }

    // 1.1 Implement an algo to determine if a string has all unique characters.
    // What if you cannot use additional data structures?
    public static boolean isUnique(String str) {
        // O(n) solution is to create a hashmap of visited characters while looping through the string
        // With no additional data structures though, will have to be more inefficient. Following is O(n^2):

        for (int i = 0; i < str.length() - 1; i++) {
            if (str.indexOf(str.charAt(i), i + 1) != -1) {
                return false;
            }
        }
        return true;

        // Even better, if we can modify the input str, we can sort the string(n log n), then do a linear check
        // Won't do here yet because sorting comes later :)
    }

    // 1.2 Given two strings, write a method to determine if one is a permutation of the other
    public static boolean isPermutation(String str0, String str1) {
        // First, return if lengths are not equal. Or empty strings.
        if (str0.length() != str1.length() || str0.length() == 0) {
            return false;
        }
        // Next, lets make a character array, sort it, and compare linearly
        // Method args passed by value, not reference, so we can modify input safely SAVE THE BYTES
        // Though we do still have to create a temp variable to store the character array;
        char charArrayStr0[] = str0.toCharArray();
        char charArrayStr1[] = str1.toCharArray();

        Arrays.sort(charArrayStr0);
        Arrays.sort(charArrayStr1);


        str0 = new String(charArrayStr0);
        str1 = new String(charArrayStr1);

        // Compare sorted strings
        return str0.equals(str1);

        // Time complexity: O(n log n) on the sorts, O(n) on the comparison
        // The assumption was made that case and whitespace were unimportant
        // However it's simple enough to do with a String.toLowerCase() call and some whitespace regex.
    }

    // 1.3 Write a method to replace all spaces in a string with "%20". You may assume the string has sufficient
    // space to hold the additional characters, and you are given the "true" length of the string.
    public static String urlify(String str) {
        // The book wants me to use a char array, but it's simpler to do it via regex.
        // At least, it is for the example input, which seems to be very specifically formatted, so not too concerned with
        // the edge cases
        return str.replaceAll("\\b\\s+\\b", "%20");
    }

    // 1.4 Given a string, write a function to check if it is a permutation of a palindrome
    public static boolean palindromePermutation(String str) {
        // Initial thought here is to create a hashmap with a count of each letter
        // If the string is even length, all char counts should be divisible by 2.
        // If odd len, only 1 char can have an odd length
        HashMap chars = new HashMap();
        str = str.toLowerCase();

        // Populate the map
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);

            // If it's not whitespace, add to map
            if (!String.valueOf(letter).equals(" ")) {
                int newCount = chars.containsKey(letter) ? (int) chars.get(letter) + 1 : 1;
                chars.put(letter, newCount);
            }

        }

        Object[] values = chars.values().toArray();
        boolean hasSeenOdd = false;

        for (int j = 0; j < values.length; j++) {
            boolean valueIsOdd = (int) values[j] % 2 != 0;
            if (valueIsOdd) {
                if (hasSeenOdd) {
                    return false;
                }
                hasSeenOdd = true;
            }
        }

        return true;

        // This works in O(n) time, though it does take up more space than I'd like.
        // I feel there are improvements here to make, but it's dinner time, so I'll come back to this one(maybe).
    }

    // 1.5 There are three types of edits that can be performed on strings: insert a character, remove a character,
    // or replace a character. Given two strings, write a function to check if they are zero or one edit away
    // pale, ple = true | pales, pale = true | pale, bale = true | pale, bake = false
    public static boolean isOneAway(String str0, String str1) {
        if (str0 == null || str1 == null || Math.abs(str0.length() - str1.length()) > 1) {
            return false;
        }

        if (str0.length() == str1.length()) {
            // Replace case
            // Both strings same length; Iterate through the strings, max 1 difference
            int diffCounter = 0;
            for (int i = 0; i < str0.length(); i++) {
                if (str0.charAt(i) != str1.charAt(i)) {
                    diffCounter++;
                    if (diffCounter > 1) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            // Insert or delete case
            // Always treat as the delete case since they are inverse of each other
            // Attempt deletion on every letter and see if they match

            String string0 = str0;
            String string1 = str1;
            if (string0.length() > string1.length()) {
                String tempStr = string0;
                string0 = string1;
                string1 = tempStr;
            }

            int idx0 = 0;
            int idx1 = 0;
            while (idx0 < string0.length() && idx1 < string1.length()) {
                if (string0.charAt(idx0) != string1.charAt(idx1)) {
                    if (idx0 != idx1) {
                        return false;
                    }
                    idx1++;
                } else {
                    idx0++;
                    idx1++;
                }
            }
            return true;
        }
        // Time & space complexity is O(n)
    }

    // 1.6 Implement a method to perform basic string compression using the counts of repeated chars.
    // Ex. aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than
    // the original string, return the original string. You can assume the string has only uppercase and lowercase letters
    public static String strCompression(String str) {
        if (str.length() <= 2) {
            return str;
        }

        String compStr = "";
        char currentLetter = str.charAt(0);
        int count = 1;


        // iterate through the string. If char is same as before, increment count, otherwise append to the result
        int i = 1;
        while (i < str.length()) {
            char newLetter = str.charAt(i);

            if (newLetter == currentLetter) {
                count++;
            } else {
                compStr += Character.toString(currentLetter) + count;
                currentLetter = newLetter;
                count = 1;
            }
            i++;
        }

        // We've finished the iteration, add whatever letter and count we have to the result
        compStr += Character.toString(currentLetter) + count;


        return compStr.length() < str.length() ? compStr : str;
        // Time complexity O(n)
    }

    // 1.7 Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
    // write a method to rotate the image by 90 degrees. Can you do this in place?
    // [[1, 2, 3]
    //  [4, 5, 6]
    //  [7, 8, 9]]

    // [[1, 1, 1, 1]
    //  [2, 2, 2, 2]
    //  [3, 3, 3, 3]
    //  [4, 4, 4, 4]

    // [[1, 1, 1, 1, 1]
    //  [2, 2, 2, 2, 2]
    //  [3, 3, 3, 3, 3]
    //  [4, 4, 4, 4, 4]
    //  [5, 5, 5, 5, 5]

    // [[1, 2, 3]
    //  [4, 5, 6]
    //  [7, 8, 9]]

    public static void printMat(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void printTemp(int[] temp) {
        for (int j = 0; j < temp.length; j++) {
            System.out.print(temp[j] + " ");
        }
        System.out.println();
        System.out.println();
    }

    public static int[][] rotateMatrix(int[][] matrix) {
        // So we need to rotate the layers. A layer would be the outer most values in the above example, but
        // we may have many layers with a larger N. We need to rotate by swapping values within the layer,
        // then do the same for the inner layers until we've swapped every layer.
        if (matrix.length <= 1) {
            return matrix;
        }

        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            int startIndex = i;
            int endIndex = n - 1 - i;
            for (int j = startIndex; j < endIndex; j++) {
                int offset = j - startIndex;
                // Save top value
                int temp = matrix[startIndex][j];

                // Left value to top value
                matrix[startIndex][j] = matrix[endIndex - offset][startIndex];

                // Bottom value to left value
                matrix[endIndex - offset][startIndex] = matrix[endIndex][endIndex - offset];

                // Right value to bottom value
                matrix[endIndex][endIndex - offset] = matrix[j][endIndex];

                // Top value to right value
                matrix[j][endIndex] = temp;
            }
        }

        return matrix;
        // Time complexity is O(n^2) and I don't think you can do any better
    }

    // 1.8 Write an algo such that if an element in an MxN matrix is 0, its entire row and column are set to 0
    public static int[][] zeroMatrix(int[][] matrix) {
        // Iterate through the matrix. Note rows and columns of zero, then replace values with 0
        int m = matrix.length;
        int n = matrix[0].length;
        HashMap rows = new HashMap();
        HashMap columns = new HashMap();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows.put(i, true);
                    columns.put(j, true);
                }
            }
        }

        // Replace rows
        for (Object key : rows.keySet()) {
            for (int i = 0; i < n; i++) {
                matrix[(int) key][i] = 0;
            }
        }

        // Replace columns
        for (Object key : columns.keySet()) {
            for (int i = 0; i < m; i++) {
                matrix[i][(int) key] = 0;
            }
        }

        return matrix;
        // Feels like too many for loops. Not sure if they're replaceable though. We need to loop and get the rows/columns
        // We also need to loop to replace the values.
        // Also, the hashmap may or may not be the most efficient structure to store our rows/columns
        // Time complexity is O(n^2)
    }

    // BONUS!!!
    // Facebook interview question:
    // Given an encoded string such as '12' where each letter maps to a number e.g. 'a' == 0 'b' == 1 etc
    // Write a function that given an 'encoded' string, return the number of ways it can be decoded.
    public static int numberOfDecodes(String data) {
         if (data.length() <= 1) {
            return 1;
        } else {
            int combinationNum = Integer.parseInt(String.valueOf(data.charAt(0)) + String.valueOf(data.charAt(1)));
            if(combinationNum >= 0 && combinationNum <= 25) {
                return  numberOfDecodes(data.substring(1)) + numberOfDecodes(data.substring(2));
            }
            return numberOfDecodes(data.substring(1));
        }
    }

    // One optimization we could make is add a second param to this function such that we pass the number of chars
    // to parse in the string e.g. numberOfDecodes(data, data.length - k) where k is a decreasing value corresponding to
    // number of chars left in the string yet to be parsed. This would save a bit on memory as we don't need to create
    // substrings.
}











