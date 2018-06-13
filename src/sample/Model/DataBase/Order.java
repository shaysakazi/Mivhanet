package sample.Model.DataBase;

public class Order {
    private int id;
    private int producId;
    private String renterEmail;
    private String tenantEmail;

    public Order(int id, int producId, String renterEmail, String tenantEmail) {
        this.id = id;
        this.producId = producId;
        this.renterEmail = renterEmail;
        this.tenantEmail = tenantEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProducId() {
        return producId;
    }

    public void setProducId(int producId) {
        this.producId = producId;
    }

    public String getRenterEmail() {
        return renterEmail;
    }

    public void setRenterEmail(String renterEmail) {
        this.renterEmail = renterEmail;
    }

    public String getTenantEmail() {
        return tenantEmail;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }
}
