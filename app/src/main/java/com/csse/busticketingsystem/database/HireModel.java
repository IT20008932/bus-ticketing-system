package com.example.myapplication22.Database;

public class HireModel {
    private int id;
    private String CustomerName;
    private int phone;
    private String email;
    private String location;
    private String date;
    private int duration;
    private String description;
    //private List<WorkerModel> worker=new ArrayList<>();

    public HireModel() {
    }

    public HireModel(int id, String customerName, int phone, String email, String location, String date, int duration, String description) {
        this.id = id;
        CustomerName = customerName;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.date = date;
        this.duration = duration;
        this.description = description;

    }

    public HireModel(String customerName, int phone, String email, String location,
                     String date, int duration, String description) {
        CustomerName = customerName;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.date = date;
        this.duration = duration;
        this.description = description;
    }

//    public HireModel(int id, String customerName, int phone, String email, String location, String date, int duration, String description) {
//        this.id = id;
//        //this.worker_hire_id = worker_hire_id;
//        CustomerName = customerName;
//        this.phone = phone;
//        this.email = email;
//        this.location = location;
//        this.date = date;
//        this.duration = duration;
//        this.description = description;
//    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
