package com.test.compass.dto;

public class Client {
    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private String postalZip;
    private String address;

    public Client(Integer id, String name, String lastname, String email, String postalZip, String address) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.postalZip = postalZip;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPostalZip() {
        return postalZip;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", postalZip='" + postalZip + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
