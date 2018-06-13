package sample.Model.DataBase;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserGrade {
    private SimpleIntegerProperty grade;
    private SimpleStringProperty courseName;
    private SimpleStringProperty moad;

    public UserGrade(int grade ,String courseName,String moad) {
        this.grade = new SimpleIntegerProperty(grade);
        this.courseName = new SimpleStringProperty(courseName);
        this.moad = new SimpleStringProperty(moad);
    }

    public int getGrade() {
        return grade.get();
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public String getMoad() {
        return moad.get();
    }

    public void setMoad(String moad) {
        this.moad.set(moad);
    }
}
