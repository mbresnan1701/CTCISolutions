package com.mbresnan.chapter1.com;

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
}
