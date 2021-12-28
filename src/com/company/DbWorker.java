package com.company;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DbWorker {
    private static Connection conn = null;

    public void connect(String path) {
        try {
            String url = "jdbc:sqlite:" + path;
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void addVkRecord(VkData data) {
        var id = data.getId();
        var name = data.getName();
        var lastName = data.getLastName();
        var bigPhotoUrl = data.getBigPhotoUrl();
        var smallPhotoUrl = data.getSmallPhotoUrl();
        var city = data.getCity();
        var bDate = data.getbDate();
        var sql = String.format("INSERT INTO vkData VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                id, name, lastName, bigPhotoUrl, smallPhotoUrl, city, bDate);
        try {
            conn.prepareStatement(sql).executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Add error");
        }
    }

    public void dropTables() throws SQLException {
        try {
            conn.createStatement().execute("DROP TABLE students;\n");
        } catch (Exception e) {
            System.out.println("Drop error");
        }

        try {
            conn.createStatement().execute("DROP TABLE course;\n");
        } catch (Exception e) {
            System.out.println("Drop error");
        }

        try {
            conn.createStatement().execute("DROP TABLE themes;\n");
        } catch (Exception e) {
            System.out.println("Drop error");
        }

        try {
            conn.createStatement().execute("DROP TABLE tasks;\n");
        } catch (Exception e) {
            System.out.println("Drop error");
        }
    }

    public void createTables() throws SQLException {

        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS course(\n" +
                "    course_id integer not null primary key autoincrement,\n" +
                "    course_name text not null);");

        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS students(\n" +
                "        studentId integer not null primary key autoincrement,\n" +
                "        firstname      text    not null,\n" +
                "        lastname   text    not null,\n" +
                "        city      text    not null,\n" +
                "        birthdate date,\n" +
                "       bigImageUrl     text,\n" +
                "       smallImageUrl text,\n" +
                "        vkId      integer not null,\n" +
                "        sex    integer default 0 not null,\n" +
                "         course_id integer references course);");

        conn.createStatement().execute("CREATE TABLE themes(\n" +
                "    theme_id integer not null primary key autoincrement,\n" +
                "    course_id integer,\n" +
                "    student_id integer,\n" +
                "    theme_name text,\n" +
                "    solved_task integer,\n" +
                "    true_answers integer,\n" +
                "    score integer);");

        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS tasks(\n" +
                "                       task_id integer not null primary key autoincrement,\n" +
                "                       course_id integer,\n" +
                "                       student_id integer,\n" +
                "                       name text,\n" +
                "                       score integer);");

    }


    public LinkedHashMap<String, Integer> answersByTheme() throws SQLException {
        var out = new LinkedHashMap<String, Integer>();
        var sql = "SELECT theme_name, SUM(true_answers) FROM themes GROUP BY theme_name ORDER BY theme_id";
        var result = conn.createStatement().executeQuery(sql);
        while (result.next()) {
            out.put(result.getString(1), result.getInt(2));
        }
        return out;
    }

    public LinkedHashMap<String, Integer> tasksByTheme() throws SQLException {
        var out = new LinkedHashMap<String, Integer>();
        var sql = "SELECT theme_name, SUM(solved_task) FROM themes GROUP BY theme_name ORDER BY theme_id";
        var result = conn.createStatement().executeQuery(sql);
        while (result.next()) {
            out.put(result.getString(1), result.getInt(2));
        }
        return out;
    }


    public void insertIntoTable(ArrayList<Student> students) {
            for (var s: students) {
                var sql = String.format("INSERT INTO students (firstname, lastname, city, birthdate, bigImageUrl, smallImageUrl,vkId,sex, course_id) VALUES('%s', '%s', '%s', '%s', '%s', '%s', %s, %s, %s)",
                        s.getFirstName(), s.getLastName(), s.getCity(),s.getbDate(), s.getBigPhotoUrl(), s.getSmallPhotoUrl(), s.getVkId(), s.getSex(), 1);
                try {
                    conn.prepareStatement(sql).executeUpdate();
                }
                catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Add error");
                }
                for (var t: s.getCourseRecord("Ulearn java").getThemes()) {
                    var sqlTheme = String.format("INSERT INTO themes (course_id, student_id, theme_name, solved_task, true_answers, score) VALUES(%s, %s, '%s', %s, %s, %s)",
                            1, 2, t.getName(), t.getSolvedTasks(), t.getTrueAnswersCounter(), 0);
                    try {
                        conn.prepareStatement(sqlTheme).executeUpdate();
                    }
                    catch (Exception e) {
                        System.out.println(e);
                        System.out.println("Add error");
                    }
                }
            }
    }

        public static VkData getVkDataById(int id) throws SQLException {
            var sql = "SELECT * FROM vkData";
            String[] columns = new String[] {"vkID", "name", "lastName", "bigPhotoUrl", "smallPhotoUrl", "city", "bDate"};
            var st = conn.createStatement().executeQuery(sql);

            return new VkData(
                    st.getInt("vkID"),
                    st.getString("name"),
                    st.getString("lastName"),
                    st.getInt("sex"),
                    st.getString("bigPhotoUrl"),
                    st.getString("smallPhotoUrl"),
                    st.getString("city"),
                    st.getString("bDate")
            );
        }

        public static void addStudentRecord(String firstname, String lastname, String groupCode, int vkId, String CourseSubs) {


            var sql = String.format("INSERT INTO students VALUES('%s', '%s', '%s', '%s', '%s')",
                    firstname, lastname, groupCode, vkId, CourseSubs);

        }


}
