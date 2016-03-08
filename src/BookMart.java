import java.util.ArrayList;

/**
 * Created by raulmartinez on 2/29/16.
 */
public class BookMart {
    public static void main(String[] args){
        ArrayList<Books> Books = DataBase.getBooks();
        User loggedIn = DataBase.Login("admin","Defense08");

        System.out.println(loggedIn.isAuthenticated());
    }
}
