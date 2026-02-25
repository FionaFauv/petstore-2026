# PetStore – TP Eval JPA / Hibernate

## Auteur
> Ajouter votre nom et prénom ici

---

## Prérequis

- Java 17+
- Maven 3.8+
- MySQL 8+

---

## Installation & exécution

### 1. Créer la base de données MySQL

```sql
CREATE DATABASE IF NOT EXISTS petstore CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

> Hibernate s'occupe automatiquement de créer toutes les tables grâce au paramètre `hibernate.hbm2ddl.auto=update`.

### 2. Configurer les identifiants MySQL

Éditer le fichier `src/main/resources/META-INF/persistence.xml` et modifier si nécessaire :

```xml
<property name="jakarta.persistence.jdbc.user"     value="root"/>
<property name="jakarta.persistence.jdbc.password" value="root"/>
```

### 3. Compiler et exécuter

```bash
# Compiler
mvn clean package

# Exécuter
java -jar target/petstore-1.0-SNAPSHOT-jar-with-dependencies.jar
```

---

## Structure du projet

```
src/
└── main/
    ├── java/com/petstore/
    │   ├── entities/
    │   │   ├── enums/
    │   │   │   ├── FishLivEnv.java     # Enum : FRESH_WATER / SEA_WATER
    │   │   │   └── ProdType.java       # Enum : FOOD / ACCESSORY / CLEANING
    │   │   ├── Address.java            # Entité adresse
    │   │   ├── Animal.java             # Entité parente (JOINED inheritance)
    │   │   ├── Cat.java                # Entité chat
    │   │   ├── Fish.java               # Entité poisson
    │   │   ├── PetStore.java           # Entité animalerie
    │   │   └── Product.java            # Entité produit
    │   ├── repository/
    │   │   └── PetStoreRepository.java # Couche d'accès aux données
    │   └── main/
    │       └── Main.java               # Point d'entrée
    └── resources/
        └── META-INF/
            └── persistence.xml         # Configuration JPA / Hibernate
```

---

## Modèle de données

| Table          | Description                              |
|----------------|------------------------------------------|
| `address`      | Adresses des animaleries                 |
| `petstore`     | Animaleries                              |
| `product`      | Produits vendus                          |
| `animal`       | Table mère (héritage JOINED)             |
| `fish`         | Poissons (jointure sur `animal`)         |
| `cat`          | Chats (jointure sur `animal`)            |
| `petstore_product` | Table de jointure N-N produits       |

---
