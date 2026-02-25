package com.petstore.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth", nullable = false)
    private Date birth;

    @Column(name = "couleur", nullable = false)
    private String couleur;

    @ManyToOne
    @JoinColumn(name = "petstore_id")
    private PetStore petStore;

    public Animal() {}

    public Animal(Date birth, String couleur) {
        this.birth   = birth;
        this.couleur = couleur;
    }

    public void setPetStore(PetStore petStore) {
        if (this.petStore != null && this.petStore != petStore) {
            this.petStore.getAnimals().remove(this);
        }
        this.petStore = petStore;
        if (petStore != null && !petStore.getAnimals().contains(this)) {
            petStore.getAnimals().add(this);
        }
    }

    public Long getId()                     { return id; }
    public void setId(Long id)              { this.id = id; }

    public Date getBirth()                  { return birth; }
    public void setBirth(Date birth)        { this.birth = birth; }

    public String getCouleur()              { return couleur; }
    public void setCouleur(String couleur)  { this.couleur = couleur; }

    public PetStore getPetStore()           { return petStore; }

    @Override
    public String toString() {

        return "Animal{id=" + id +
                ", birth=" + birth +
                ", couleur='" + couleur +
                "'}";
    }
}
