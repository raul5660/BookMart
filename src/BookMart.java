import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by raulmartinez on 2/29/16.
 */
public class BookMart {

    private static Scanner reader = new Scanner(System.in);
    private static User authenticatedUser;
    private static ArrayList<Books> books;

    public static void main(String[] args){
        login();
    }
    private static void login() {
        reader = new Scanner(System.in);
        System.out.println("Login");
        System.out.print("\tUsername: ");
        String userName = reader.nextLine();
        System.out.print("\tPassword: ");
        String password = reader.nextLine();

        authenticatedUser = DatabaseController.Login(userName,password);

        if (authenticatedUser.isAuthenticated()) {
            if (authenticatedUser.getAccountType().equals("admin")) {
                adminConsole();
            } else {
                genres();
            }
        }
    }

    private static void adminConsole(){
        System.out.println("Admin Console");
        System.out.println("\t0) History\n\t1) Genres\n\t2) Return\n\t3) Logout");
        int tmp = reader.nextInt();
        if (tmp == 1) {
            genres();
        } else if (tmp == 2){
            returnBooks();
        } else if (tmp ==3){
            authenticatedUser = new User();
            login();
        }
    }

    private static void genres() {
        reader = new Scanner(System.in);
        System.out.println("Genres");
        for (int i = 0; i < DatabaseController.genres.length; i++) {
            System.out.println(String.format("\t%d) %s", i, DatabaseController.genres[i]));
        }
        System.out.println(String.format("\t%d) %s", DatabaseController.genres.length, "Logout"));
        System.out.print("Input: ");
        int genre = reader.nextInt();
        if (genre == DatabaseController.genres.length) {
            authenticatedUser = new User();
            login();
        } else {
            books = DatabaseController.getBooksByGenre(DatabaseController.genres[genre]);
            DisplayBooks();
        }
    }

    private static void DisplayBooks() {
        System.out.println(books.get(0).getGenre());
        for(int i = 0; i < books.size(); i++) {
            System.out.println(String.format("\t%d) %s", i,books.get(i).getName()));
        }
        System.out.println(String.format("\t%d) %s", books.size(), "Back"));
        System.out.print("Input: ");
        int input = reader.nextInt();
        if (input == books.size()) {
            genres();
        } else {
            System.out.println(String.format("Are you sure you want to checkout %s (Y/n):",books.get(input).getName()));
            String tmp = reader.nextLine();
            switch (tmp.toLowerCase()) {
                case "n":
                    break;
                case "no":
                    break;
                default:
                    DatabaseController.checkoutBook(authenticatedUser,books.get(input));
                    break;
            }
        }
    }

    private static void returnBooks()
    {
        ArrayList<Document> checkedOut = authenticatedUser.getBooksCheckedOut();
        int a;

        for (a = 0; a < checkedOut.size(); a++)
            System.out.println(a + ": " + checkedOut.get(a));

        System.out.println(a + ": Back");

        int input = reader.nextInt();

        if (input == a)
            adminConsole();
        else
            DatabaseController.returnBook(authenticatedUser, DatabaseController.documentToBooks(checkedOut.get(input)));

    }
}
