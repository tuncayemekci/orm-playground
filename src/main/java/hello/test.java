package hello;

public class test {

    public static void main(String[] args) {

        ConnectionManager.getUsersTableFromDBToMap();
        ConnectionManager.addRandomUsersToDB(2);
        User.addUsersMapToJson();
        User.printUsersMap();


    }
}
