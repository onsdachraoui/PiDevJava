package entities;

public class Association {
    private int id,code_postal,telephone;
    private String nom,email,lieu,description,photo;

    public Association(){}
    public Association (int id,String nom,int telephone, String email,String lieu,int code_postal,String description,String photo ){
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.lieu = lieu;
        this.code_postal=code_postal;
        this.description = description;
        this.photo = photo;

    }
    public Association (String nom,int telephone, String email,String lieu,int code_postal,String description,String photo ){

        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.lieu = lieu;
        this.code_postal=code_postal;
        this.description = description;
        this.photo = photo;

    }

    public Association(String text, int telephone, String text1, String text2, int codePostal, String text3, int i) {
    }

    public int getCode_postal() {
        return code_postal;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Association{" +
                "id=" + id +
                ", codePostal=" + code_postal +
                ", telephone=" + telephone +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", lieu='" + lieu + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
