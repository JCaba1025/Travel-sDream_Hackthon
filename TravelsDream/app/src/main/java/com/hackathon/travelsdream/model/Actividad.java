package com.hackathon.travelsdream.model;

public class Actividad {

    private int idA;
    private String nick;
    private String name;
    private String departamento;
    private String municipio;
    private String place;
    private String descrip;
    private String picture;
    private String price;
    private String tipo;

    public int getIdA() { return idA; }

    public void setIdA(int idA) { this.idA = idA; }

    public String getNick() { return nick; }

    public void setNick(String nick) { this.nick = nick; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDepartamento() { return departamento; }

    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getMunicipio() { return municipio; }

    public void setMunicipio(String municipio) {this.municipio = municipio; }

    public String getPlace() { return place; }

    public void setPlace(String place) { this.place = place; }

    public String getDescrip() {return descrip; }

    public void setDescrip(String descrip) { this.descrip = descrip;  }

    public String getPicture() { return picture; }

    public void setPicture(String picture) { this.picture = picture; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) {this.tipo = tipo; }

    public Actividad() {
    }

    public Actividad(String nick, String name, String departamento, String municipio, String place, String descrip, String picture, String price, String tipo) {
        this.nick = nick;
        this.name = name;
        this.departamento = departamento;
        this.municipio = municipio;
        this.place = place;
        this.descrip = descrip;
        this.picture = picture;
        this.price = price;
        this.tipo = tipo;
    }
}
