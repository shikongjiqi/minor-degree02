package edu.huc.common.result;

public class ResultApplyUser {
    private String name;

    private String majorName;

    private String minorName;

    private String majorCourse;

    private String cardId;

    private int checked;

    private double averageScore;

    private String interestCourse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMinorName() {
        return minorName;
    }

    public void setMinorName(String minorName) {
        this.minorName = minorName;
    }

    public String getMajorCourse() {
        return majorCourse;
    }

    public void setMajorCourse(String majorCourse) {
        this.majorCourse = majorCourse;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public String getInterestCourse() {
        return interestCourse;
    }

    public void setInterestCourse(String interestCourse) {
        this.interestCourse = interestCourse;
    }
}
