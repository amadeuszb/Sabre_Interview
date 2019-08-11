import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SentenceIndexerTest {

    public final SentenceIndexer indexer = new SentenceIndexer();

    @Test
    public void emptySentenceShouldReturnEmptyString() {
        String inputText = "";
        String response = "";
        assertEquals(indexer.indexCharactersToWords(inputText), response);
    }

    @Test
    public void nullSentenceShouldReturnEmptyString() {
        String inputText = null;
        String response = "";
        assertEquals(indexer.indexCharactersToWords(inputText), response);
    }

    @Test
    public void onlyWhiteCharactersSentenceShouldReturnEmptyString() {
        String inputText = "   ,    ,,    ,,   ,  ";
        String response = "";
        assertEquals(indexer.indexCharactersToWords(inputText), response);
    }

    @Test
    public void shouldReturnCorrectStringProvided() {
        String inputText = "ala ma kota, kot koduje w Javie Kota";
        String response = "a: ala, javie, kota, ma\n" +
                "d: koduje\n" +
                "e: javie, koduje\n" +
                "i: javie\n" +
                "j: javie, koduje\n" +
                "k: koduje, kot, kota\n" +
                "l: ala\n" +
                "m: ma\n" +
                "o: koduje, kot, kota\n" +
                "t: kot, kota\n" +
                "u: koduje\n" +
                "v: javie\n" +
                "w: w\n";
        assertEquals(indexer.indexCharactersToWords(inputText), response);
    }

    @Test
    public void shouldReturnCorrectString() {
        String inputText = "aa, aab, aa, b";
        String response = "a: aa, aab\n" +
                "b: aab, b\n";
        assertEquals(indexer.indexCharactersToWords(inputText), response);
    }

}