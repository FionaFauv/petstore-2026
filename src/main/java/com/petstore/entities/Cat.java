package com.petstore.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cat")
@PrimaryKeyJoinColumn(name = "animal_id")
public class Cat extends Animal {

    @Column(name = "chip_id", nullable = false, unique = true)
    private String chipId;

    public Cat() {}

    public Cat(Date birth, String couleur, String chipId) {
        super(birth, couleur);
        this.chipId = chipId;
    }

    public String getChipId()               { return chipId; }
    public void setChipId(String chipId)    { this.chipId = chipId; }

    @Override
    public String toString() {
        return
                "Cat{id=" + getId() +
                        ", birth=" + getBirth() +
                        ", couleur='" + getCouleur() +
                        "', chipId='" + chipId +
                        "'}";
    }
}
