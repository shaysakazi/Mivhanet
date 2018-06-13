package sample.Model.DataBase;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        Db db = Db.getSingleton();
        //List<Product> product=db.getAllProductsPrice(2000);
        //String ass = db.getRoleUser("ido");
        /*List<Courses>  listCourses = db.getAllCourses();
        for (Courses course: listCourses) {
            System.out.println(course.getCourseName());
        }*/

        /*List<Lecturer> listCourses = db.getAllLecturers();
        for (Lecturer lecturer: listCourses) {
            System.out.println(lecturer.getLecturerName());
        }*/

        /*List<UserGrade> listCourses = db.getUserExams("idan");
        for (UserGrade userGrade: listCourses) {
            System.out.println(userGrade.getGrade()+ "," +userGrade.getCourseName()+","+userGrade.getMoad());
        }*/

        /*List<UserCourse> listCourses = db.getUserCourses("eden");
        for (UserCourse userCourse: listCourses) {
            System.out.println(userCourse.getCourse());
        }*/

        //System.out.println(db.checkUser("ido","Texas" ));
        //String s = db.getSemesterId("A", "2017");

        //System.out.println(db.addCPS("dataMining","B","2017","armin"));

        //System.out.println(db.addUserPassword("doron","hello"));
        System.out.println(db.getUserPassword("doron"));

        //db.addExam("25/6/19",3,"C");
        //System.out.println(ass);
    }
}
