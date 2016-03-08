import java.util.ArrayList;

/**
 * Created by raulmartinez on 3/7/16.
 */
public class User {
    private boolean isAuthenticated = false;
    private String firstName, lastName, accountType, userName;
    ArrayList<String> booksCheckedOut;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User() { }

    public User(boolean isAuthenticated, String firstName, String lastName, String accountType, String userName, ArrayList<String> booksCheckedOut) {
        this.isAuthenticated = isAuthenticated;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        this.userName = userName;
        this.booksCheckedOut = booksCheckedOut;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ArrayList<String> getBooksCheckedOut() {
        return booksCheckedOut;
    }

    public void setBooksCheckedOut(ArrayList<String> booksCheckedOut) {
        this.booksCheckedOut = booksCheckedOut;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        User tmpUser = (User) obj;
        if (    !this.firstName.equals(tmpUser.getFirstName()) ||
                !this.lastName.equals(tmpUser.getLastName()) ||
                !this.userName.equals(tmpUser.getUserName()) ||
                !this.accountType.equals(tmpUser.getAccountType())){
            return false;
        }
        return true;
    }
}
