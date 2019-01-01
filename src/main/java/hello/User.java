package hello;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class User {

    public static Map<Integer, User> users = new HashMap<>();

    private Integer Id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String status;

    public User(){}

    public User(Integer id, String username, String password, String name, String surname, String status) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.status = status;
    }

    public User(Object[] forConstructor) throws IllegalAccessException {

        Field[] fields = User.class.getDeclaredFields();

        for (int i = 1; i < fields.length ; i++) {
            fields[i].set(this, forConstructor[i]);
        }
    }

    public static void printUsersMap(){
        System.out.println("\n-------------------------\n");
        for (Map.Entry<Integer, User> u : users.entrySet()){
            System.out.println(u.getKey() + " " + u.getValue().username + " " + u.getValue().password + " " + u.getValue().name + " " + u.getValue().surname + " " + u.getValue().status);
        }
        System.out.println("\n-------------------------\n");
    }

    public static void addUsersMapToJson(){
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File("dblog.json"), users);
            System.out.println("Users in HashMap have been backed up to dblog.json file.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void getUsersFromJson() throws IOException, IllegalAccessException {

        users.clear();
        JsonNode jsonNode = new ObjectMapper().readTree(new FileReader("dblog.json"));
        Field[] fields = User.class.getDeclaredFields();


        /*
        Integer id = jsonNode.get(String.valueOf(i)).get("id").asInt();
        String username = jsonNode.get(String.valueOf(i)).get("username").asText();
        String name = jsonNode.get(String.valueOf(i)).get("name").asText();
        String surname = jsonNode.get(String.valueOf(i)).get("surname").asText();
        String status = jsonNode.get(String.valueOf(i)).get("status").asText();
        users.put(jsonNode.get(String.valueOf(i)).get("id").asInt(), new hello.User(id,username,name,surname,status));
        */


        User gonnaBeAdded = null;
        Object[] forConstructor = null;
        for (int i = 1; i <= jsonNode.size(); i++) {
            forConstructor = new Object[fields.length];
            for (int j = 1; j < fields.length; j++) {
                if(fields[j].getType().getTypeName().equals("java.lang.Integer")){
                    forConstructor[j] = jsonNode.get(String.valueOf(i)).get(String.valueOf(fields[j].getName())).asInt();
                }
                else{
                    forConstructor[j] = jsonNode.get(String.valueOf(i)).get(String.valueOf(fields[j].getName())).asText();
                }
            }

            gonnaBeAdded = new User(forConstructor);
            users.put(i,gonnaBeAdded);
        }

    }

    public static Integer username(String username){
        for (Map.Entry<Integer, User> u : users.entrySet()){
            if( u.getValue().getUsername().equalsIgnoreCase(username)) {
                return (Integer) u.getValue().getId();
            }
        }
        return -1;
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;

        /*
        if (username.equalsIgnoreCase("heyyo")) {
            hello.User obj = this;
            Field field = super.getClass().getDeclaredField("id");
            field.setAccessible(true);
            Integer accessGetValuesParameter = (Integer) field.get(obj);
            System.out.println(accessGetValuesParameter);


            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = hello.ConnectionManager.con.prepareStatement("update users set username = ? where id = ?");
                preparedStatement.setString(1,username);
                preparedStatement.setInt(2,accessGetValuesParameter);
                preparedStatement.executeUpdate();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
        */
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
