import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
 * Name: BookMart
 * Type: Class
 * Arguments: NA
 * Description: This class will be used to run the entire application
 */
public class BookMart {

    private static Scanner reader = new Scanner(System.in);
    private static User authenticatedUser;
    private static ArrayList<Books> books;
    private static long DAY_IN_MS = 1000 * 60 * 60 * 24; //number of miliseconds in a day

    /*
     * Name: main
     * Type: Method
     * Arguments: String[] args
     * Description: This methods is used to start the application
     */
    public static void main(String[] args){
        introduction();
    }

    /*
     * Name: introduction
     * Type: Method
     * Arguments: NA
     * Description: This method initiates the prompting process
     */
    private static void introduction()
    {
        boolean go = true;

        System.out.println("Welcome to BookMart!");
        System.out.println("Do you have a account with us? (Y/N)");
        while(go)
        {
            System.out.print("input:");

            String input = reader.next();

            switch (input) {
                case "n":
                case "N":
                    accountCreation();
                    go = false;
                    break;
                case "y":
                case "Y":
                    login();
                    go = false;
                    break;
                default:
                    go = true;
                    System.out.println("Incorrect input, please enter Y/N");
            }
        }

    }

    /*
     * Name: accountCreation
     * Type: Method
     * Arguments: NA
     * Description: this method prompts for account creation
     */
    private static void accountCreation()
    {
        String firstName;
        String lastName;
        String userName;
        String password = "";
        String accountType = "user";
        String membershipType = "";
        String payPalUserName = "";
        boolean lock = true;
        User tmpUser;
        int temp;


        System.out.println("Welcome to our account creation page we need to ask you a little about yourself to get you started");
        System.out.println("Please enter your First Name");
        firstName = reader.next();

        System.out.println("Please enter your Last Name");
        lastName = reader.next();

        System.out.println("Please enter your UserName");
        userName = reader.next();

        while (lock)
        {
            System.out.println("Please enter your Password");
            password = reader.next();

            System.out.println("Please enter your Password again");
            String tmpPassword = reader.next();

            if (password.equals(tmpPassword))
                lock = false;
            else
                System.out.println("Passwords do not match please try again");
        }

        do
        {
            System.out.println("Are you:");
            System.out.println("1: Student");
            System.out.println("2: Faculty");
            System.out.println("Enter 1/2: ");
            temp = reader.nextInt();
            if (temp == 1) {
                membershipType = "Student";
                System.out.println("Student accounts are $7.99 a month");
            } else {
                membershipType = "Faculty";
                System.out.println("Faculty accounts are $9.99 a month");
            }
        }while( !(temp == 1 || temp == 2) );

        tmpUser = new User(false, firstName, lastName, accountType, membershipType, userName, "null", new ArrayList<Document>());

        System.out.println("Please enter your Paypal Username for payment");
        payPalUserName = reader.next();

        if(DatabaseController.createUser(tmpUser, password, payPalUserName))
            System.out.println("Your account has been created");
        else
            System.out.println("error creating account");

        login();
    }

    /*
     * Name: login
     * Type: Method
     * Arguments: NA
     * Description: this method prompts for loggin in
     */
    private static void login() {
        reader = new Scanner(System.in);
        System.out.println("Login");
        System.out.print("\tUsername: ");
        String userName = reader.nextLine();
        System.out.print("\tPassword: ");
        String password = reader.nextLine();

        authenticatedUser = DatabaseController.Login(userName,password);

        if (authenticatedUser == null)
        {
            System.out.println("Incorrect username or password");
            login();
        }

        if (authenticatedUser.isAuthenticated()) {
            console();
        }
    }

    /*
     * Name:
     * Type: Method
     * Arguments: NA
     * Description: this method prompts for admin settings
     */
    private static void console() {
        if (authenticatedUser.getAccountType().equals("admin"))
        {
            System.out.println("Admin Console");
            System.out.println("\t0) History\n\t1) Genres\n\t2) Return\n\t3) Logout\n\t4) Exit");
            int tmp = reader.nextInt();
            if (tmp == 0) {
                history();
            } else if (tmp == 1) {
                genres();
            } else if (tmp == 2) {
                returnBooks();
            } else if (tmp == 3) {
                authenticatedUser = new User();
                login();
            } else if (tmp == 4) {
                System.exit(1);
            }
        }
        else
        {
            System.out.println("User Console");
            System.out.println("\t0) Genres\n\t1) Return\n\t2) Logout\n\t3) Exit");
            int tmp = reader.nextInt();
            if (tmp == 0) {
                genres();
            } else if (tmp == 1){
                returnBooks();
            } else if (tmp == 2){
                authenticatedUser = new User();
                login();
            } else if (tmp == 3){
                System.exit(1);
            }
        }
    }

