package edu.huc.common.result;

/**
 * 对辅修专业信息的数据结果进行封装
 */
public class ResultMinor {
    private int minorId;

    private String minorName;

    private String academy;

    private int count;

    public int getMinorId() {
        return minorId;
    }

    public void setMinorId(int minorId) {
        this.minorId = minorId;
    }

    public String getMinorName() {
        return minorName;
    }

    public void setMinorName(String minorName) {
        this.minorName = minorName;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ResultMinor{" +
                "minorId=" + minorId +
                ", minorName='" + minorName + '\'' +
                ", academy='" + academy + '\'' +
                ", count=" + count +
                '}';
    }
}
