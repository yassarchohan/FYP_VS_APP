package com.example.yassarchohan.virtual_shopping_app;

/**
 * Created by Yassar chohan on 9/30/2017.
 */
public class Getter_methods {

    public int id;
    public String productname;
    public String productdesc;
    public  String prize;
    public double ratings;
    public String useremail;

    public Getter_methods(){

    }

    public Getter_methods(int id, String useremail, String prize, String productdesc, String productname) {
        this.id = id;
        this.useremail = useremail;
        this.prize = prize;
        this.productdesc = productdesc;
        this.productname = productname;
    }

    public Getter_methods(String str_user, String str_email, String str_contacts, String str_pass, String uid) {
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Getter_methods(String productname, String prize) {
        this.productname = productname;
        this.prize = prize;
    }

    public Getter_methods(String productname) {
        this.productname = productname;
    }

    public Getter_methods(int id, String productname, String productdesc, String prize, double ratings) {
        this.id = id;
        this.productname = productname;
        this.productdesc = productdesc;
        this.prize = prize;
        this.ratings = ratings;
    }

    public Getter_methods(int id, String productname, String productdesc, String prize) {
        this.id = id;
        this.productname = productname;
        this.productdesc = productdesc;
        this.prize = prize;
    }

    public Getter_methods(String productname, String productdesc, String prize) {
        this.productname = productname;
        this.productdesc = productdesc;
        this.prize = prize;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }
}
