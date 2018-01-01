package com.example.yassarchohan.virtual_shopping_app;

/**
 * Created by Yassar chohan on 12/20/2017.
 */
public class modelclass {

    private String title;
    private String messagee;
    private String email;

    public modelclass() {
    }

    public modelclass(String email, String title, String messagee) {
        this.email = email;
        this.title = title;
        this.messagee = messagee;
    }

    public modelclass(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessagee() {
        return messagee;
    }

    public void setMessagee(String messagee) {
        this.messagee = messagee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
