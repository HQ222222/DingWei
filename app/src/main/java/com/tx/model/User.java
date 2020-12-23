package com.tx.model;

import org.litepal.crud.DataSupport;

public class User extends DataSupport {

    private String name;
    private String password;
    private  byte[] headshot;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public byte[] getHeadshot() {
        return headshot;
    }

    public void setHeadshot(byte[] headshot) {
        this.headshot = headshot;
    }
}