    /*
     * Name: genres
     * Type: Method
     * Arguments: NA
     * Description: this method prompts for genre selection
     */
    private static void genres() {
        reader = new Scanner(System.in);
        System.out.println("Genres");
        for (int i = 0; i < DatabaseController.genres.length; i++) {
            System.out.println(String.format("\t%d) %s", i, DatabaseController.genres[i]));
        }
        System.out.println(String.format("\t%d) back\n\t%d) %s", DatabaseController.genres.length, DatabaseController.genres.length+1, "Logout"));
        System.out.print("Input: ");
        int genre = reader.nextInt();
        if (genre == DatabaseController.genres.length+1)
        {
            authenticatedUser = new User();
            login();
        }
        else if (genre == DatabaseController.genres.length)
        {
            console();
        }
        else
        {
            books = DatabaseController.getBooksByGenre(DatabaseController.genres[genre]);
            DisplayBooks();
        }
    }

    /*
     * Name: DisplayBooks
     * Type: Method
     * Arguments: NA
     * Description: this method prompts for book display of given genre
     */
    private static void DisplayBooks() {
        reader = new Scanner(System.in);
        System.out.println(books.get(0).getGenre());
        for(int i = 0; i < books.size(); i++) {
            System.out.println(String.format("\t%d) %s", i,books.get(i).getName()));
        }
        System.out.print(String.format("\t%d) %s", books.size(), "Back"));
        System.out.print("Input: ");
        int input = reader.nextInt();
        if (input == books.size()) {
            genres();
        } else {
            reader = new Scanner(System.in);
            System.out.println(String.format("Are you sure you want to checkout %s (Y/n):",books.get(input).getName()));
            String tmp = reader.next();
            switch (tmp.toLowerCase()) {
                case "n":
                    break;
                case "no":
                    break;
                default:
                    DatabaseController.checkoutBook(authenticatedUser,books.get(input));
                    break;
            }
            System.out.println();
            console();
        }
    }

    /*
     * Name: history
     * Type: Method
     * Arguments: NA
     * Description: this method prompts for history of checked out books
     */
    private static void history()
    {
        System.out.println("Enter the username of the user to see his/her purchase history");
        String input = reader.next();
        User tmpUser =  DatabaseController.getUser(input);

        System.out.println(input + " has checkout the following books.");

        //checks to make sure the user has a hstory to check
        if(tmpUser.getBooksCheckedOut().size() > 0)
        {
            for (Document doc : tmpUser.getBooksCheckedOut()) {
                Books book = DatabaseController.documentToBooks(doc);
                System.out.println("\tChecked Out: " + doc.getDate("dateCheckedOut") + " - " + book.getName());
            }
        }
        else
        {
            System.out.println("This user has no history");
        }
        System.out.println();
        console();
    }

    /*
     * Name: returnBooks
     * Type: Method
     * Arguments: NA
     * Description: this method prompts for book returns
     */
    private static void returnBooks()
    {
        ArrayList<Document> checkedOut = authenticatedUser.getBooksCheckedOut();
        Books book;
        int a;

        System.out.println("The following are books you have checked out that need to be returned.");

        //Loop though all books in history looking for what has not been returned
        for (a = 0; a < checkedOut.size(); a++)
        {
            if(!checkedOut.get(a).getBoolean("returned"))
            {
                //calculate the date the book should be returned by
                Date dueDate = checkedOut.get(a).getDate("dateCheckedOut");
                if(authenticatedUser.getMembershipType() != null)
                {
                    if (authenticatedUser.getMembershipType().equals("Student"))
                        dueDate = new Date(dueDate.getTime() + 7 * DAY_IN_MS);
                    else
                        dueDate = new Date(dueDate.getTime() + 14 * DAY_IN_MS);
                }

                book = DatabaseController.documentToBooks(checkedOut.get(a));
                System.out.println("\t" + a + ": Due: " + dueDate + " - " + book.getName());
            }
        }

        System.out.println("\t" + a + ": Back");

        int input = reader.nextInt();

        if (input == a)
            console();
        else {
            DatabaseController.returnBook(authenticatedUser, DatabaseController.documentToBooks(checkedOut.get(input)));
        }

        System.out.println();
        console();

    }
}
