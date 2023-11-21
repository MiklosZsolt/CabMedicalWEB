package ServerCabinetMedical.demo.MODEL;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "utilizatori")
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "Nume", nullable = false)
    private String nume;

    @Column(name = "Parola", nullable = false)
    private String parola;

    @Column(name = "Rol", nullable = false)
    private String rol;

    @Column(name = "specialitate")
    private String specialitate;

    @Column(name = "CNP")
    private String CNP;

    public Utilizator() {
    }

    public Utilizator(Integer id, String nume, String parola, String rol, String specialitate, String CNP) {
        this.id = id;
        this.nume = nume;
        this.parola = parola;
        this.rol = rol;
        this.specialitate = specialitate;
        this.CNP = CNP;
    }
//    public Utilizator() {}
//
//    public Utilizator(String nume, String parola, String rol) {
//        this.nume = nume;
//        this.parola = parola;
//        this.rol = rol;
//    }

//    public Utilizator(String nume, String parola, String rol, String specialitate, String CNP) {
//        this.nume = nume;
//        this.parola = parola;
//        this.rol = rol;
//        this.specialitate = specialitate;
//        this.CNP = CNP;
//    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getSpecialitate() {
        return specialitate;
    }

    public void setSpecialitate(String specialitate) {
        this.specialitate = specialitate;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}