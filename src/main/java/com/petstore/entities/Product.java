package com.petstore.entities;

import com.petstore.entities.enums.ProdType;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "label", nullable = false)
    private String label;


    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProdType type;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToMany(mappedBy = "products")
    private List<PetStore> petStores = new ArrayList<>();

    public Product() {}

    public Product(String code, String label, ProdType type, double price) {
        this.code  = code;
        this.label = label;
        this.type  = type;
        this.price = price;
    }

    public void addPetStore(PetStore petStore) {
        if (!this.petStores.contains(petStore)) {
            this.petStores.add(petStore);
            petStore.addProduct(this);
        }
    }

    public void removePetStore(PetStore petStore) {
        if (this.petStores.remove(petStore)) {
            petStore.removeProduct(this);
        }
    }

    public Long getId()                         { return id; }
    public void setId(Long id)                  { this.id = id; }

    public String getCode()                     { return code; }
    public void setCode(String code)            { this.code = code; }

    public String getLabel()                    { return label; }
    public void setLabel(String label)          { this.label = label; }

    public ProdType getType()                   { return type; }
    public void setType(ProdType type)          { this.type = type; }

    public double getPrice()                    { return price; }
    public void setPrice(double price)          { this.price = price; }

    public List<PetStore> getPetStores()        { return petStores; }
    public void setPetStores(List<PetStore> p)  { this.petStores = p; }

    @Override
    public String toString() {
        return
                "Product{id=" + id +
                        ", code='" + code +
                        "', label='" + label +
                        "', type=" + type +
                        ", price=" + price +
                        "}";
    }
}
