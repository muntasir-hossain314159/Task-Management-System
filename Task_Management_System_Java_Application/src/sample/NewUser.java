package sample;

//NewUser class stores the data from a ResultSet in GetNewUserList class
public class NewUser {

    private int ID;
    private String username = null;
    private String password = null;

    //Constructor
    public NewUser() {}

    //Constructor
    public NewUser(int ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
}
