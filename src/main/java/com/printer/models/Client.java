package com.printer.models;

public class Client {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Client(int id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Client(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEmpty() {
        return this.name == null || this.email == null || this.phone == null || this.address == null;
    }

    @Override
    public String toString() {
        return "Client{id=" + id + ", name='" + name + "', email='" + email + "', phone='" + phone + "', address='" + address + "'}";
    }
}
