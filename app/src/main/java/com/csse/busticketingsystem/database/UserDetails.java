package com.example.myapplication22.Database;

import android.provider.BaseColumns;

public class UserDetails {

    private int userId;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile;
    private String workArea;
    private String password;
    private String repassword;
    private String address;
    private String experience;
    private String category;
    private int salary;

    public UserDetails() {
    }

    public UserDetails(int userId, String first_name, String last_name, String email, String mobile, String workArea, String password, String repassword, String address, String experience, String category, int salary) {
        this.userId = userId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile = mobile;
        this.workArea = workArea;
        this.password = password;
        this.repassword = repassword;
        this.address = address;
        this.experience = experience;
        this.category = category;
        this.salary = salary;
    }

    public UserDetails(String first_name, String last_name, String email, String mobile, String workArea, String password, String repassword, String address, String experience, String category, int salary) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile = mobile;
        this.workArea = workArea;
        this.password = password;
        this.repassword = repassword;
        this.address = address;
        this.experience = experience;
        this.category = category;
        this.salary = salary;
    }

    public UserDetails(int userId, String first_name, String last_name, String email, String mobile, String workArea, String password, String repassword, String address, String experience, String category) {
        this.userId = userId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile = mobile;
        this.workArea = workArea;
        this.password = password;
        this.repassword = repassword;
        this.address = address;
        this.experience = experience;
        this.category = category;
    }

    public UserDetails(String first_name, String last_name, String email, String mobile, String workArea, String password, String repassword, String address, String experience, String category) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile = mobile;
        this.workArea = workArea;
        this.password = password;
        this.repassword = repassword;
        this.address = address;
        this.experience = experience;
        this.category = category;
    }

//    public UserDetails(int userId,String first_name, String last_name, String email, String mobile, String workArea, String experience, String category) {
//        this.userId = userId;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.email = email;
//        this.mobile = mobile;
//        this.workArea = workArea;
//        this.experience = experience;
//        this.category = category;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public static class User implements BaseColumns{



        public static final String TABLE_NAME = "users";



        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getWorker_id() {
            return worker_id;
        }

        public static String getFirst_name() {
            return first_name;
        }

        public static String getLast_name() {
            return last_name;
        }

        public static String getEmail() {
            return email;
        }

        public static String getMobile() {
            return mobile;
        }

        public static String getWorkArea() {
            return workArea;
        }

        public static String getPassword() {
            return password;
        }

        public static String getRe_password() {
            return re_password;
        }

        public static String getAddress() {
            return address;
        }

        public static String getExperience() {
            return experience;
        }

        public static String getCategory() {
            return category;
        }

//        public static String getSalary() {
//            return salary;
//        }

        public static  String worker_id = "id";
        public static  String first_name = "f_name";
        public static  String last_name = "l_name";
        public static  String email = "email";

        public static String getPro_image() {
            return pro_image;
        }

        public static void setPro_image(String pro_image) {
            User.pro_image = pro_image;
        }

        public static  String mobile = "m_number";
        public static  String workArea = "work_area";
        public static  String password = "password";
        public static  String re_password = "re_password";
        public static  String address = "address";
        public static  String experience = "experience";
        public static  String category = "category";
        public static String salary= "salary";
        public static String pro_image = "pro_image";

    }


}

