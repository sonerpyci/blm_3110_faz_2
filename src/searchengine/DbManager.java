package searchengine;


import java.sql.*;


public class DbManager {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/BLM_3110";
    static final String USER = "root";
    static final String PASS = "";

    private Connection conn;


    public void init(){
        try {
            Class.forName(JDBC_DRIVER);
            this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet executeQuery (String query) {
        ResultSet resultSet = null;
        try{
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet executeOperation (String query) {
        ResultSet resultSet = null;
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = stmt.getGeneratedKeys();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public Connection getConn() {
        return conn;
    }

}
