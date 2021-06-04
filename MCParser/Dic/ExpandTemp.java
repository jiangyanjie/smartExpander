package util;

import java.util.ArrayList;
import java.util.HashSet;

public class ExpandTemp {
	public static void main(String[] args) {
		String identifierName = "toText";
		String comment = "text";
		
		System.out.println(canHeuExpand(identifierName, comment));
	}
	public static boolean canHeuExpand(String identifierName, String comment) {
		ArrayList<String> parts = Util.split(identifierName);
		parts = Dic.removePrepositions(String.join(" ", parts));

		ArrayList<String> words = parseComment(comment);
		if (words == null) {
			return false;
		}
//		HashSet<String> stemsWords = Dic.getStemsWords(words);
//		for (String word : words) {
//			stemsWords.add(word);
//		}
		for (String part : parts) {
			if (!in(part, words)) {
//				if (forFailedToken(part, words)) {
//					continue;
//				} else {
//					result = false;
//					break;
//				}
				return false;
			}
		}
//		if (result == false && identifierName.contains("get")) {
//			result = true;
//			identifierName = identifierName.replace("get", "return");
//			ArrayList<String> parts2 = Util.split(identifierName);
//			for (String part : parts2) {
//				if (!in(part, words)) {
//					result = false;
//					break;
//				}
//			}
//		}
		return true;
	}
	
	private static boolean forFailedToken(String token, ArrayList<String> words) {
		HashSet<String> stems = Dic.findStem(token);
		for (String stem : stems) {
			if (in(stem, words)) {
				return true;
			}
		}
		return false;
	}

	private static boolean in(String part, ArrayList<String> words) {
		for (String word : words) {
			if (part.equalsIgnoreCase(word)) return true;
//			if (Util.isSequence(word, part)) {
//				return true;
//			}
//			if (part.charAt(part.length()-1) == 's' && Util.isSequence(word, part.substring(0, part.length()-1))) {
//				return true;
//			}
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
//			arrayList.addAll(Util.split(string));
			arrayList.add(string);
		}

		return arrayList;
	}
}

