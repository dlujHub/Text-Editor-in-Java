package document;

import java.util.List;

/** 
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words, 
 * and sentences and then stores those values.
 *
 */
public class EfficientDocument extends Document {

	private int numWords = 0;  // The number of words in the document
	private int numSentences = 0;  // The number of sentences in the document
	private int numSyllables = 0;  // The number of syllables in the document
	
	public EfficientDocument(String text)
	{
		super(text);
		processText();
	}
	
	
	/** 
	 * Take a string that either contains only alphabetic characters,
	 * or only sentence-ending punctuation.  Return true if the string
	 * contains only alphabetic characters, and false if it contains
	 * end of sentence punctuation.  
	 * 
	 * @param tok The string to check
	 * @return true if tok is a word, false if it is punctuation. 
	 */
	private boolean isWord(String tok)
	{
	    // A fast way of checking whether a string is a word
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	
	
    /** Passes through the text one time to count the number of words, syllables 
     * and sentences, and set the member variables appropriately.
     * Words, sentences and syllables are defined as described below. 
     */
	private void processText()
	{
		// Call getTokens on the text to preserve separate strings that are 
		// either words or sentence-ending punctuation.  Ignore everything
		// That is not a word or a sentence-ending puctuation.
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		if (!tokens.isEmpty()) {
			for (String word : tokens) {
				if (isWord(word)) {
					numWords++;
					numSyllables += countSyllables(word);
				} else {
					numSentences++;
				}
			}

			if (isWord(tokens.get(tokens.size()-1))) {
				numSentences++;
			}
		}
	}

	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 *
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the document.EfficientDocument object.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences() {
		return numSentences;
	}

	
	/**
	 * Get the number of words in the document.
	 * A "word" is defined as a contiguous string of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z.  This method completely 
	 * ignores numbers when you count words, and assumes that the document does not have 
	 * any strings that combine numbers and letters. 
	 *
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the document.EfficientDocument object.
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords() {
	    return numWords;
	}


	/**
	 * Get the total number of syllables in the document (the stored text). 
	 * To calculate the the number of syllables in a word, it uses the following rules:
	 *       Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 *
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the document.EfficientDocument object.
	 * 
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables() {
        return numSyllables;
	}
	
	// Can be used for testing
	public static void main(String[] args)
	{
	    Document.testCase(new EfficientDocument("This is a test.  How many???  "
                + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        Document.testCase(new EfficientDocument(""), 0, 0, 0);
        Document.testCase(new EfficientDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        Document.testCase(new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
        Document.testCase(new EfficientDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		Document.testCase(new EfficientDocument("Segue"), 2, 1, 1);
		Document.testCase(new EfficientDocument("Sentence"), 2, 1, 1);
		Document.testCase(new EfficientDocument("Sentences?!"), 3, 1, 1);
		Document.testCase(new EfficientDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		
	}
	

}
