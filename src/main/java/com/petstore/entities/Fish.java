package com.petstore.entities;

import com.petstore.entities.enums.FishLivEnv;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fish")
@PrimaryKeyJoinColumn(name = "animal_id")
public class Fish extends Animal {

    @Enumerated(EnumType.STRING)
    @Column(name = "living_env", nullable = false)
    private FishLivEnv livingEnv;

    public Fish() {}

    public Fish(Date birth, String couleur, FishLivEnv livingEnv) {
        super(birth, couleur);
        this.livingEnv = livingEnv;
    }

    public FishLivEnv getLivingEnv()                {
        return livingEnv;
    }

    public void setLivingEnv(FishLivEnv livingEnv)  {
        this.livingEnv = livingEnv;
    }


    @Override
    public String toString() {
        return
                "Fish{id=" + getId() +
                        ", birth=" + getBirth() +
                        ", couleur='" + getCouleur() +
                        "', livingEnv=" + livingEnv +
                        "}";
    }
}
