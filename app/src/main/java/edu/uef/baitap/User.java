package edu.uef.baitap;

public class User {
    private int id;
    private String username;
    private String password;

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    private byte[] image;
    private String hoTen;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, String hoTen, byte[] image) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.image = image;
        this.hoTen = hoTen;
    }

    public User(String username, String password, String hoTen, byte[] image) {
        this.username = username;
        this.password = password;
        this.image = image;
        this.hoTen=hoTen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
