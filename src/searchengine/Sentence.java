package searchengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sentence {
	private int wordCount; // Number of words in a sentence

	private ArrayList<ShiftedSentence> shiftedSentences;

	public Sentence(String[] words) {

		words = removeStopWords(words); // stop word'leri direkt dahil etmedim olusturulanlardan dolayısıyla search edilecek cumle girildiginden de oncelikle stop wordslerden arindirilacak. Stop words'ler indexlemeye dahil edilmedi.

	    wordCount = words.length;
		shiftedSentences = new ArrayList<ShiftedSentence>();

		// Kelimeleri shift ederek yeni cümleler olusturur.
		for(int i= 0; i < words.length; i++) {
			// Shifting words[] to left
			float new_score = (float)1/(words.length * (i+1));
			ShiftedSentence shiftedSentence = new ShiftedSentence(String.join(" ", words), new_score);
			shiftedSentences.add(shiftedSentence);
			words = shiftSentenceLeft(words);
		}
	}

	public String[] shiftSentenceLeft(String[] words) {
		List<String> wordsList = new ArrayList<>(Arrays.asList(words));
		wordsList.add(wordsList.remove(0));

		return wordsList.toArray(new String[0]);
	}

	public int getWordCount() {
		return wordCount;
	}

	public String getShiftedSentence(int i) {
		return shiftedSentences.get(i).getProcessedSentence();
	}

	public ArrayList<String> getStopWords(){
        String[] stopWords = new String[]{"vs","a","the","of","from","to", "someone"};

        return new ArrayList<String>(Arrays.asList(stopWords));
    }

    public String[] removeStopWords(String[] words){
		ArrayList<String> wordsList = new ArrayList<String>();
	    ArrayList<String> stopWords = getStopWords();

	    for(int i = 0; i < words.length; i++) {
	    	System.out.println(stopWords.indexOf(words[i]) == -1);
	        if(stopWords.indexOf(words[i]) == -1){
	            wordsList.add(words[i]);
            }
        }
		System.out.println(String.join("#", wordsList));
        return wordsList.toArray(new String[0]);
    }
}