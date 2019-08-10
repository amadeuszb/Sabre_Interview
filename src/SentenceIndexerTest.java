import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SentenceIndexerTest {

    @Test
    void emptySentenceShouldReturnEmptyString() {
        SentenceIndexer indexer = new SentenceIndexer();
        String inputText = "";
        String response = "";
        assertEquals(indexer.indexSentence(inputText), response);
    }
    @Test
    void nullSentenceShouldReturnEmptyString() {
        SentenceIndexer indexer = new SentenceIndexer();
        String inputText = null;
        String response = "";
        assertEquals(indexer.indexSentence(inputText), response);
    }
    @Test
    void onlyWhiteCharactersSentenceShouldReturnEmptyString() {
        SentenceIndexer indexer = new SentenceIndexer();
        String inputText = "   ,    ,,    ,,   ,  ";
        String response = "";
        assertEquals(indexer.indexSentence(inputText), response);
    }

    @Test
    void shouldReturnCorrectStringProvided() {
        SentenceIndexer indexer = new SentenceIndexer();
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
        assertEquals(indexer.indexSentence(inputText), response);
    }
    @Test
    void shouldReturnCorrectString() {
        SentenceIndexer indexer = new SentenceIndexer();
        String inputText = "aa, aab, aa, b";
        String response = "a: aa, aab\n" +
                "b: aab, b\n";
        assertEquals(indexer.indexSentence(inputText), response);
    }



}