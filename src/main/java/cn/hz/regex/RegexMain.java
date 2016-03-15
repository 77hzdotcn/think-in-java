package cn.hz.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMain {

	public static void main(String[] args) {
		String patternStr = "@([3-9]|1\\d?|2[0-6]?)";
		String s = "今天是@19@21@14@4@1@25";
		char[] table = new char[26];
		for (int i = 0; i < 26; i++) {
			table[i] = (char) (i + 65);
		}
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(s);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			String sIndex = matcher.group(1);
			int index = Integer.parseInt(sIndex);
			String rep = "" + table[index - 1];
			matcher.appendReplacement(sb, rep);
		}
		matcher.appendTail(sb);
		System.out.println(sb.toString());
	}

}
