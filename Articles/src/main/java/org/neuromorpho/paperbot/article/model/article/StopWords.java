package org.neuromorpho.paperbot.article.model.article;

import java.util.*;

public class StopWords {

    private String[] defaultStopWords = {"i", "a", "about", "an", "and",
            "are", "as", "at", "be", "by", "com", "for", "from", "how",
            "in", "is", "it", "of", "on", "or", "that", "the", "this",
            "to", "was", "what", "when", "where", "who", "will", "with", 
            "…", "(", ")", ":", ",", "/", "'"};

    private static HashSet stopWords = new HashSet();

    public StopWords() {
        stopWords.addAll(Arrays.asList(defaultStopWords));
    }

    public List<String> removeStopWords(String string) {
        StringTokenizer tokens = new StringTokenizer(string.toLowerCase());
        List<String> result = new ArrayList<>();
        while (tokens.hasMoreElements()) {
            String word = tokens.nextToken();
            if (!stopWords.contains(word)) {
                result.add(word);
            }
        }
        return result;
    }
}
