package com.mbresnan.chapter1.com;

import java.util.Arrays;

public class ChapterOne {

  public ChapterOne() {}

  // 1.1 Implement an algo to determine if a string has all unique characters.
  // What if you cannot use additional data structures?
  public boolean isUnique(String str) {
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
  public boolean isPermutation(String str0, String str1) {
    // First, return if lengths are not equal. Or empty strings.
    if (str0.length() != str1.length() || str0.length() == 0 || str1.length() == 0) {
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
  public String urlify(String str) {
    // The book wants me to use a char array, but it's simpler to do it via regex.
    // At least, it is for the example input, which seems to be very specifically formatted, so not too concerned with
    // the edge cases
    return str.replaceAll("\\b\\s+\\b", "%20");
  }

}
