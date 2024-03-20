public class Main
{
    public static void main(String[] args) {
        DbFunctions dbFunctions = new DbFunctions();
        dbFunctions.connectToDb();
        dbFunctions.RefreshTasksTable();
        dbFunctions.InsertTask(5, "New 5", 12, 14);
        dbFunctions.PrintTasks();
    }
}
