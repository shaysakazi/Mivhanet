package sample.Model.DataBase;

public class Test {
    public static void main(String[] args) throws Exception {
        Db db = Db.getSingleton();
        //List<Product> product=db.getAllProductsPrice(2000);
        //String ass = db.getRoleUser("ido");
        /*List<Courses>  listCourses = db.getAllCourses();
        for (Courses course: listCourses) {
            System.out.println(course.getCourseName());
        }*/

        /*List<Semester> listCourses = db.getAllSemester();
        for (Semester semester: listCourses) {
            System.out.println(semester.getSemester()+ "," +semester.getYear());
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


        //db.addExam("25/6/19",3,"C");
        //System.out.println(ass);
    }
}
