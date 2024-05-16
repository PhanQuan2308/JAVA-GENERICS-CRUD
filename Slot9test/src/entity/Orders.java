package entity;

import java.util.Date;

public class Orders extends Entity<Integer>{

    private int order_id;
    private int customer_id;
    private int product_id;
    private Date order_date;
    private String payment_type;
    private String order_status;


    public Orders(){

    }
    public Orders(Integer order_id) {
        super(order_id);
    }

    public Orders( int order_id, int customer_id, int product_id, Date order_date, String payment_type, String order_status) {
        super(order_id);
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.order_date = order_date;
        this.payment_type = payment_type;
        this.order_status = order_status;
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

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
