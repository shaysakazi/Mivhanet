package sample.Model.DataBase;

import javafx.beans.property.SimpleStringProperty;

public class Lecturer {
    SimpleStringProperty lecturerName;

    public Lecturer(String lecturerName) {
        this.lecturerName = new SimpleStringProperty(lecturerName);
    }

    public String getLecturerName() {
        return lecturerName.get();
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName.set(lecturerName);
    }
}
