package com.example.myapplication22.Database;

public class WorkerModel {
    private int workerId;
    private String workerName;
    private int salary;
    private String category;

    public WorkerModel() {
    }

    public WorkerModel(int workerId, String workerName, int salary,String category) {
        this.workerId = workerId;
        this.workerName = workerName;
        this.salary = salary;
        this.category=category;
    }

    public WorkerModel(String workerName, int salary,String category) {
        this.workerName = workerName;
        this.salary = salary;
        this.category=category;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
