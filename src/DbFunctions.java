import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions
{
    public Connection connectToDb() {
        final String API_KEY = "";
        Connection connection = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(API_KEY);
            if(connection != null) {
                System.out.println("Connected to Database");            }
            else {
                System.out.println("Failed to connect");
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        return  connection;
    }

    public void RefreshTasksTable() {
        Statement statement;
        try
        {
            String query = "drop table tasks; create table tasks (taskID int,taskName varchar(255),startTime int,endtime int);";
            statement = connectToDb().createStatement();
            statement.executeUpdate(query);
            System.out.println("Tasks refreshed");
        }
        catch (Exception e) {

        }
    }

    public void InsertTask(int taskID, String taskName, int startTime, int endTime) {
        Statement statement;
        try
        {
            String query = String.format("insert into tasks(taskid, taskname, starttime, endtime) values(%d,'%s',%d,%d);"
                    , taskID, taskName, startTime, endTime);
            statement = connectToDb().createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void PrintTasks() {
        Statement statement;
        ResultSet results;
        try {
            String query = "select * from tasks";
            statement = connectToDb().createStatement();
            results = statement.executeQuery(query);
            while (results.next()) {
                System.out.println(results.getString("taskid")+" "+results.getString("taskname")
                        +" "+results.getString("starttime")+" "+results.getString("endtime"));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
