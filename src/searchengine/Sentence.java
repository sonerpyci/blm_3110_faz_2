package searchengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sentence {
	private int wordCount; // Number of words in a sentence

	private ArrayList<String> shiftedSentences;

	public Sentence(String[] words) {
		wordCount = words.length;
		shiftedSentences = new ArrayList<String>();

		shiftedSentences.add(String.join(" ", words));

		// Kelimeleri shift ederek yeni cümleler olusturur.
		for(int j = 1; j < words.length; j++) {
			// Shifting words[] to left
			List<String> wordsList = Arrays.asList(words).subList(1, words.length);
			wordsList.add(words[0]);
			words = (String[])wordsList.toArray();

			shiftedSentences.add(String.join(" ", words));
		}
	}

	public int getWordCount() {
		return wordCount;
	}

	public String getShiftedSentence(int i) {
		return shiftedSentences.get(i);
	}
}