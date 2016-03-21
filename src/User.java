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
     * Name: getID
     * Type: Method
     * Arguments: NA
     * Description: returns the objects id property
     */
    public String getID() {
        return id;
    }

    /*
     * Name: setID
     * Type: Method
     * Arguments: String id
     * Description: Sets the objects id property
     */
    public void setID(String id) {
        this.id = id;
    }

    /*
     * Name: isAuthenticated
     * Type: Method
     * Arguments: NA
     * Description: returns the objects isAuthenticated property
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /*
     * Name: setAuthenticated
     * Type: Method
     * Arguments: boolean authenticated
     * Description: Sets the objects isAuthenticated property
     */
    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    /*
     * Name: getFirstName
     * Type: Method
     * Arguments: NA
     * Description: returns the objects firstName property
     */
    public String getFirstName() {
        return firstName;
    }

    /*
     * Name: setFirstName
     * Type: Method
     * Arguments: String firstName
     * Description: Sets the objects firstName property
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /*
     * Name: getLastName
     * Type: Method
     * Arguments: NA
     * Description: returns the objects lastName property
     */
    public String getLastName() {
        return lastName;
    }

    /*
     * Name: setLastName
     * Type: Method
     * Arguments: String lastName
     * Description: Sets the ojects lastName property
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*
     * Name: getAccountType
     * Type: Method
     * Arguments: NA
     * Description: returns the objects AccountType property
     */
    public String getAccountType() {
        return accountType;
    }

    /*
     * Name: setAccountType
     * Type: Method
     * Arguments: String accountType
     * Description: Sets the objects accountType property
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /*
     * Name: getMembershipType
     * Type: Method
     * Arguments: NA
     * Description: returns the objects membershipType property
     */
    public String getMembershipType() {
        return membershipType;
    }

    /*
     * Name: setMembershipType
     * Type: Method
     * Arguments: String membershipType
     * Description: sets the objects membershipType property
     */
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    /*
     * Name: getBooksCheckedOut
     * Type: Method
     * Arguments: NA
     * Description: returns the objects booksCheckOut property
     */
    public ArrayList<Document> getBooksCheckedOut() {
        return booksCheckedOut;
    }

    /*
     * Name: setBooksCheckOut
     * Type: Method
     * Arguments: ArrayList<Document> booksCheckOut
     * Description: sets the objects booksCheckOut property
     */
    public void setBooksCheckedOut(ArrayList<Document> booksCheckedOut) {
        this.booksCheckedOut = booksCheckedOut;
    }

    /*
     * Name: equals
     * Type: Overrided Method
     * Arguments: Object obj
     * Description: This method returns a boolean value if specific values are identical
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
