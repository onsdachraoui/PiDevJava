package entities;

public class Don {

    private int id, association_id,telephone;
    private String nom,prenom,email,type,mode_de_paiement,categorie,etat,commentaire,label;
    private float montant;

    public Don() {

    }
    public Don(int id,String nom,String prenom,String email,int telephone,String type ,String mode_de_paiement,String categorie ,String etat,String commentaire ,String label , float montant, int association_id) {
        this.id = id;


        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.type = type;
        this.montant = montant;
        this.mode_de_paiement = mode_de_paiement;
        this.categorie = categorie;
        this.etat = etat;
        this.commentaire = commentaire;
        this.label = label;

        this.association_id = association_id;
    }
    public Don( int association_id,int telephone,String nom,String prenom, String email,String type ,String mode_de_paiement,String categorie ,String etat,String commentaire ,String label , float montant) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.type = type;
        this.montant = montant;
        this.mode_de_paiement = mode_de_paiement;
        this.categorie = categorie;
        this.etat = etat;
        this.commentaire = commentaire;
        this.label = label;

        this.association_id = association_id;

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getAssociation_id() {
        return association_id;
    }

    public void setAssociation_id(int association_id) {
        this.association_id = association_id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
   public void setEmail(String email) {
        this.email = email;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMode_de_paiement() {
        return mode_de_paiement;
    }

    public void setMode_de_paiement(String mode_de_paiement) {
        this.mode_de_paiement = mode_de_paiement;
    }

    @Override
    public String toString() {
        return "Don{" +
                "id=" + id +
                ", association_id=" + association_id +
                ", telephone=" + telephone +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", mode_de_paiement='" + mode_de_paiement + '\'' +
                ", categorie='" + categorie + '\'' +
                ", etat='" + etat + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", label='" + label + '\'' +
                ", montant=" + montant +
                '}';
    }
}

