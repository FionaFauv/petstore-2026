package com.petstore.repository;

import com.petstore.entities.Animal;
import com.petstore.entities.PetStore;
import com.petstore.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class PetStoreRepository {

    private final EntityManager em;

    public PetStoreRepository(EntityManager em) {

        this.em = em;
    }

    public void save(Object entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void saveAll(Object... entities) {
        em.getTransaction().begin();
        for (Object entity : entities) {
            em.persist(entity);
        }
        em.getTransaction().commit();
    }

    public PetStore findPetStoreById(Long id) {

        return em.find(PetStore.class, id);
    }

    public List<PetStore> findAllPetStores() {
        return em.createQuery("SELECT p FROM PetStore p", PetStore.class).getResultList();
    }

    public List<Animal> findAnimalsByPetStoreId(Long petStoreId) {
        TypedQuery<Animal> query = em.createQuery(
                "SELECT a FROM Animal a WHERE a.petStore.id = :petStoreId",
                Animal.class
        );
        query.setParameter("petStoreId", petStoreId);
        return query.getResultList();
    }

    public List<Animal> findAllAnimals() {
        return em.createQuery("SELECT a FROM Animal a", Animal.class).getResultList();
    }

    public List<Product> findAllProducts() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}
