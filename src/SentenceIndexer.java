import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class SentenceIndexer {
    private String whiteCharactersRegex = "\\s*(,|\\s)\\s*";
    private String emptyString = "";

    String indexSentence(String sentence) {
        Optional<String> sentenceOptional = Optional.ofNullable(sentence);
        if (sentenceOptional.isPresent()) {
            sentence = sentence.toLowerCase();
            String[] wordsArray = sentence
                    .split(whiteCharactersRegex);
            Set<String> words = new TreeSet<>(Arrays.asList(wordsArray));
            Map<Character, TreeSet<String>> mapOfCharacters = getMapWithAllCharactersAsKey(sentence);
            words.forEach(word -> addWordToMap(mapOfCharacters, word));
            return mapToString(mapOfCharacters);
        } else return emptyString;
    }

    void printIndexedSentence(String sentence) { //TODO Logger ,white chars
        System.out.print(indexSentence(sentence));
    }

    private String mapToString(Map<Character, TreeSet<String>> indexedMap) {
        return indexedMap
                .keySet()
                .stream()
                .map(key -> key + ": " + indexedMap.get(key))
                .collect(Collectors.joining())
                .replaceAll("\\[", emptyString)
                .replaceAll("]", "\n");
    }

    private void addWordToMap(Map<Character, TreeSet<String>> mapOfCharacters, String word) {
        word
                .chars()
                .distinct()
                .forEach(c -> addElementToMap(mapOfCharacters, word, (char) (c)));
    }

    private void addElementToMap(Map<Character, TreeSet<String>> mapOfCharacters, String word, Character c) {
        mapOfCharacters
                .getOrDefault(c, new TreeSet<>())
                .add(word);
    }

    private Map<Character, TreeSet<String>> getMapWithAllCharactersAsKey(String allCharacters) {
        return allCharacters
                .replaceAll(whiteCharactersRegex, emptyString)
                .chars()
                .mapToObj(c -> (char) c)
                .distinct()
                .collect(Collectors.toMap(Function.identity(),
                        i -> new TreeSet<>(),
                        (ghrPrevious, ghrNew) -> ghrNew,
                        TreeMap::new));
    }
}
