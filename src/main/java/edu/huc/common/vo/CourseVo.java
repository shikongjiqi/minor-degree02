package edu.huc.common.vo;

public class CourseVo {
    private int courseId;

    private String courseName;

    private String minorName;

    private String timeTable;

    private String userName;

    public String getMinorName() {
        return minorName;
    }

    public void setMinorName(String minorName) {
        this.minorName = minorName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(String timeTable) {
        this.timeTable = timeTable;
    }

    public String getTeacherName() {
        return userName;
    }

    public void setTeacherName(String userName) {
        this.userName = userName;
    }
}
