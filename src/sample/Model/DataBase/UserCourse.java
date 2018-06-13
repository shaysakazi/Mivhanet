package sample.Model.DataBase;

import javafx.beans.property.SimpleStringProperty;

public class UserCourse {
    private SimpleStringProperty Course;

    public UserCourse(String course) {
        this.Course = new SimpleStringProperty(course);
    }

    public String getCourse() {
        return Course.get();
    }

    public void setCourse(String course) {
        this.Course.set(course);
    }
}
