package ServerCabinetMedical.demo.MODEL;
import ServerCabinetMedical.demo.MODEL.Consultatii;

import jakarta.persistence.*;

@Entity
@Table(name = "pacienti")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "CNP")
    private Integer CNP;

    @Column(name = "Nume")
    private String Nume;

    @Column(name = "Varsta")
    private Integer Varsta;

    public Pacient(Integer id, Integer CNP, String nume, Integer varsta) {
        this.id = id;
        this.CNP = CNP;
        Nume = nume;
        Varsta = varsta;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "id=" + id +
                ", CNP=" + CNP +
                ", Nume='" + Nume + '\'' +
                ", Varsta=" + Varsta +
                '}';
    }

    public Pacient() {
    }

//    public Pacient(Integer idpacienti, Integer idmedic, Integer idconsultatie, String dataconsultatie, String simptome, String diagnostic, String tratament) {
//        super(idpacienti, idmedic, idconsultatie, dataconsultatie, simptome, diagnostic, tratament);
//    }

//    public Pacient() {
//    }

//    public Pacient(Integer id, String nume, String simptome, String diagnostic, String tratament) {
//
//        this.id = id;
//        this.Nume = nume;
//        this.setSimptome(simptome);
//        this.setTratament(diagnostic);
//        this.setDiagnostic(tratament);
//
//    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

//    public Pacient(Integer idpacienti, Integer idmedic, Integer idconsultatie, String dataconsultatie, String simptome, String diagnostic, String tratament) {
//        super(idpacienti, idmedic, idconsultatie, dataconsultatie, simptome, diagnostic, tratament);
//    }

//    public Pacient(Integer id, Integer CNP, String nume, Integer varsta) {
//        this.id = id;
//        this.CNP = CNP;
//        this.Nume = nume;
//        this.Varsta = varsta;
//    }
//    public Pacient(Integer id, String nume, Integer varsta) {
//        this.id = id;
//        this.Nume = nume;
//        this.Varsta = varsta;
//    }


    public Integer getCNP() {
        return CNP;
    }

    public void setCNP(Integer CNP) {
        this.CNP = CNP;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public Integer getVarsta() {
        return Varsta;
    }

    public void setVarsta(Integer varsta) {
        Varsta = varsta;
    }
}

