import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static java.util.Arrays.asList;

/**
 * Created by raulmartinez on 3/3/16.
 */
public class DataBase {
    public DataBase() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");
    }
    public void update() {

    }
    public void read(String Collection, int ID) {

    }
    public void delete() {

    }
}
