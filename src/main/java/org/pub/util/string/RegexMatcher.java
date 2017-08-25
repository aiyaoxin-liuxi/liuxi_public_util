/*
 * RegexMatcher.java
 */
package org.pub.util.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class RegexMatcher {

    /**
     * Creates a new instance of RegexMatcher
     */
    public RegexMatcher() {
    }

    /**
     * 找出给定的字符串中匹配指定正则表达的所有子串，并返回字串中特定的捕获字符组。
     *
     * @param input 输入字符串。
     * @param regex 需要匹配的正则表达式。
     * @param group 要求返回的捕获字符组
     * @return 符合条件的字符数组
     */
    public static String[] matches(String input, String regex, int group) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        int groups = m.groupCount();
        List<String> matches = new ArrayList<String>();
        if (group < 0 || group > groups) {
            throw new IndexOutOfBoundsException();
        }
        while (m.find()) {
            matches.add(m.group(group));
        }
        return (String[]) matches.toArray(new String[matches.size()]);
    }

    /**
     * 找出给定的字符串中匹配指定正则表达的所有子串，并返回字串中特定的捕获字符组。
     *
     * @param input 输入字符串。
     * @param regex 需要匹配的正则表达式。
     */
    public static String[] matches(String input, String regex) {
        return matches(input, regex, 0);
    }
}
