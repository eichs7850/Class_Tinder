package com.example.classtinder;
public class CourseModal {
    private String courseName;
    private String[] courseTimes;
    private String[] courseDays;
    private String courseDescription;
    private int imgId;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseTimes(String[] courseTimes) {
        this.courseTimes = courseTimes;
    }

    public String[] getCourseTimes() {
        return courseTimes;
    }

    public void setCourseDays(String[] courseDays) {
        this.courseDays = courseDays;
    }

    public String[] getCourseDays() {
        return courseDays;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public CourseModal(String courseName, String courseDescription, String[] courseTimes, String[] courseDays, int imgId) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseDays = courseDays;
        this.courseTimes = courseTimes;
        this.imgId = imgId;
    }
}
