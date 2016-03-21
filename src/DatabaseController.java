import com.mongodb.*;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Date;
import static java.util.Arrays.asList;

/*
 * Name:
 * Type:
 * Arguments:
 * Description:
 */
public class DatabaseController {
    private static MongoDatabase db;
    public static String[] genres = {"Art", "Business & Economics", "Computer Science", "Design", "Education", "Law", "Mathematics", "Music", "Philosophy and Psychology"};

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    private static void Initialize() {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        db = mongoClient.getDatabase("BookMart");
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public static ArrayList<Books> getBooksByGenre(String genre) {
        Initialize();
        final ArrayList<Books> Books = new ArrayList<Books>();
        FindIterable<Document> iterable = db.getCollection("books").find(new Document("genre", genre));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Object tmp = document.get("author");
                String authors = "";
                if (tmp.getClass().equals(String.class)) {
                    authors = tmp.toString();
                } else {
                    StringBuilder sbuilder = new StringBuilder();
                    for (int i = 0; i < ((ArrayList) tmp).size(); i++) {
                        sbuilder.append(((ArrayList) tmp).get(i).toString());
                        if(((ArrayList) tmp).size()-1 != i){
                            sbuilder.append(", ");
                        }
                    }
                    authors = sbuilder.toString();
                }
                Books.add(new Books(
                        document.getInteger("quantity"),
                        document.getString("genre"),
                        authors,
                        document.getString("name"),
                        document.get("_id").toString()
                ));
            }
        });
        return Books;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public static User Login(String username, final String password) {
        Initialize();
        final User[] user = new User[1];
        FindIterable<Document> iterable = db.getCollection("users").find(new Document("userName", username));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                User tmpuser = new User();
                 if (document.getString("passWord").equals(password)){
                     user[0] =  new User(
                             true,
                             document.getString("firstName"),
                             document.getString("lastName"),
                             document.getString("accountType"),
                             document.getString("membershipType"),
                             document.getString("userName"),
                             document.get("_id").toString(),
                             ((ArrayList<Document>) document.get("booksRentedOut"))
                     );
                     user[0].setAuthenticated(true);
                 } else {
                     user[0] = new User();
                 }
            }
        });

        return user[0];
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public static ArrayList<Books> getBooks(){
        ArrayList<Books> Books = new ArrayList<Books>();
        for (int i = 0; i < genres.length; i++ ){
            ArrayList<Books> tmp = getBooksByGenre(genres[i]);
            for(int j = 0; j < tmp.size(); j++){
                Books.add(tmp.get(j));
            }
        }
        return Books;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public static boolean createUser(User user, String password) {
        Initialize();
        Document tmpUser = new Document();
        tmpUser.append("firstName", user.getFirstName());
        tmpUser.append("lastName", user.getLastName());
        tmpUser.append("userName", user.getUserName());
        tmpUser.append("passWord", password);
        tmpUser.append("accountType", user.getAccountType());
        tmpUser.append("membershipType", user.getMembershipType());
        tmpUser.append("booksRentedOut", asList());
        db.getCollection("users").insertOne(tmpUser);

        User tmpUser2 = Login(user.getUserName(), password);
        if (user.equals(tmpUser2)){
            return true;
        }
        return false;
    }

    //****************************************************************************************
    // -Takes in a String of a username
    // -returns a User object that corresponds with the username
    //****************************************************************************************

    public static User getUser(String userName)
    {
        Initialize();
        final User[] user = new User[1];
        FindIterable<Document> iterable = db.getCollection("users").find(new Document("userName", userName));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                user[0] =  new User(
                        true,
                        document.getString("firstName"),
                        document.getString("lastName"),
                        document.getString("accountType"),
                        document.getString("membershipType"),
                        document.getString("userName"),
                        document.get("_id").toString(),
                        ((ArrayList<Document>) document.get("booksRentedOut"))
                );
                user[0].setAuthenticated(false);
            }
        });

        return user[0];
    }

    //****************************************************************************************
    // -Takes in a Document
    // -returns a Books object that corresponds to that Document
    //****************************************************************************************

    public static Books documentToBooks(Document doc)
    {
        final Books[] book =  new Books[1];

        FindIterable<Document> iterable = db.getCollection("books").find(new Document("_id", doc.get("bookID")));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Object tmp = document.get("author");
                String authors = "";
                if (tmp.getClass().equals(String.class)) {
                    authors = tmp.toString();
                } else {
                    StringBuilder sbuilder = new StringBuilder();
                    for (int i = 0; i < ((ArrayList) tmp).size(); i++) {
                        sbuilder.append(((ArrayList) tmp).get(i).toString());
                        if(((ArrayList) tmp).size()-1 != i){
                            sbuilder.append(", ");
                        }
                    }
                    authors = sbuilder.toString();
                }
                book[0] = new Books(
                        document.getInteger("quantity"),
                        document.getString("genre"),
                        authors,
                        document.getString("name"),
                        document.get("_id").toString()
                );
            }
        });

        return book[0];
    }

    //****************************************************************************************
    // -Takes in a User and Book
    // -updates database table that the user has checkedout that book
    // -decreases quantity of book left in database
    //****************************************************************************************
    public static boolean checkoutBook(User user, Books book)
    {
        Date date = new Date();               //Start at current date
        int newQuantity;                      //the amount of book left after operation
        int rentCount = 0;                        //the number of books already rented by the user tht month
        ArrayList<Document> checkedOut = user.getBooksCheckedOut();
        Date now = new Date();


        Initialize();

        //Check that user exists
        FindIterable<Document> iterable = db.getCollection("users").find(new Document("_id", new ObjectId(user.getID())));

        if (iterable == null)
        {
            System.out.println("User does not exist");
            return false;
        }

        //Check that Book exist and is in stock
        iterable =  db.getCollection("books").find(new Document("_id", new ObjectId(book.getID())));

        if (iterable ==  null)
        {
            System.out.println("book does not exist");
            return false;
        }
        else if (iterable.first().getInteger("quantity") < 1)
        {
            System.out.printf("book not in stock");
            return false;
        }

        newQuantity = iterable.first().getInteger("quantity") - 1;

        //Checks that the user does not already have this book checked out
        for (Document doc : user.getBooksCheckedOut())
        {
            if ((doc.get("bookID")).equals(new ObjectId(book.getID())) && !(doc.getBoolean("returned")))
            {
                return false;
            }
        }

        //Check that the user has no exceeded their renting amount for the month
        for(Document doc : checkedOut)
        {
            if (doc.getDate("dateCheckedOut").getMonth() == now.getMonth() && !doc.getBoolean("returned"))
                rentCount++;
        }
        if (user.getMembershipType().equals("Student"))
        {
            if (rentCount > 0)
            {
                System.out.println("You can only check out 1 book a month");
                return false;
            }
        }
        else
        {
            if (rentCount > 3)
            {
                System.out.println("You can only check out 4 books a month");
                return false;
            }
        }


        //update database that user has checked out a book
        db.getCollection("users").updateOne(new Document("_id", new ObjectId(user.getID())),
                new Document("$push", new Document("booksRentedOut", new Document().append("bookID",
                        new ObjectId(book.getID())).append("dateCheckedOut", date).append("returned", false))));

        db.getCollection("books").updateOne(new Document("_id", new ObjectId(book.getID())), new Document("$set",
                new Document("quantity", newQuantity)));

        //##### need to consider a update method for users and books to be used here

        return true;
    }


    //****************************************************************************************
    // -Takes in a User and Book
    // -updates database table that the user has returned that book
    // -increases quantity of book left in database
    //****************************************************************************************
    public static boolean returnBook(User user, Books book)
    {
        int newQuantity;                            //the amount of book left after operation

        Initialize();

        //Check that user exists
        FindIterable<Document> iterable = db.getCollection("users").find(new Document("_id", new ObjectId(user.getID())));

        if (iterable == null)
        {
            System.out.println("User does not exist");
            return false;
        }

        //Check that Book exist and is in stock
        iterable =  db.getCollection("books").find(new Document("_id", new ObjectId(book.getID())));

        if (iterable ==  null)
        {
            System.out.println("book does not exist");
            return false;
        }

        newQuantity = iterable.first().getInteger("quantity") + 1;

        //Checks that the user does have this book checked out and has not returned it
        for (Document doc : user.getBooksCheckedOut())
        {
            if (!((doc.get("bookID")).equals(new ObjectId(book.getID())) && !(doc.getBoolean("returned"))))
            {
                return false;
            }
        }

        //update database that user has checked out a book
        db.getCollection("users").updateOne(new Document("_id", new ObjectId(user.getID())).append("booksRentedOut.bookID",
                new ObjectId(book.getID())), new Document("$set", new Document("booksRentedOut.$.returned", true)));

        db.getCollection("books").updateOne(new Document("_id", new ObjectId(book.getID())), new Document("$set",
                new Document("quantity", newQuantity)));

        return true;
    }


}
