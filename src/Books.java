/**
 * Created by raulmartinez on 3/7/16.
 */
public class Books {
    private String name, author, genre;
    private int quantity;

    public Books(int quantity, String genre, String author, String name) {
        this.quantity = quantity;
        this.genre = genre;
        this.author = author;
        this.name = name;
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
