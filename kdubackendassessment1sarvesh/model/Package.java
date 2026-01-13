package com.example.kdubackendassessment1sarvesh.model;

public class Package {
    static int idSet=1;
    public static void increment(){
        idSet+=1;
    }
    String id;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = String.valueOf(idSet);
        Package.increment();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    String destination;
    double weight;
    String status;
    String deliveryType;

    public Package(String deliveryType, String destination,double weight) {
        this.setId();
        this.deliveryType = deliveryType;
        this.destination = destination;
        this.weight = weight;
    }


}
