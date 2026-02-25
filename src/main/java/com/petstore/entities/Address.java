package com.petstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "city", nullable = false)
    private String city;

    public Address() {}

    public Address(String number, String street, String zipCode, String city) {
        this.number  = number;
        this.street  = street;
        this.zipCode = zipCode;
        this.city    = city;
    }

    public Long getId()                     { return id; }
    public void setId(Long id)              { this.id = id; }

    public String getNumber()               { return number; }
    public void setNumber(String number)    { this.number = number; }

    public String getStreet()               { return street; }
    public void setStreet(String street)    { this.street = street; }

    public String getZipCode()              { return zipCode; }
    public void setZipCode(String zipCode)  { this.zipCode = zipCode; }

    public String getCity()                 { return city; }
    public void setCity(String city)        { this.city = city; }


    @Override
    public String toString() {
        return
                "Address{id=" + id +
                        ", number='" + number +
                        "', street='" + street +
                        "', zipCode='" + zipCode +
                        "', city='" + city + "'}";
    }
}
