import java.util.*;

class SentenceIndexer {

    private String sentence;

    SentenceIndexer(String sentence) {
        this.sentence = sentence;
    }

    void indexSentence() {
        sentence = sentence.toLowerCase();
        String[] wordsArray = sentence
                .split("\\s*(,|\\s)\\s*");
        Set<String> words = new TreeSet<>(Arrays.asList(wordsArray));
        Map<Character, TreeSet<String>> mapOfCharacters = getMapOfAllCharacters(sentence);
        words.forEach(
                word ->
                        word.chars().distinct().forEach(c -> addToMap(mapOfCharacters, word, (char) (c)))
        );
        printResult(mapOfCharacters);
    }

    private void printResult(Map<Character, TreeSet<String>> mapOfCharacters) {
        mapOfCharacters
                .keySet()
                .forEach(k -> System.out.println(k + " : " + mapOfCharacters.get(k)));
    }

    private void addToMap(Map<Character, TreeSet<String>> map, String word, Character c) {
        map.getOrDefault(c, new TreeSet<>()).add(word);
    }

    private static Map<Character, TreeSet<String>> getMapOfAllCharacters(String allCharacters) {
        char[] tableOfAllChars = allCharacters
                .replaceAll("\\s*(,|\\s)\\s*", "")
                .toCharArray();
        Map<Character, TreeSet<String>> setOfCharacters = new HashMap<>();
        for (char c : tableOfAllChars) {
            setOfCharacters.put(c, new TreeSet<>());
        }
        return setOfCharacters;
    }
}
