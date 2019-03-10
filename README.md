Text Editor in Java
===============================
The basic code (like JavaFx UI, some JUnit tests) is taken from Coursera course - https://www.coursera.org/specializations/java-object-oriented.

This text editor has following features:
1. Autocomplete using trie data structure
Dictionary was implemented in trie - saves the memory + reduces the searching time to O(L), L - length of the string to search.
![auto](/data/img/auto.png)

2.  A word is highlighted red, if it's not in the dictionary
![red](/data/img/red.png)

3. Spelling suggestions using one letter mutations
![spelling](/data/img/spelling.png)

4. Markov text generator
It generates new text of specified length, using some input text (like "War and Peace").
![markov](/data/img/markov.png)

5. Edit distance - Word path between source to target words
Application takes 2 words and gives the steps on how the first word can be converted to second word making only one mutation at a time and the resulting all intermediate words are in the dictionary. If no such transformation is found - "No Path Found" appears. 
![edit](/data/img/edit.png)

6. Flesch readability score
This score determines how easy/difficult it is to read a text. (Calculates the number of sentences, words, and syllables in it and uses a formula.
![flesch](/data/img/flesch.png)
