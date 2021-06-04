package FindCasualNames.relation;

import util.Util;

import java.util.ArrayList;

public class Comment extends IdentifierWithoutType {
	private int startLine;
	public Comment(int startLine, int endLine, String content) {
		super(endLine, content); // end line
		this.startLine = startLine;
	}

	public int getStartLine() {
		return startLine;
	}

	public int getEndLine() {
		return getLine();
	}

	@Override
	public String toString() {
		return leaveLetters(getContent());
//		return toOneLineAndReplaceT(getContent());
	}

	public String leaveLetters(String str) {
		String result = "";
		char[] chars = str.toCharArray();
		for (char c :
				chars) {
			if (Util.isLetter(c)) {
				result += c;
			} else {
				result += " ";
			}
		}
		while (result.contains("  ")) {
			result = result.replaceAll("  ", " ");
		}
		return result.trim();
	}
	
	public String toOneLineAndReplaceT(String str) {
		str = str.replaceAll("\t", " ");
		// filter out / and *
		String[] lines = str.split("\n");

		ArrayList<String> arrayList = new ArrayList<>();
		for (String line :
				lines) {
			line = removeStart(line, '/');
			line = removeStart(line, '*');
			line = removeEnd(line, '/');
			line = removeEnd(line, '*');
			line = removeEnd(line, '.');
			if (line.trim().length() != 0) {
				arrayList.add(line);
			}
		}
		if (arrayList.size() == 0) {
			return "";
		}
		return String.join(". ", arrayList);
	}

	private String removeStart(String str, char c) {
		str = str.trim();
		char[] chars = str.toCharArray();
		int i = 0;
		for (; i < chars.length; i++) {
			if (chars[i] != c) {
				break;
			}
		}
		return str.substring(i).trim();
	}

	public static String removeEnd(String str, char c) {
		str = str.trim();
		char[] chars = str.toCharArray();
		int j = chars.length-1;
		for (; j >= 0; j--) {
			if (chars[j] != c) {
				break;
			}
		}
		return str.substring(0, j+1).trim();
	}
}
