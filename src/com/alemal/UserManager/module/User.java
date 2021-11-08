package com.alemal.UserManager.module;

import java.io.Serializable;

public class User implements Serializable {
    public int id;
    public String firstname;
    public String lastname;
    public String phoneNumber;
    public String email;
    public String address;
    public String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
