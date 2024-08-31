package com.test.compass.dto;

public class Result {
    private final Integer source;
    private final Integer match;
    private Integer score = 0;

    public Result(Integer source, Integer match) {
        this.source = source;
        this.match = match;
    }

    public void plusScore() {
        this.score += 1;
    }

    public Integer getScore() {
        return score;
    }

    public String getAccuracy() {
        if(score >= 1 && score <= 2 ) {
            return "Low";
        } else if(score == 3 ) {
            return "Medium";
        } else if(score > 3 ) {
            return "High";
        }
        return "";
    }

    @Override
    public String toString() {
        return "ContactID Source =" + source +
                ", ContactID Match =" + match +
                ", Accuracy =" + getAccuracy();
    }
}
