package com.company;
import java.sql.*;

public class DbWorker {
        private static Connection conn = null;

        public static void connect() {
            try {
                String url = "jdbc:sqlite:identifier.sqlite";
                conn = DriverManager.getConnection(url);
                System.out.println("Connection to SQLite has been established.");

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
                    id, name, lastName,bigPhotoUrl, smallPhotoUrl, city, bDate);
            try {
                conn.prepareStatement(sql).executeQuery();
            }
            catch (Exception e) {
                System.out.println(e);
                System.out.println("Add error");
            }
        }

        public static String getVkDataById(int id) throws SQLException {
            var sql = "SELECT * FROM vkData WHERE vkID = " + id;
            String[] columns = new String[] {"vkID", "name", "lastName", "bigPhotoUrl", "smallPhotoUrl", "city", "bDate"};
            var statement = conn.prepareStatement(sql, columns);
            var st = statement.executeQuery();
            System.out.println(st.next());
            return st.getString("name");
//            return new VkData(
//                    st.getInt("vkID"),
//                    st.getString("name"),
//                    st.getString("lastName"),
//                    st.getString("bigPhotoUrl"),
//                    st.getString("smallPhotoUrl"),
//                    st.getString("city"),
//                    st.getString("bDate")
//            );
        }

}
