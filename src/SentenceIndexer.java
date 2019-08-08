import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

class SentenceIndexer {

    private String sentence;
    private String whiteCharactersRegex = "\\s*(,|\\s)\\s*";

    SentenceIndexer(String sentence) {
        this.sentence = sentence;
    }

    void indexSentence() {
        sentence = sentence.toLowerCase();
        String[] wordsArray = sentence
                .split(whiteCharactersRegex);
        Set<String> words = new TreeSet<>(Arrays.asList(wordsArray));
        Map<Character, TreeSet<String>> mapOfCharacters = getMapOfAllCharacters(sentence);
        words.forEach(word -> addWordToMap(mapOfCharacters, word));
        printResult(mapOfCharacters);
    }

    private void addWordToMap(Map<Character, TreeSet<String>> mapOfCharacters, String word) {
        word
                .chars()
                .distinct()
                .forEach(c -> addToMap(mapOfCharacters, word, (char) (c)));
    }

    private void printResult(Map<Character, TreeSet<String>> mapOfCharacters) {
        mapOfCharacters
                .keySet()
                .forEach(key -> System.out.println(key + " : " + mapOfCharacters.get(key)));
    }

    private void addToMap(Map<Character, TreeSet<String>> map, String word, Character c) {
        map.getOrDefault(c, new TreeSet<>()).add(word);
    }

    private Map<Character, TreeSet<String>> getMapOfAllCharacters(String allCharacters) {
        return allCharacters
                .replaceAll(whiteCharactersRegex, "")
                .chars()
                .mapToObj(c -> (char) c)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), i -> new TreeSet<>()));
    }
}
