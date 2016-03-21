/*
 * Name: Books
 * Type: Class
 * Arguments: NA
 * Description: This class will be used to create objects to represent books data
 */
public class Books {
    // Attributes to be used within the class
    private String name, author, genre, id;
    private int quantity;

    /*
     * Name: Books
     * Type: Constructor
     * Arguments: int quantity, String genre, String author, String name, String id
     * Description: This constructor will be used to create a Book object with all attributes defined
     */
    public Books(int quantity, String genre, String author, String name, String id) {
        this.setQuantity(quantity);
        this.setGenre(genre);
        this.setAuthor(author);
        this.setName(name);
        this.setID(id);
    }

    /*
     * Name: getID
     * Type: Method
     * Arguments: NA
     * Description: Returns the objects id property
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
     * Name: getName
     * Type: Method
     * Arguments: NA
     * Description: Returns the objects name property
     */
    public String getName() {
        return name;
    }

    /*
     * Name: setName
     * Type: Method
     * Arguments: String name
     * Description: Sets the objects name property
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
    * Name: getAuthor
    * Type: Method
    * Arguments: NA
    * Description: Returns the objects author property
    */
    public String getAuthor() {
        return author;
    }

    /*
    * Name: setAuthor
    * Type: Method
    * Arguments: String author
    * Description: Sets the objects author property
    */
    public void setAuthor(String author) {
        this.author = author;
    }

    /*
     * Name: getGenre
     * Type: Method
     * Arguments: NA
     * Description: Returns the objects genre property
     */
    public String getGenre() {
        return genre;
    }

    /*
     * Name: setGenre
     * Type: Method
     * Arguments: String genre
     * Description: Sets the objects genre property
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /*
     * Name: getQuantity
     * Type: Method
     * Arguments: NA
     * Description: Returns the objects quantity property
     */
    public int getQuantity() {
        return quantity;
    }

    /*
     * Name: setQuantity
     * Type: Method
     * Arguments: int quantity
     * Description: Sets the objects quanitity property
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
