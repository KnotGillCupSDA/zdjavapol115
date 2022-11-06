package com.sda.testingadvanced.parametrized;

public class PalindromeChecker {

	/**
	 * @return true if text is a palindrome
	 */
	public static boolean isPalindrome(String text) {
		// abba, kajak
		int i = 0;
		int j = text.length() - 1;

		while (i < text.length() && j >= 0 && i <= j) {
			if (text.charAt(i) != text.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}

		return true;
	}

	public static boolean isPalindromeIwo(String text) {
		String reversedString = "";
		char ch;
		for (int i = 0; i < text.length(); i++) {
			ch = text.charAt(i);
			reversedString = ch + reversedString;
		}
		return reversedString.equals(text);
	}

	public static boolean isPalindromeStringBuilder(String text) {
		return new StringBuilder(text).reverse().toString().equals(text);
	}
}
