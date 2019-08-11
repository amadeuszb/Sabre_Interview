import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class SentenceIndexer {
    private String spaceString = " ";
    private String emptyString = "";
    private String onlyLettersRegex = "[^a-zA-Z\\s+]";

    String indexCharactersToWords(String sentence) {
        return Optional.ofNullable(sentence)
                .map(this::indexCharacters)
                .orElseGet(() -> emptyString);
    }

    private String indexCharacters(String sentence) {
        sentence = sentence.toLowerCase()
                .replaceAll(onlyLettersRegex, emptyString);
        String[] arrayWithAllWords = sentence
                .split(spaceString);
        Map<Character, TreeSet<String>> mapOfPresentCharacters = getMapWithAllCharactersAsKey(sentence);
        new TreeSet<>(Arrays.asList(arrayWithAllWords)).forEach(word -> addWordToMap(mapOfPresentCharacters, word));
        return mapToString(mapOfPresentCharacters);
    }

    void printIndexedSentence(String sentence) {
        System.out.print(indexCharactersToWords(sentence));
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
                .replaceAll(spaceString, emptyString)
                .chars()
                .mapToObj(c -> (char) c)
                .distinct()
                .collect(Collectors.toMap(Function.identity(),
                        i -> new TreeSet<>(),
                        (ghrPrevious, ghrNew) -> ghrNew,
                        TreeMap::new));
    }
}
