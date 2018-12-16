package searchengine;

public class Sentence {
	private int arraylength;
	private String temp;
	String [] sentenceArrayVar;
	public Sentence(String [] words) {
		arraylength=words.length;	
		sentenceArrayVar= new String[words.length];
		int count = 1;

		int i;
		String sentencer = "";
		for (i = 0; i < words.length; i++) {
			sentencer = sentencer.concat(" "+words[i]);
		}
		sentenceArrayVar[0]= sentencer;

		int j;
		for(j =1; j <words.length; j++) {
			int k=0;
			sentencer ="";
					
			while(k<words.length-1) {
				if(k==0) {
					
				temp=words[k];
				words[k]=words[k+1];
				}else {
					words[k]=words[k+1];
				}
				
				k++;
			
			}
			words[k]=temp;
		for (i = 0; i < words.length; i++) {
			
			sentencer = sentencer.concat(" "+words[i]);
			//sentenceArrayVar[count]=sentenceArrayVar[count].concat(" "+words[i]);
			
			
}
	sentenceArrayVar[count]= sentencer;
		count++;
		}
		
	}
	
	
	
	
	
	public int getArraylength() {
		return arraylength;
	}

}