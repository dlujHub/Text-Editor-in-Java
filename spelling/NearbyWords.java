/**
 * 
 */
package spelling;

import java.util.*;

public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<>();
		   insertions(s, retList, wordsOnly);
		  substitution(s, retList, wordsOnly);
		  deletions(s, retList, wordsOnly);
		   return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		for (int i=0; i<s.length(); i++) {

			for (int indexChar = (int) 'a'; indexChar <= (int) 'z'; indexChar++) {
				StringBuilder  sb = new StringBuilder();
				sb.append(s.substring(0, i) + (char) indexChar + s.substring(i,s.length()));

				if (!currentList.contains(sb) && !s.equals(sb.toString()) && (!wordsOnly || dict.isWord(sb.toString()))) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		for (int i=0; i<s.length(); i++) {
			StringBuffer sb = new StringBuffer(s);

			sb.delete(i, i+1);
			if (!currentList.contains(sb) && !s.equals(sb.toString()) && (!wordsOnly || dict.isWord(sb.toString()))) {
				currentList.add(sb.toString());
			}
		}
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// initial variables
		List<String> queue = new LinkedList<String>();     // String to explore
		HashSet<String> visited = new HashSet<String>();   // to avoid exploring the same  
														   // string multiple times
		List<String> retList = new LinkedList<String>();   // words to return
		 String curr;
		int count = 0;
		int countThreshold=0;
		// insert first node
		queue.add(word);
		visited.add(word);
					
		while (!queue.isEmpty() && count <= numSuggestions && THRESHOLD > countThreshold) {
			curr = queue.remove(0);
			for (String w : distanceOne(curr, true)) {
				if (!visited.contains(w)) {
					visited.add(w);
					queue.add(w);
					if (dict.isWord(w)) {
						retList.add(w);
					}
					countThreshold++;
				}

			}
			count = retList.size();
		}
		//Collections.sort(retList);
		return retList;

	}	

   public static void main(String[] args) {
	   //basic testing code to get started
	   String word = "dar";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away word Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");

	   word = "kangaro";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);

   }

}
