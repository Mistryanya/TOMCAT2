package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//   Regex for removing square brackets:  [\[\]]


/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {
    private final String databaseName;

    //Constructor filename.db
    public Connect(String DatabaseName) {
        databaseName = DatabaseName;
    }

    String contentPath = "src/main/java/Database/db";
    String url = "jdbc:sqlite:" + contentPath + "/";

    public Connection openConnection(){
        try {
            return DriverManager.getConnection(url+databaseName);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void createNewTable(String tableName, String columns) throws SQLException {
        Connection conn = openConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("create table " +tableName+"("+columns+");");
        conn.close();
    }

    public void dropTable(String tableName) throws SQLException{
        Connection conn = openConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("drop table "+tableName+";");
        conn.close();
    }

    //Full Query
    public ArrayList<String[]> query(String sqlQuery, String[] columns) throws SQLException{
        Connection conn = openConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        ArrayList<String[]> temp = new ArrayList<>();
        int row = 0;
        String[] temp2;

        while (rs.next()) {
            temp2 = new String[columns.length];
            for (int i= 0;i<columns.length;i++){
                temp2[i] = rs.getString(columns[i]);
                row = row +1;
            }
            temp.add(temp2);
        }
        return temp;
    }

    //Quick Query
    public ArrayList<String[]> query(String[] columns, String tableName, String Filter) throws SQLException{
        Connection conn = openConnection();
        Statement stmt = conn.createStatement();
        String Scolumns = Arrays.toString(columns).replaceAll("[\\[\\]]","");
        ResultSet rs;

        //if Filter equals * print all results
        if(Filter.equals("*")){
            rs = stmt.executeQuery("select " + Scolumns + " from " + tableName +";");
        }else {
            rs = stmt.executeQuery("select " + Scolumns + " from " + tableName + " where " + Filter + ";");
        }
        int row = 0;
        ArrayList<String[]> temp = new ArrayList<>();
        String[] temp2;

        while (rs.next()) {
            temp2 = new String[columns.length];
            for (int i= 0;i<columns.length;i++){
                temp2[i] = rs.getString(columns[i]);
                row = row +1;
            }
            temp.add(temp2);
        }
        return temp;
    }

    //Full insert
    public void insert(String sqlInsert) throws SQLException {
        Connection conn = openConnection();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sqlInsert);

        conn.close();
    }

    //quick insert ONLY accepts STRINGS as values
    public void insert(String tableName, String[] columns, String[] values) throws SQLException {
        Connection conn = openConnection();
        Statement stmt = conn.createStatement();

        String Scolumns = Arrays.toString(columns).replaceAll("[\\[\\]\"']","");
        String Svalues = Arrays.toString(values).replaceAll("[\\[\\]]","").replaceAll("[,]","\", \"").replaceAll("[\"][ ]","\"");

        System.out.println("insert into "+tableName+"("+ Scolumns +") values(\""+ Svalues +"\");");

        stmt.executeUpdate("insert into "+tableName+"("+ Scolumns +") values(\""+ Svalues +"\");");
        conn.close();
    }

    /*Debugging Usage only*/
    public static void main(String[] args) throws SQLException {

        Connect testObject = new Connect("Accounts.db");
        Random rnd = new Random();
        ArrayList<String[]> result;
        int id = rnd.nextInt(99999);

        //String[] columnsFor... = {"UserName","HashedPassword"};


        //testObject.insert("insert into Accounts(UserName, HashedPassword, id) values(\"TestCase3\", \"Unhashed pass\","+id+")");
        //result = testObject.query("select "+ Arrays.toString(columnsForIDS).replaceAll("[\\[\\]]","") +" from IDS;", columnsForIDS);
        //System.out.println(result.get(0)[0]);

        //testObject.insert("test", new String[]{"normal"}, new String[]{"3"});

        //result = testObject.query("select "+Arrays.toString(columnsFortest).replaceAll("[\\[\\]]","") +" from test;", columnsFortest);
        //System.out.println(result.get(0)[0]);

        //result = testObject.query(columnsFortest,"test","*");
        //System.out.println(result.get(0)[0]);
    }
}
