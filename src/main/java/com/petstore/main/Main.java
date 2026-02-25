package com.petstore.main;

import com.petstore.entities.*;
import com.petstore.entities.enums.FishLivEnv;
import com.petstore.entities.enums.ProdType;
import com.petstore.repository.PetStoreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstorePU");
        EntityManager em = emf.createEntityManager();
        PetStoreRepository repo = new PetStoreRepository(em);

        System.out.println("=== Démarrage de l'application PetStore ===\n");

        try {

            Address addr1 = new Address("12", "Rue des Lilas",       "75001", "Paris");
            Address addr2 = new Address("5",  "Avenue du Parc",      "69002", "Lyon");
            Address addr3 = new Address("30", "Boulevard de la Mer", "13001", "Marseille");

            PetStore store1 = new PetStore("Animaux du Cœur", "Alice Martin");
            PetStore store2 = new PetStore("La Maison des Bêtes", "Bob Dupont");
            PetStore store3 = new PetStore("Pet Paradise", "Clara Durand");

            store1.setAddress(addr1);
            store2.setAddress(addr2);
            store3.setAddress(addr3);

            Product croquettes  = new Product("FOOD-001",  "Croquettes Premium",     ProdType.FOOD,      25.99);
            Product collier     = new Product("ACC-001",   "Collier anti-puces",      ProdType.ACCESSORY, 12.50);
            Product litiere     = new Product("CLEAN-001", "Litière agglomérante",    ProdType.CLEANING,  9.90);
            Product aquarium    = new Product("ACC-002",   "Aquarium 50L",            ProdType.ACCESSORY, 89.00);
            Product antiparasite= new Product("CLEAN-002", "Spray antiparasitaire",   ProdType.CLEANING,  15.00);

            store1.addProduct(croquettes);
            store1.addProduct(collier);
            store1.addProduct(litiere);
            store2.addProduct(aquarium);
            store2.addProduct(croquettes);
            store3.addProduct(antiparasite);
            store3.addProduct(litiere);

            Fish nemo    = new Fish(new Date(), "Orange et blanc", FishLivEnv.SEA_WATER);
            Fish dorado  = new Fish(new Date(), "Doré",            FishLivEnv.SEA_WATER);
            Fish poisson = new Fish(new Date(), "Rouge",           FishLivEnv.FRESH_WATER);
            Fish carpe   = new Fish(new Date(), "Argenté",         FishLivEnv.FRESH_WATER);

            Cat felix    = new Cat(new Date(), "Noir et blanc", "CHIP-001");
            Cat luna     = new Cat(new Date(), "Roux",          "CHIP-002");
            Cat minou    = new Cat(new Date(), "Gris",          "CHIP-003");
            Cat caramel  = new Cat(new Date(), "Beige",         "CHIP-004");

            store1.addAnimal(felix);
            store1.addAnimal(luna);
            store1.addAnimal(nemo);
            store2.addAnimal(minou);
            store2.addAnimal(dorado);
            store2.addAnimal(poisson);
            store3.addAnimal(caramel);
            store3.addAnimal(carpe);

            System.out.println("--- Insertion en base de données ---");
            repo.saveAll(store1, store2, store3);
            System.out.println("Insertion réussie !\n");

            System.out.println("--- Requête : animaux de l'animalerie '" + store1.getName() + "' (id=" + store1.getId() + ") ---");
            List<Animal> animauxStore1 = repo.findAnimalsByPetStoreId(store1.getId());

            if (animauxStore1.isEmpty()) {
                System.out.println("Aucun animal trouvé.");
            } else {
                for (Animal a : animauxStore1) {
                    System.out.println("  -> " + a);
                }
            }

            System.out.println();

            System.out.println("--- Tous les animaux (toutes animaleries) ---");
            List<Animal> tousAnimaux = repo.findAllAnimals();
            tousAnimaux.forEach(a -> System.out.println("  -> " + a + " | PetStore : " + a.getPetStore().getName()));

            System.out.println();

            System.out.println("--- Toutes les animaleries ---");
            repo.findAllPetStores().forEach(s -> System.out.println("  -> " + s));

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            System.out.println("\n=== Fin de l'application ===");
        }
    }
}
