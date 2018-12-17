package searchengine;

public class Engine {
	private Sentence [] sentenceArray;
	private String[] sortArray;

	private int count;
	private int countsort;
	private int i,j;

	public Engine() {
		sortArray = new String[100];
		sentenceArray = new Sentence [100];
		count = 0;
		countsort = 0;
	}

	public void sortArrayCreate() {
		for(i = 0; i < count; i++) {
			for(j = 0; j < sentenceArray[i].getArraylength(); j++) {
				sortArray[countsort] = "";
				sortArray[countsort] = sortArray[countsort].concat(sentenceArray[i].sentenceArrayVar[j]);
				countsort++;
			}
		}
		
	}
	public void sort() {
		sortArrayCreate();
		char temp[] = new char[countsort];

		for(i = 0; i < countsort; i++) {
			temp[i] = sortArray[i].charAt(1);
	
			int x = temp[i];

			if(x < 97) {    // Uppercase to lowercase yapiyorlar ama Java ascii kullanmiyor o yuzden bu mantık hatalı buna bakmak lazim
				x = x + 32;
				temp[i]=(char)x;
			}
		}

		int n = countsort;
		  
		// One by one move boundary of unsorted subarray
		for (i = 0; i < n-1; i++) {
			// Find the minimum element in unsorted array
			int min_idx = i;

			for (j = i+1; j < n; j++) {
				int s = temp[j];
				int o = temp[min_idx];
		            	
				if (s< o) {
					min_idx = j;
				}
			}

			// Swap the found minimum element with the first
			// element
			char temp2 = temp[min_idx];

			temp[min_idx] =temp[i];
			temp[i] = temp2;
		            
			String temp3 = sortArray[min_idx];

			sortArray[min_idx] = sortArray[i];
			sortArray[i] = temp3;
		}
	}
	
	public void addSentence(Sentence  sentencex) {
		sentenceArray[count] = sentencex;
		count++;
	}
	
	
	
	public String listIndex(int i) {
		return sortArray[i];
	}

	public int getCountSort() {
		return countsort;
	}
}
