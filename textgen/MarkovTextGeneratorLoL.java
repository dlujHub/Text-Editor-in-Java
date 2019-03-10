package textgen;

import java.util.*;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 *
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String[] allWords = sourceText.split(" +");

		starter = allWords[0];
		String prevWord = starter;
		String w = "";
		int idx = -1;
		for (int i=1; i < allWords.length; i++) {
			w = allWords[i];
			for (ListNode n : wordList) {
				if (n.getWord().equals(prevWord)) {
					idx = wordList.indexOf(n);
					break;
				}
					idx = -1;
			}
		if (idx != -1 )	{
			wordList.get(idx).addNextWord(w);
			prevWord = w;
		} else {
			wordList.add(new ListNode(prevWord));
			wordList.get(wordList.size()-1).addNextWord(w);
			prevWord = w;
		}
		}


		// for starter (the last word in the string)
		for (ListNode n : wordList) {
			if (n.getWord().equals(w)) {
				idx = wordList.indexOf(n);
				break;
			}
			idx = -1;
		}
		if (idx != -1 ) {
			wordList.get(idx).addNextWord(starter);
		} else {
			wordList.add(new ListNode(w));
			wordList.get(wordList.size()-1).addNextWord(starter);
		}

	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		String currWord = starter;
		String output;
				output = starter;
		int count = 0;
		int idx = 0;
		while (count < numWords-1) {
			for (ListNode n : wordList) {
				if (n.getWord().equals(currWord)) {
					idx = wordList.indexOf(n);
					break;
				}
			}
			currWord = wordList.get(idx).getRandomNextWord(new Random());
			output +=  " " + currWord;
			count++;
		}
		return output;
	}
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		starter = "";
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}

	
	/**
	 * This is a minimal set of tests.
	 * @param args
	 */
	public static void


	main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "hi there hi Leo";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(4));

		 gen = new MarkovTextGeneratorLoL(new Random(42));
		 textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));

	}

}

/** Links a word to the next words in the list
 */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int idx = generator.nextInt(nextWords.size());
	    return nextWords.get(idx);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


