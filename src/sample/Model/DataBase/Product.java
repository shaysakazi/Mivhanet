package sample.Model.DataBase;

/**
 * Created by IL984626 on 30/12/2017.
 */
public class    Product {
    private int id;
    private String name;
    private double price;
    private int donation;
    private int swap;
    private int available;
    private double rating;
    private String category;
    private String userEmail;


    public Product(int id, String name, double price, int donation, int swap, int available, double rating, String category, String userEmail) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.donation = donation;
        this.swap = swap;
        this.available = available;
        this.rating = rating;
        this.userEmail = userEmail;
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDonation() {
        return donation;
    }

    public void setDonation(int donation) {
        this.donation = donation;
    }

    public int getSwap() {
        return swap;
    }

    public void setSwap(int swap) {
        this.swap = swap;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
