# API Java Spring Boot avec MySQL

## Pré-requis

Avant de configurer et de lancer l'API, assurez-vous que les éléments suivants sont installés :
- **Java 17+** 
- **Maven**
- **MySQL** 

## Installer et lancer le projet

### 1) Cloner le dépôt
```sh
git clone https://github.com/Krisandr-a/developpez-le-back-end-en-utilisant-java-et-spring.git
```

### 2) Configurer la base de données MySQL

Assurez-vous que MySQL fonctionne et créez une base de données à utiliser :

```sql
CREATE DATABASE rentaldb;
CREATE USER 'nom_utilisateur'@'localhost' IDENTIFIED BY 'mot_de_passe';
GRANT ALL PRIVILEGES ON rentaldb.* TO 'nom_utilisateur'@'localhost';
FLUSH PRIVILEGES;
```

Modifiez le fichier `application.properties` pour qu'il corresponde au nom d'utilisateur et au mot de passe de la base de données :

#### **`src/main/resources/application.properties`**
```properties
spring.datasource.username=nom_utilisateur
spring.datasource.password=mot_de_passe
```

### 3) Builder le projet

Exécutez la commande suivante pour builder le projet :
```sh
mvn clean install
```

### 4) Lancer l'application

Exécutez la commande suivante pour lancer l'application :

```sh
mvn spring-boot:run
```

## Tester l'API

Utiliser la documentation Swagger pour tester les endpoints :
`http://localhost:3001/swagger-ui/index.html`

