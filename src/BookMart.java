import java.util.ArrayList;

/**
 * Created by raulmartinez on 2/29/16.
 */
public class BookMart {
    public static void main(String[] args){

        /*
        User user = new User(false, "raul","martinez", "user", "raul5660",new ArrayList<String>());
        boolean isCreated = DatabaseController.createUser(user, "Defense08");
        user.setAuthenticated(isCreated);
        if (user.isAuthenticated())
            System.out.println("successful");
          */

        User user = DatabaseController.Login("admin", "Defense08");

        System.out.println(user.getUserName());

        ArrayList<Books> testBooks =  DatabaseController.getBooks();

        System.out.println(testBooks.get(0).getName());

        if(DatabaseController.checkoutBook(user, testBooks.get(0)))
            System.out.println("Book added");
        else
            System.out.println("adding failed");
    }
}
