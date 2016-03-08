import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static java.util.Arrays.asList;
import static java.util.Arrays.deepHashCode;

/**
 * Created by raulmartinez on 3/3/16.
 */
public class DataBase {
    private static MongoDatabase db;
    private static void Initialize() {
        MongoClient mongoClient = new MongoClient("192.168.244.137", 27017);
        db = mongoClient.getDatabase("BookMart");
    }
    public static ArrayList<Books> getBooks() {
        Initialize();
        ArrayList<Books> Books = new ArrayList<Books>();
        FindIterable<Document> iterable = db.getCollection("books").find();
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
                        document.getString("name")
                ));
            }
        });
        return Books;
    }
}
