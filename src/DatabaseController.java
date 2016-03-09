import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import org.bson.types.ObjectId;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static java.util.Arrays.asList;
import static java.util.Arrays.deepHashCode;
import static com.mongodb.client.model.Filters.*;

/**
 * Created by raulmartinez on 3/3/16.
 */
public class DatabaseController {
    private static MongoDatabase db;
    public static String[] genres = {"Art", "Business & Economics", "Computer Science", "Design", "Education", "Law", "Mathematics", "Music", "Philosophy and Psychology"};

    private static void Initialize() {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        db = mongoClient.getDatabase("BookMart");
    }

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

    public static User Login(String username, final String password) {
        Initialize();
        final User[] user = new User[1];
        FindIterable<Document> iterable = db.getCollection("users").find(new Document("userName", username));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                User tmpuser = new User();
                 if (document.getString("passWord").equals(password)){
                     tmpuser =  new User(
                             true,
                             document.getString("firstName"),
                             document.getString("lastName"),
                             document.getString("accountType"),
                             document.getString("userName"),
                             document.get("_id").toString(),
                             ((ArrayList<String>) document.get("booksRentedOut"))
                     );
                 }
                user[0] = tmpuser;
            }
        });

        return user[0];
    }

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

    public static boolean createUser(User user, String password) {
        Initialize();
        Document tmpUser = new Document();
        tmpUser.append("firstName", user.getFirstName());
        tmpUser.append("lastName", user.getLastName());
        tmpUser.append("userName", user.getUserName());
        tmpUser.append("passWord", password);
        tmpUser.append("accountType", user.getAccountType());
        tmpUser.append("booksRentedOut", asList());
        db.getCollection("users").insertOne(tmpUser);

        User tmpUser2 = Login(user.getUserName(), password);
        if (user.equals(tmpUser2)){
            return true;
        }
        return false;
    }

    //****************************************************************************************
    // -Takes in a User and Book
    // -updates database table that the user has checkedout that book
    // -decreases quantity of book left in database
    //****************************************************************************************
    public static boolean checkoutBook(User user, Books book) {
        final long DAY_IN_MS = 1000 * 60 * 60 * 24; //number of miliseconds in a day
        Date returnDate = new Date();               //Start at current date
        int newQuantity;                            //the amount of book left after operation

        Initialize();

        //Check that user exists
        FindIterable<Document> iterable = db.getCollection("users").find(new Document("_id", new ObjectId(user.getID())));
        if (iterable.first() == null) {
            System.out.println("User says fuck you");
            return false;
        }
        /* Start of error checking for duplicate rentals
        else
        {
            ArrayList test = (ArrayList) iterable.first().get("booksRentedOut");
        //     BasicDBObject test2 = (BasicDB) test.get(0);
            System.out.println(test.get(0).getClass(Class<BasicDBObject>));
        }
        */
        //Check that Book exist
        iterable = db.getCollection("books").find(new Document("_id", new ObjectId(book.getID())));

        if (iterable.first() == null) {
            System.out.println("book says fuck you at id " + book.getID());
            return false;
        }

        newQuantity = iterable.first().getInteger("quantity") - 1;

        //calculate date the book must be returned
        /*  NEED TO IMPLEMENT MEMBERSHIP TYPE
        if(user.getAccountType() == )
            returnDate = new Date(returnDate.getTime() + 7 * DAY_IN_MS);
        else
            returnDate = new Date(returnDate.getTime() + 14 * DAY_IN_MS);
        */
        //update database that user has checked out a book

        db.getCollection("users").updateOne(new Document("_id", new ObjectId(user.getID())),
                new Document("$set", new Document("booksRentedOut", asList(new Document("bookID",
                        new ObjectId(book.getID())).append("dueDate", returnDate).append("returned", false)))));

        db.getCollection("books").updateOne(new Document("_id", new ObjectId(book.getID())), new Document("$set",
                new Document("quantity", newQuantity)));

        //##### need to consider a update method for users and books to be used here

        return true;
    }

}
