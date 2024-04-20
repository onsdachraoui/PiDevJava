package services;

import entities.Association;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {

    void ajouter(T t) throws SQLException;

    void modifier(T t) throws SQLException;
    void modifier(String nom,int telephone,String email,String lieu,int codePostal,String description,int id) throws SQLException;
    void supprimer(int id) throws SQLException;

    List<T> afficher() throws SQLException;
    void add(T entity) throws SQLException ;

}
