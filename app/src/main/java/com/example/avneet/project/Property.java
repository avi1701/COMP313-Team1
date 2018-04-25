package com.example.avneet.project;

import com.example.avneet.project.Backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avneet on 4/18/2018.
 */

public class Property
{
    int pic_id;
    int user_id;
    String address,city,province,postal_code,path,date,description;

    public Property()
    {


    }

    public Property(int pic_id,int user_id,String address,String city,String province,String postal_code,String path,String date,String description)
    {
       this.pic_id=pic_id;
       this.user_id=user_id;
       this.address=address;
       this.city=city;
       this.path=path;
       this.postal_code=postal_code;
       this.province=province;
       this.date=date;
       this.description=description;
    }

     public int getPic_id() {
        return pic_id;
     }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
