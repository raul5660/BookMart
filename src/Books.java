/**
 * Created by raulmartinez on 3/7/16.
 */
public class Books {
    private String name, author, genre, id;
    private int quantity;

    public Books(int quantity, String genre, String author, String name, String id) {
        this.setQuantity(quantity);
        this.setGenre(genre);
        this.setAuthor(author);
        this.setName(name);
        this.setID(id);
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
