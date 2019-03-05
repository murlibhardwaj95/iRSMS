package com.syon.isrms.Beans;

public class AddAccountBean {
    String username;
    String password;
    String photo;
    String profile_name;



    public AddAccountBean(String username, String password, String photo, String profile_name) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.profile_name = profile_name;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public String getProfile_name() {
        return profile_name;
    }
}
