package sample.Model.DataBase;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Idosakazi on 08/01/2018.
 */
public class ProductShow{

    private SimpleIntegerProperty id;
    private SimpleStringProperty category;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty donation;
    private SimpleIntegerProperty swap;


    public ProductShow(int id ,String category, String name, double price, int swap, int donation){
        this.id = new SimpleIntegerProperty(id);
        this.category = new SimpleStringProperty(category);
        this.name=new SimpleStringProperty(name) ;
        this.price = new SimpleDoubleProperty(price);
        this.swap = new SimpleIntegerProperty(swap);
        this.donation = new SimpleIntegerProperty(donation);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getDonation() {
        return donation.get();
    }

    public void setDonation(int donation) {
        this.donation.set(donation);
    }

    public int getSwap() {
        return swap.get();
    }

    public void setSwap(int swap) {
        this.swap.set(swap);
    }

}
