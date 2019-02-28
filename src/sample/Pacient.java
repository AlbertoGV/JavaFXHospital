package sample;


import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.Period;

public class Pacient {
    private Genere genere;
    private float Pes;
    private int Alçada;
    private Image image;
    private String Telefon, DNI;
    private String Nom;

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCognoms() {
        return Cognoms;
    }

    public void setCognoms(String cognoms) {
        Cognoms = cognoms;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public LocalDate getDataNaixament() {
        return DataNaixament;
    }

    public void setDataNaixament(LocalDate dataNaixament) {
        DataNaixament = dataNaixament;
    }

    private String Cognoms;
    private String Email;
    private LocalDate DataNaixament;

    public Genere getGenere() {
        return genere;
    }
    public int getEdat() {
        return Period.between(getDataNaixament(), LocalDate.now()).getYears();
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }



    public enum Genere {
        HOME,DONA
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dni) {
        DNI = dni;
    }

    public Pacient(String dni, String nom, String cognoms, LocalDate dataN, Genere g, String telf, float p, int a) {
        Nom = nom;
        Cognoms= cognoms;
        DataNaixament=dataN;
        genere=g;
        DNI = dni;
        Pes =   p;
        Alçada = a;
        Telefon = telf;

    }

    public float getPes() {
        return Pes;
    }

    public void setPes(float pes) {
        Pes = pes;
    }

    public int getAlçada() {
        return Alçada;
    }

    public void setAlçada(int alçada) {
        Alçada = alçada;
    }


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}