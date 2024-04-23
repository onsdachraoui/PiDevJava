package org.example.entities;

public class Service {
    private int id  ;
    private String nom ;
    private String categorie ;
    private String description ;
    private String duree_service ;
    private String disponibilite_service ;

    public Service() {
    }

    public Service(int id, String nom, String categorie, String description, String duree_service, String disponibilite_service) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.duree_service = duree_service;
        this.disponibilite_service = disponibilite_service;
    }

    public Service(String nom, String categorie, String description, String duree_service, String disponibilite_service) {
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.duree_service = duree_service;
        this.disponibilite_service = disponibilite_service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuree_service() {
        return duree_service;
    }

    public void setDuree_service(String duree_service) {
        this.duree_service = duree_service;
    }

    public String getDisponibilite_service() {
        return disponibilite_service;
    }

    public void setDisponibilite_service(String disponibilite_service) {
        this.disponibilite_service = disponibilite_service;
    }
}
