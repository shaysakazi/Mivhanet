package sample.Model.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IL984626 on 30/12/2017.
 */
public class Db {

    private static int productID=0;

    private static class Holder {
        static Db instance;

        static {
            try {
                instance = new Db();
            } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }

    public static Db getSingleton() { // Note: "synchronized" not needed
        return Holder.instance;
    }

    public Db() throws Exception {
        String url = "jdbc:sqlite:ev4rent.db";
        Connection conn = SqliteHelper.getConn();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Users(ID INTEGER NOT NULL UNIQUE ,UserName TEXT NOT NULL,LasName TEXT NOT NULL,FirstName TEXT NOT NULL,Address TEXT,Phone TEXT,mail TEXT NOT NULL,Role TEXT,Password TEXT,PRIMARY KEY(ID, UserName) );");
        /*stmt.execute("CREATE TABLE IF NOT EXISTS users(email VARCHAR PRIMARY KEY NOT NULL , name VARCHAR NOT NULL,password VARCHAR NOT NULL , age INTEGER NOT NULL ,paypal VARCHAR NOT NULL, hasProducts NUMERIC NOT NULL );");
        stmt.execute("CREATE TABLE IF NOT EXISTS products(id INTEGER PRIMARY KEY, name VARCHAR NOT NULL,price REAL NOT NULL , swap NUMERIC NOT NULL ,donation NUMERIC NOT NULL ,available NUMERIC NOT NULL, rating REAL NOT NULL,category VARCHAR NOT NULL , userEmail VARCHAR NOT NULL ,FOREIGN KEY (userEmail) REFERENCES users(email));");
        stmt.execute("CREATE TABLE IF NOT EXISTS packages(id INTEGER PRIMARY KEY,name VARCHAR NOT NULL ,productId INTEGER  NOT NULL,FOREIGN KEY (productId) REFERENCES products(id));");
        stmt.execute("CREATE TABLE IF NOT EXISTS orders(id INTEGER PRIMARY KEY,productId INTEGER NOT NULL ,renterEmail VARCHAR NOT NULL,tenantEmail VARCHAR NOT NULL,FOREIGN KEY (productId) REFERENCES products(id),FOREIGN KEY (renterEmail) REFERENCES users(email),FOREIGN KEY (tenantEmail) REFERENCES users(email));");
        stmt.execute("CREATE TABLE IF NOT EXISTS swaps(id INTEGER  PRIMARY KEY,productId1 INTEGER NOT NULL,renterEmail1 VARCHAR NOT NULL ,productId2 INTEGER  NOT NULL,renterEmail2 VARCHAR NOT NULL,FOREIGN KEY (renterEmail2) REFERENCES users(email),FOREIGN KEY (renterEmail1) REFERENCES users(email),FOREIGN KEY (productId1) REFERENCES products(id),FOREIGN KEY (productId2) REFERENCES products(id));");*/
    }

    public String getPasswordUser(String username)throws Exception {
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT Password FROM Users WHERE UserName = "+"'"+username+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();

        return rs.getString("Password");
    }

    public String getRoleUser(String username)throws Exception {
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT Role FROM Users WHERE UserName = "+"'"+username+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();

        return rs.getString("Role");
    }

    public ObservableList<Courses> getAllCourses() throws Exception {
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT CourseName FROM Courses";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs= ps.executeQuery();
        if (!rs.isBeforeFirst() ) {
            return null;
        }

        List<Courses> listProducts = new ArrayList<Courses>();

        while(rs.next()){
            listProducts.add(new Courses(rs.getString("CourseName")));
        }

        ObservableList<Courses> listOfProducts = FXCollections.observableList(listProducts);

        return listOfProducts;
    }

    public ObservableList<Semester> getAllSemester() throws Exception {
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT Semester,year FROM Semester";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs= ps.executeQuery();
        if (!rs.isBeforeFirst() ) {
            return null;
        }

        List<Semester> listProducts = new ArrayList<Semester>();

        while(rs.next()){
            listProducts.add(new Semester(rs.getString("Semester"),rs.getInt("year")));
        }

        ObservableList<Semester> listOfProducts = FXCollections.observableList(listProducts);

        return listOfProducts;
    }

