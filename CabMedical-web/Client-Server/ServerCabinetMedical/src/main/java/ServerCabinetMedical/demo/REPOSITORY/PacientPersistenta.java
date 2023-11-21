package ServerCabinetMedical.demo.REPOSITORY;

import ServerCabinetMedical.demo.MODEL.Pacient;
import ServerCabinetMedical.demo.MODEL.Utilizator;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacientPersistenta extends CrudRepository<Pacient, Long> {

    List<Pacient> findAll();




    //List<Pacient> findByMedic(Utilizator utilizator);
    @Query("SELECT DISTINCT p FROM Consultatii c JOIN c.medic m JOIN c.pacient p WHERE m.rol = 'medic' AND m.nume = :numeMedic")
    List<Pacient> findPacientiByNumeMedic(@Param("numeMedic") String numeMedic);

//    @Query("SELECT p FROM Utilizator u JOIN Consultatii c ON u.id = c.idmedic JOIN Pacienti p ON c.idpacient = p.id WHERE u.nume = :numeMedic AND u.rol = 'medic'")
//    List<Pacient> findPacientiByNumeMedic2(@Param("numeMedic") String numeMedic);
    @Query("SELECT p.id, p.Nume, c.diagnostic, c.tratament, c.simptome FROM Consultatii c JOIN c.medic m JOIN c.pacient p WHERE m.rol = 'medic' AND m.id = :medicId")
    List<Object[]> findPacientiByMedicId(@Param("medicId") int medicId);

    @Modifying
    @Query("UPDATE Consultatii  SET diagnostic = :diagnostic, tratament = :tratament, simptome = :simptome WHERE idConsultatie = :idConsultatie")
    void updateConsultatieDetails(@Param("idConsultatie") Integer idConsultatie, @Param("diagnostic") String diagnostic, @Param("tratament") String tratament, @Param("simptome") String simptome);

    @Query("SELECT p.id, p.Nume, c.diagnostic, c.tratament, c.simptome FROM Pacient p JOIN Consultatii c ON p.id = c.pacient.id WHERE c.diagnostic = :diagnostic")
    List<Object[]> findPacientsByDiagnostic(@Param("diagnostic") String diagnostic);
    @Query("SELECT p.id, p.Nume, c.diagnostic, c.tratament, c.simptome FROM Pacient p JOIN Consultatii c ON p.id = c.pacient.id WHERE c.tratament = :tratament")
    List<Object[]> findPacientsByTratament(@Param("tratament") String tratament);
    @Query("SELECT p.id, p.Nume, c.diagnostic, c.tratament, c.simptome FROM Pacient p JOIN Consultatii c ON p.id = c.pacient.id WHERE p.Varsta = :varsta")
    List<Object[]> findPacientsByVarsta(@Param("varsta") int varsta);

    @Query("SELECT p.id, p.Nume, c.diagnostic, c.tratament, c.simptome " +
            "FROM Pacient p " +
            "JOIN Consultatii c ON p.id = c.pacient.id " +
            "JOIN Utilizator u ON u.id = c.medic " +
            "WHERE u.rol = 'medic' AND u.nume = :Medic")
    List<Object[]> findPacientsByMedic2(@Param("Medic") String Medic);

    @Query("SELECT p.id, p.Nume, c.diagnostic, c.tratament, c.simptome FROM Pacient p JOIN Consultatii c ON p.id = c.pacient.id WHERE p.Nume = :nume")
    List<Object[]> findPacientsByNume(@Param("nume") String nume);

    @Query("SELECT p.Varsta FROM Pacient p")
    List<Integer> getAllVarstePacienti();













}
