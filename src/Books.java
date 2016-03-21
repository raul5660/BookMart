/*
 * Name:
 * Type:
 * Arguments:
 * Description:
 */
public class Books {
    private String name, author, genre, id;
    private int quantity;

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public Books(int quantity, String genre, String author, String name, String id) {
        this.setQuantity(quantity);
        this.setGenre(genre);
        this.setAuthor(author);
        this.setName(name);
        this.setID(id);
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public String getID() {
        return id;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setID(String id) {
        this.id = id;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public String getName() {
        return name;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public String getAuthor() {
        return author;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public String getGenre() {
        return genre;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public int getQuantity() {
        return quantity;
    }

    /*
     * Name:
     * Type:
     * Arguments:
     * Description:
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
