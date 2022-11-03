package com.example.myapplication22.Database;

import android.provider.BaseColumns;

public class CustomerDetails {

    public CustomerDetails(int anInt, String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {

    }

    public static class Customer implements BaseColumns {

        public static final String TABLE_NAME = "customers";
        public static int userId;

        public static String getTableName() {
            return TABLE_NAME;
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

        public static String getPassword() {
            return password;
        }

        public static String getRe_password() {
            return re_password;
        }

        public static String getAddress() {
            return address;
        }


        public static  String first_name = "f_name";
        public static  String last_name = "l_name";
        public static  String email = "email";
        public static  String mobile = "m_number";
        public static  String password = "password";
        public static  String re_password = "re_password";
        public static  String address = "address";

    }
}
