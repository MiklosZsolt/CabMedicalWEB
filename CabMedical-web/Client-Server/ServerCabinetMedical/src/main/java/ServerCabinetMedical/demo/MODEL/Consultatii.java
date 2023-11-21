package ServerCabinetMedical.demo.MODEL;

import jakarta.persistence.*;

@Entity
@Table(name = "consultatii")
public class Consultatii {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConsultatie", nullable = false)
    private Integer idConsultatie;

    @ManyToOne
    @JoinColumn(name = "idmedic", referencedColumnName = "id")
    private Utilizator medic;

    @ManyToOne
    @JoinColumn(name = "idpacient", referencedColumnName = "id")
    private Pacient pacient;

    @Column(name = "DataConsultatie", nullable = false)
    private String dataConsultatie;

    @Column(name = "simptome")
    private String simptome;

    @Column(name = "diagnostic")
    private String diagnostic;

    @Column(name = "tratament")
    private String tratament;

    public Consultatii() {
    }

    public Integer getIdConsultatie() {
        return idConsultatie;
    }
    public Consultatii(String simptome, String diagnostic, String tratament) {
        this.simptome = simptome;
        this.diagnostic = diagnostic;
        this.tratament = tratament;
    }
    public void setIdConsultatie(Integer idConsultatie) {
        this.idConsultatie = idConsultatie;
    }

    public Utilizator getMedic() {
        return medic;
    }

    public void setMedic(Utilizator medic) {
        this.medic = medic;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public String getDataConsultatie() {
        return dataConsultatie;
    }

    public void setDataConsultatie(String dataConsultatie) {
        this.dataConsultatie = dataConsultatie;
    }

    public Consultatii(Integer idConsultatie, Utilizator medic, Pacient pacient, String dataConsultatie, String simptome, String diagnostic, String tratament) {
        this.idConsultatie = idConsultatie;
        this.medic = medic;
        this.pacient = pacient;
        this.dataConsultatie = dataConsultatie;
        this.simptome = simptome;
        this.diagnostic = diagnostic;
        this.tratament = tratament;
    }
//    public Consultatii() {
//    }

//    public Consultatii(Integer idpacienti, Integer idmedic, Integer idconsultatie, String dataconsultatie, String simptome, String diagnostic, String tratament) {
//        this.idpacienti = idpacienti;
//        this.idmedic = idmedic;
//        this.idconsultatie = idconsultatie;
//        this.dataconsultatie = dataconsultatie;
//        this.simptome = simptome;
//        this.diagnostic = diagnostic;
//        this.tratament = tratament;
//    }
//    public Consultatii(int id, String nume, String simptome, String diagnostic, String tratament) {
//        super();
//        this.setId(id);
//        this.setNume(nume);
//        this.setSimptome(simptome);
//        this.setDiagnostic(diagnostic);
//        this.setTratament(tratament);
//    }


//    public Consultatii() {
//    }

//    public Consultatii(Integer id, String nume, String diagnostic, String tratament, String simptome) {
//    }
//
//    public Consultatii(Integer id, String diagnostic, String tratament, String simptome) {
//        this.idpacienti = id;
//        this.diagnostic = diagnostic;
//        this.tratament = tratament;
//        this.simptome =simptome;
//    }

//    public Integer getIdpacienti() {
//        return idpacienti;
//    }
//
//    public void setIdpacienti(Integer idpacienti) {
//        this.idpacienti = idpacienti;
//    }

//    public Integer getIdmedic() {
//        return idmedic;
//    }
//
//    public void setIdmedic(Integer idmedic) {
//        this.idmedic = idmedic;
//    }
//
//    public Integer getIdconsultatie() {
//        return idconsultatie;
//    }
//
//    public void setIdconsultatie(int idconsultatie) {
//        this.idconsultatie = idconsultatie;
//    }
//
//    public String getDataconsultatie() {
//        return dataconsultatie;
//    }
//
//    public void setDataconsultatie(String dataconsultatie) {
//        this.dataconsultatie = dataconsultatie;
//    }

    public String getSimptome() {
        return simptome;
    }

    public void setSimptome(String simptome) {
        this.simptome = simptome;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTratament() {
        return tratament;
    }

    public void setTratament(String tratament) {
        this.tratament = tratament;
    }
}

