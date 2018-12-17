package searchengine;

import java.util.Arrays;
import java.util.List;

public class Sentence {
	private int arraylength;
	private String temp;

	String [] sentenceArrayVar;

	public Sentence(String[] words) {
		arraylength = words.length;
		sentenceArrayVar = new String[words.length];

		/*
		String sentencer = "";

		for (int i = 0; i < words.length; i++) {
			sentencer = sentencer.concat(" " + words[i]);   // cumle baslangicinda bosluk oluyor bu hatali !
		}

		sentenceArrayVar[0]= sentencer;
		*/

		sentenceArrayVar[0] = String.join(" ", words);  // yukaridaki yerine eklendi

		// Kelimeleri shift ederek yeni cümleler olusturur.
		for(int j = 1; j < words.length; j++) {
			/* Shifting words[] to left
		    int k = 0;
			sentencer = "";

			while(k < words.length-1) {
				if(k == 0) {
				    temp = words[k];
				    words[k] = words[k+1];
				}
				else {
					words[k] = words[k+1];
				}
				
				k++;
			}

      		words[k]=temp;
      		*/
            //TODO: Test edilmeli shift edilme islemi icin asagidaki kod yukarıdaki yerine
			List<String> wordsList = Arrays.asList(words).subList(1, words.length);
			wordsList.add(words[0]);
			words = (String[])wordsList.toArray();

			/*
			sentencer = "";

			for (int i = 0; i < words.length; i++) {
				sentencer = sentencer.concat(" " + words[i]);   // cumle baslangicinda bosluk oluyor bu hatali !
			    //sentenceArrayVar[count]=sentenceArrayVar[count].concat(" "+words[i]);
			}

			sentenceArrayVar[count] = sentencer;
			*/

			sentenceArrayVar[j] = String.join(" ", words);  // yukaridakine alternatif
		}
	}
	
	
	
	
	
	public int getArraylength() {
		return arraylength;
	}

}