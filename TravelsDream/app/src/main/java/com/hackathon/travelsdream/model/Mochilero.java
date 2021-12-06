package com.hackathon.travelsdream.model;

public class Mochilero {

    private int idM;
    private String Nick;
    private String name;
    private String lastName;
    private String email;
    private String mobile;
    private String passWord;

    public int getIdM() { return idM; }

    public void setIdM(int idM) { this.idM = idM; }

    public String getNick() { return Nick; }

    public void setNick(String nick) { Nick = nick; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }

    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getPassWord() { return passWord; }

    public void setPassWord(String passWord) { this.passWord = passWord; }


    public Mochilero() {

    }

    public Mochilero(String nick, String name, String lastName, String email, String mobile, String passWord) {
        this.Nick = nick;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.passWord = passWord;
    }
}
