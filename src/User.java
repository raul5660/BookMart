import org.bson.Document;
import java.util.ArrayList;

/*
 * Name: User
 * Type: Class
 * Arguments: NA
 * Description: This class will be used to create objects to represent and hold user database
 */
public class User {
    // Attributes to be used within the class
    private boolean isAuthenticated = false;
    private String firstName, lastName, accountType, userName, id;
    private String membershipType;                                  //Should be Student or Faculty
    private ArrayList<Document> booksCheckedOut;

    /*
     * Name: User
     * Type: Constructor
     * Arguments: NA
     * Description: This is an empty constructor to create a user object without data
     */
    public User() { }

    /*
     * Name: User
     * Type: Constructor
     * Arguments: boolean isAuthenticated, String firstName, String lastName, String accountType, String membershipType, String userName, String id, ArrayList<Document> booksCheckedOut
     * Description: This constructor will be used to create a user object with all attributes defined
     */
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

    /*
     * Name: getUserName
     * Type: Method
     * Arguments: NA
     * Description: returns the objects userName property
     */
    public String getUserName() {
        return userName;
    }

    /*
     * Name: setUserName
     * Type: Method
     * Arguments: String userName
     * Description: Sets the objects userName property
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public String getID() {
        return id;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setID(String id) {
        this.id = id;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public String getFirstName() {
        return firstName;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public String getLastName() {
        return lastName;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public String getAccountType() {
        return accountType;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public String getMembershipType() {
        return membershipType;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public ArrayList<Document> getBooksCheckedOut() {
        return booksCheckedOut;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setBooksCheckedOut(ArrayList<Document> booksCheckedOut) {
        this.booksCheckedOut = booksCheckedOut;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
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
