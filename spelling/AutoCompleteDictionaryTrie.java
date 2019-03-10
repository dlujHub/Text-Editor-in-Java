package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie.
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    word = word.toLowerCase();
	    TrieNode curr = root;
		TrieNode next;
	    for (char c : word.toCharArray()) {
			next = curr.insert(c);
			if (next == null) {
	    		curr = curr.getChild(c);
			} else {
				curr = next;
			}
		}
		if (curr.endsWord()) {
	    	return false;
		}
		curr.setEndsWord(true);
	    size++;

	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie. */
	@Override
	public boolean isWord(String s) 
	{
		s = s.toLowerCase();
		TrieNode curr = root;
		for (char c : s.toCharArray()) {
			curr = curr.getChild(c);
			if (curr == null) {
				return false;
			}
		}
		if (curr.endsWord()) {
			return true;
		}
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions)
     {
    	 List<String> answer = new ArrayList<>();
    	 LinkedList<TrieNode> queue = new LinkedList<>();
    	 prefix = prefix.toLowerCase();
		 TrieNode curr = root;
		 int count=0;

		 for (char c : prefix.toCharArray()) {
		 	curr = curr.getChild(c);
		 	if (curr == null) {
		 		return answer;
			}
		 }
		 queue.add(curr);
		 while (!queue.isEmpty() && count < numCompletions) {

			 curr = queue.remove(0);
			 if (curr.endsWord()) {
				 answer.add(curr.getText());
				 count++;
			 }
		 	for (char c : curr.getValidNextCharacters()) {
		 		queue.add(curr.getChild(c));
			}

		 }

         return answer;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}



}