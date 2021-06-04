package util;

import java.util.ArrayList;

public class Expand {
	public static void main(String[] args) {
		String identifierName = "toText";
		String comment = "tet";
		
		System.out.println(canHeuExpand(identifierName, comment));
	}
	public static boolean canHeuExpand(String identifierName, String comment) {
		ArrayList<String> parts = Util.split(identifierName);
		parts = Dic.removePrepositions(String.join(" ", parts));

		ArrayList<String> words = parseComment(comment);
		if (words == null) {
			return false;
		}
		for (String part : parts) {
			if (!in(part, words)) {
				return false;
			}
		}
		return true;
	}

	private static boolean in(String part, ArrayList<String> words) {
		for (String word : words) {
			if (part.equalsIgnoreCase(word)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<String> parseComment(String comment) {
		String result = "";
		char[] chars = comment.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (Util.isLetter(chars[i])) {
				result += chars[i];
			} else {
				result += ' ';
			}
		}
		while (result.contains("  ")) {
			result = result.replace("  ", " ");
		}
		if (result.trim().equals("")) {
			return null;
		}
		ArrayList<String> arrayList = new ArrayList<>();
		for (String string : result.trim().split(" ")) {
			arrayList.add(string);
		}

		return arrayList;
	}
}

