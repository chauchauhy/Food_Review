package com.example.initapp.model;

public class Order_List {
    private String orderId;
    private String order_content;
    private String price;
    private String order_res_id;
    private String  order_res_name;
    private String paymentID;
    private String finish;
    private String order_time;

    public Order_List(String orderId, String order_content, String price, String order_res_id, String order_res_name, String paymentID, String finish, String order_time) {
        this.orderId = orderId;
        this.order_content = order_content;
        this.price = price;
        this.order_res_id = order_res_id;
        this.order_res_name = order_res_name;
        this.paymentID = paymentID;
        this.finish = finish;
        this.order_time = order_time;
    }

    public Order_List() {
    }

    public String getOrder_content() {
        return order_content;
    }

    public void setOrder_content(String order_content) {
        this.order_content = order_content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrder_res_id() {
        return order_res_id;
    }

    public void setOrder_res_id(String order_res_id) {
        this.order_res_id = order_res_id;
    }

    public String getOrder_res_name() {
        return order_res_name;
    }

    public void setOrder_res_name(String order_res_name) {
        this.order_res_name = order_res_name;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order_List{" +
                "orderId='" + orderId + '\'' +
                ", order_content='" + order_content + '\'' +
                ", price='" + price + '\'' +
                ", order_res_id='" + order_res_id + '\'' +
                ", order_res_name='" + order_res_name + '\'' +
                ", paymentID='" + paymentID + '\'' +
                ", finish='" + finish + '\'' +
                ", order_time='" + order_time + '\'' +
                '}';
    }
}
