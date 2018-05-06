package com.mbresnan.chapter1.com;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class ChapterOne {

  public ChapterOne() {}

  // 1.1 Implement an algo to determine if a string has all unique characters.
  // What if you cannot use additional data structures?
  public static boolean isUnique(String str) {
    // O(n) solution is to create a hashmap of visited characters while looping through the string
    // With no additional data structures though, will have to be more inefficient. Following is O(n^2):

    for(int i = 0; i < str.length() - 1; i++) {
      if(str.indexOf(str.charAt(i), i + 1) != -1) {
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
    for(int i = 0; i < str.length(); i++) {
      char letter = str.charAt(i);

      // If it's not whitespace, add to map
      if(!String.valueOf(letter).equals(" ")) {
        int newCount = chars.containsKey(letter) ? (int) chars.get(letter) + 1 : 1;
        chars.put(letter, newCount);
      }

    }

    Object[] values = chars.values().toArray();
    boolean hasSeenOdd = false;

    for(int j = 0; j < values.length; j++) {
      boolean valueIsOdd = (int) values[j] % 2 != 0;
      if(valueIsOdd) {
        if(hasSeenOdd) {
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
    if (str0 == null || str1 == null || Math.abs(str0.length() - str1.length()) > 1 ) {
      return false;
    }

    if (str0.length() == str1.length()) {
      // Replace case
      // Both strings same length; Iterate through the strings, max 1 difference
      int diffCounter = 0;
      for(int i = 0; i < str0.length(); i++) {
        if(str0.charAt(i) != str1.charAt(i)) {
          diffCounter++;
          if(diffCounter > 1) {
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
      if(string0.length() > string1.length()) {
        String tempStr = string0;
        string0 = string1;
        string1 = tempStr;
      }

      int idx0 = 0;
      int idx1 = 0;
      while (idx0 < string0.length() && idx1 < string1.length()) {
        if(string0.charAt(idx0) != string1.charAt(idx1)) {
          if(idx0 != idx1) {
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

}











