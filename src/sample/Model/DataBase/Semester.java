package sample.Model.DataBase;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Semester {

    private SimpleStringProperty Semester;
    private SimpleIntegerProperty year;

    public Semester(String semester ,int year) {
        this.Semester = new SimpleStringProperty(semester);
        this.year = new SimpleIntegerProperty(year);
    }

    public String getSemester() {
        return Semester.get();
    }

    public String semesterProperty() {
        return Semester.get();
    }

    public void setSemester(String semester) {
        this.Semester.set(semester);
    }

    public int getYear() {
        return year.get();
    }

    public int yearProperty() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }
}
