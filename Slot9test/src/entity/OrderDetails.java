package entity;

public class OrderDetails extends Entity<Integer>{

    private int order_detail_id;
    private int order_id;
    private int customer_id;
    private int product_id;
    private int quantity;
    private double total_price;

    public OrderDetails(){

    }
    public OrderDetails(Integer order_detail_id) {
        super(order_detail_id);
    }

    public OrderDetails( int order_detail_id, int order_id, int customer_id, int product_id, int quantity, double total_price) {
        super(order_detail_id);
        this.order_detail_id = order_detail_id;
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.total_price = total_price;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
