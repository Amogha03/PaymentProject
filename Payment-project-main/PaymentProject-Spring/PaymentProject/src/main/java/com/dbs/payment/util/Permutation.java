package com.dbs.payment.util;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
	static List<String> result;

	private static String constructArray(String[] elements, char delimiter) {
		String finalString = "";
		for (int i = 0; i < elements.length; i++) {
			if (i == elements.length - 1) {
				finalString = finalString + elements[i];
			} else {
				finalString = finalString + elements[i] + delimiter;
			}
		}
		return finalString;
	}

	private static void swap(String[] elements, int a, int b) {
		String tmp = elements[a];
		elements[a] = elements[b];
		elements[b] = tmp;
	}

	public static List<String> findPermutations(String[] elements, char delimiter) {
		result = new ArrayList<>();
		int n = elements.length;
		printAllIterativeUtil(n, elements, delimiter);
		return result;
	}

	public static void printAllIterativeUtil(int n, String[] elements, char delimiter) {

		int[] indexes = new int[n];
		for (int i = 0; i < n; i++) {
			indexes[i] = 0;
		}

		result.add(constructArray(elements, delimiter));

		int i = 0;
		while (i < n) {
			if (indexes[i] < i) {
				swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
				result.add(constructArray(elements, delimiter));
				indexes[i]++;
				i = 0;
			} else {
				indexes[i] = 0;
				i++;
			}
		}
	}
}