    public boolean addExam(String Date,int cpsId,String moad){
        Connection conn = null;
        try {
            conn = SqliteHelper.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Statement stmt = conn.createStatement();
            String INSERT_SQL = "INSERT INTO Exams(Date, cpsId,moad) VALUES(?, ?, ?)";
            PreparedStatement ps=conn.prepareStatement(INSERT_SQL);
            ps.setString(1, Date);
            ps.setInt(2, cpsId);
            ps.setString(3, moad);
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean addUser(String UserName,String LastName,String FirstName,String Address,int Phone,String mail,String Role,String Password){
        Connection conn = null;
        try {
            conn = SqliteHelper.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Statement stmt = conn.createStatement();
            String INSERT_SQL = "INSERT INTO Users(UserName, LastName, FirstName, Address, Phone,mail,Role,Password) VALUES(?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement ps=conn.prepareStatement(INSERT_SQL);
            ps.setString(1, UserName);
            ps.setString(2, LastName);
            ps.setString(3, FirstName);
            ps.setString(4, Address);
            ps.setInt(5, Phone);
            ps.setString(6, mail);
            ps.setString(7, Role);
            ps.setString(8, Password);
            ps.executeUpdate();
        } catch (SQLException e) {
           return false;
        }
        return true;
    }

    public User getUser(String UserName) throws Exception {
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT * FROM users WHERE UserName LIKE "+"'"+UserName+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        return new User(rs.getString("UserName"),rs.getString("LastName"),rs.getString("FirstName"),rs.getString("Address"),rs.getInt("Phone"),rs.getString("mail"),rs.getString("Role"),rs.getString("Password"));
    }

    public ObservableList<UserGrade> getUserExams(String UserName)throws Exception{
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT TestStudent.Grade,Courses.CourseName,Exams.Moad from Users \n" +
                "Inner Join TestStudent ON TestStudent.StudentID = Users.ID\n" +
                "Inner Join Exams On Exams.ID = TestStudent.ExamID\n" +
                "Inner Join CoursePerSemester On CoursePerSemester.ID = Exams.CpsID\n" +
                "Inner join Courses On Courses.ID = CoursePerSemester.courseID\n" +
                "where \n" +
                "Users.UserName =" + "'"+UserName+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs= ps.executeQuery();
        if (!rs.isBeforeFirst() ) {
            return null;
        }

        List<UserGrade> listProducts = new ArrayList<UserGrade>();

        while(rs.next()){
            listProducts.add(new UserGrade(rs.getInt("Grade"),rs.getString("CourseName"),rs.getString("Moad")));
        }

        ObservableList<UserGrade> listOfProducts = FXCollections.observableList(listProducts);

        return listOfProducts;
    }

    public ObservableList<UserCourse> getUserCourses(String UserName) throws Exception{
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT distinct Courses.CourseName from Users \n" +
                "Inner Join TestStudent On TestStudent.StudentID = Users.ID\n" +
                "Inner Join Exams On Exams.ID = TestStudent.ExamID\n" +
                "Inner Join CoursePerSemester On CoursePerSemester.ID = Exams.CpsID\n" +
                "Inner Join Courses On Courses.ID = CoursePerSemester.courseID\n" +
                "where \n" +
                "Users.UserName =" + "'"+UserName+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs= ps.executeQuery();
        if (!rs.isBeforeFirst() ) {
            return null;
        }

        List<UserCourse> listProducts = new ArrayList<UserCourse>();

        while(rs.next()){
            listProducts.add(new UserCourse(rs.getString("CourseName"))); // pay attention this name of column in the db
        }

        ObservableList<UserCourse> listOfProducts = FXCollections.observableList(listProducts);

        return listOfProducts;
    }

    public boolean checkUser(String userName, String password) throws Exception {
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT * FROM users WHERE UserName LIKE "+"'"+userName+"'"+"AND Password LIKE"+"'"+password+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        if (!rs.isBeforeFirst() ) {
            return false;
        }
        return true;

    }

    public String getUserId(String UserName) throws Exception{
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT ID FROM users WHERE UserName LIKE "+"'"+UserName+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        return rs.getString("ID");
    }

    public String getCourseId(String courseName) throws Exception{
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT ID FROM Courses WHERE CourseName LIKE "+"'"+courseName+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        return rs.getString("ID");
    }

    public String getSemesterId(String semester, String year)throws Exception{
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT ID FROM Semester WHERE Semester LIKE "+"'"+semester+"'"+" AND year LIKE "+"'"+year+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        return rs.getString("ID");
    }

    public String getCPSIdByManager(String managerID) throws Exception{
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT ID FROM CoursePerSemester WHERE Manager LIKE "+"'"+managerID+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        return rs.getString("ID");
    }

    public String getCPSIdBySemesterIdAndCourseId(String courseId,String semesterId) throws Exception{
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT ID FROM CoursePerSemester WHERE courseID  LIKE "+"'"+courseId+"'"+" AND semesterID LIKE "+"'"+semesterId+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        return rs.getString("ID");
    }

    public boolean addCPS(String courseName,String Semester,String year,String courseManager)throws Exception{
        Connection conn = null;
        try {
            conn = SqliteHelper.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String courseID = getCourseId(courseName);
            String semesterID = getSemesterId(Semester,year);
            String manager = getUserId(courseManager);
            String role = getRoleUser(courseManager);

            Statement stmt = conn.createStatement();
            String INSERT_SQL = "INSERT INTO CoursePerSemester(courseID,semesterID,Manager) VALUES(?, ?, ?)";
            PreparedStatement ps=conn.prepareStatement(INSERT_SQL);
            ps.setString(1, courseID);
            ps.setString(2, semesterID);
            ps.setString(3, manager);
            ps.executeUpdate();
            addCourseStuff(manager,role);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean addCourseStuff(String userId, String role)throws Exception{
        Connection conn = null;
        try {
            conn = SqliteHelper.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String cpsId = getCPSIdByManager(userId);
            Statement stmt = conn.createStatement();
            String INSERT_SQL = "INSERT INTO CourseStaff(userID,CPSID,Role) VALUES(?,?,?)";
            PreparedStatement ps=conn.prepareStatement(INSERT_SQL);
            ps.setString(1, userId);
            ps.setString(2, cpsId);
            ps.setString(3, role);
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;

    }

    public boolean addExam(String courseName, String semester, String year, String moad,String date)throws Exception{
        Connection conn = null;
        try {
            conn = SqliteHelper.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String courseId = getCourseId(courseName);
            String semesterId = getSemesterId(semester,year);
            String cpsId = getCPSIdBySemesterIdAndCourseId(courseId,semesterId);

            Statement stmt = conn.createStatement();
            String INSERT_SQL = "INSERT INTO Exams(Date,CpsID,Moad) VALUES(?,?,?)";
            PreparedStatement ps=conn.prepareStatement(INSERT_SQL);
            ps.setString(1, date);
            ps.setString(2, cpsId);
            ps.setString(3, moad);
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;

    }

    public ObservableList<Lecturer> getAllLecturers()throws Exception{
        Connection conn = SqliteHelper.getConn();
        String query = "select UserName from Users where Role = 'Lecturer'";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs= ps.executeQuery();
        if (!rs.isBeforeFirst() ) {
            return null;
        }

        List<Lecturer> listProducts = new ArrayList<Lecturer>();

        while(rs.next()){
            listProducts.add(new Lecturer(rs.getString("UserName")));
        }

        ObservableList<Lecturer> listOfProducts = FXCollections.observableList(listProducts);

        return listOfProducts;
    }

    public boolean addUserPassword(String userName,String password) throws Exception{
        Connection conn = null;
        try {
            conn = SqliteHelper.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String sql1 = "UPDATE Users SET Password= "+"'"+password+"'"+"  WHERE  UserName ="+"'"+userName+"'"+" ;";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public String getUserPassword(String userName)throws Exception{
        Connection conn = SqliteHelper.getConn();
        String query = "SELECT Password FROM Users WHERE UserName LIKE "+"'"+userName+"'"+";";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        return rs.getString("Password");
    }
}



