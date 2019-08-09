package com.example.initapp.model;

public class PaymentMethod {
    private String paymentID;
    private String paymentMethod;

    public PaymentMethod(String paymentID, String paymentMethod) {
        this.paymentID = paymentID;
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod() {
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "paymentID='" + paymentID + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
