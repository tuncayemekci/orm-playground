package hello;

import java.util.HashMap;
import java.util.Map;

public class User {

    public static Map<Integer, User> users = new HashMap<>();

    private Integer Id;
    private String username;
    private String name;
    private String surname;
    private String status;

    public User(){}

    public User(Integer id, String username, String name, String surname, String status) {
        Id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.status = status;
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
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
