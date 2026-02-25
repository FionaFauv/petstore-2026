package com.petstore.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "petstore")
public class PetStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manager_name", nullable = false)
    private String managerName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animals = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "petstore_product",
            joinColumns        = @JoinColumn(name = "petstore_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public PetStore() {}

    public PetStore(String name, String managerName) {
        this.name        = name;
        this.managerName = managerName;
    }

    public void addAnimal(Animal animal) {
        if (!this.animals.contains(animal)) {
            this.animals.add(animal);
            animal.setPetStore(this);
        }
    }

    public void removeAnimal(Animal animal) {
        if (this.animals.remove(animal)) {
            animal.setPetStore(null);
        }
    }

    public void addProduct(Product product) {
        if (!this.products.contains(product)) {
            this.products.add(product);
            product.getPetStores().add(this);
        }
    }

    public void removeProduct(Product product) {
        if (this.products.remove(product)) {
            product.getPetStores().remove(this);
        }
    }

    public void setAddress(Address address) {

        this.address = address;
    }

    public Long getId()                             {
        return id;
    }

    public void setId(Long id)                      {
        this.id = id;
    }

    public String getName()                         { return name; }
    public void setName(String name)                { this.name = name; }

    public String getManagerName()                  { return managerName; }
    public void setManagerName(String m)            { this.managerName = m; }

    public Address getAddress()                     { return address; }

    public List<Animal> getAnimals()                { return animals; }
    public void setAnimals(List<Animal> animals)    { this.animals = animals; }

    public List<Product> getProducts()              { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    @Override
    public String toString() {
        return
                "PetStore{id=" + id +
                        ", name='" + name +
                        "', managerName='" +
                        managerName +
                        "'}";
    }
}
