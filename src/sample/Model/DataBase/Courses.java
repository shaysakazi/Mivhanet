package sample.Model.DataBase;

import javafx.beans.property.SimpleStringProperty;

public class Courses {
    SimpleStringProperty courseName;

    public Courses(String courseName) {
        this.courseName = new SimpleStringProperty(courseName);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }
}
