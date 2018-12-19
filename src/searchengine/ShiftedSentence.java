package searchengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShiftedSentence {

    private String processedSentence;
    private float score;

    public ShiftedSentence(String processedSentence, float score){
        this.processedSentence = processedSentence;
        this.score = score;
    }

    public String getProcessedSentence() {
        return processedSentence;
    }

    public void setProcessedSentence(String processedSentence) {
        this.processedSentence = processedSentence;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
