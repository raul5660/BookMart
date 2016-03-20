import org.bson.Document;

import java.util.ArrayList;

/**
 * Created by raulmartinez on 3/7/16.
 */
public class User {
    private boolean isAuthenticated = false;
    private String firstName, lastName, accountType, userName, id;
    private String membershipType;                                  //Should be Student or Faculty
    private ArrayList<Document> booksCheckedOut;

    public User() { }

    public User(boolean isAuthenticated, String firstName, String lastName, String accountType, String membershipType, String userName, String id, ArrayList<Document> booksCheckedOut) {
        this.setAuthenticated(isAuthenticated);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAccountType(accountType);
        this.setMembershipType(membershipType);
        this.setUserName(userName);
        this.setID(id);
        this.setBooksCheckedOut(booksCheckedOut);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
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

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public ArrayList<Document> getBooksCheckedOut() {
        return booksCheckedOut;
    }

    public void setBooksCheckedOut(ArrayList<Document> booksCheckedOut) {
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
