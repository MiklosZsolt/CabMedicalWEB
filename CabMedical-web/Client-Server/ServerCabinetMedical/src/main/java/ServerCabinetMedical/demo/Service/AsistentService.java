package ServerCabinetMedical.demo.Service;

import ServerCabinetMedical.demo.MODEL.Consultatii;
import ServerCabinetMedical.demo.MODEL.Pacient;
import ServerCabinetMedical.demo.MODEL.Utilizator;
import ServerCabinetMedical.demo.REPOSITORY.PacientPersistenta;
import ServerCabinetMedical.demo.REPOSITORY.UtilizatorPersistenta;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistentService {
    @Autowired
    private PacientPersistenta pacientPersistenta;
    private UtilizatorPersistenta utilizatorPersistenta;

    public List<Pacient> getPacients()  {
        List<Pacient> pacients = pacientPersistenta.findAll();

        return pacients;
    }
    public void stergePacienti(Integer id) {
        pacientPersistenta.deleteById(Long.valueOf(id));
    }
    public Pacient adaugaPacient(Pacient pacient) {
        return pacientPersistenta.save(pacient);
    }
    public Pacient updatePacient(Pacient pacient) {
        Optional<Pacient> existingPacient = pacientPersistenta.findById(Long.valueOf(pacient.getId()));

        if (existingPacient.isPresent()) {
            Pacient currentPacient = existingPacient.get();

            // Actualizarea câmpurilor cu valorile noi, dacă sunt furnizate în cerere
            if (pacient.getCNP() != null) {
                currentPacient.setCNP(pacient.getCNP());
            }

            if (pacient.getNume() != null) {
                currentPacient.setNume(pacient.getNume());
            }

            if (pacient.getVarsta() != null) {
                currentPacient.setVarsta(pacient.getVarsta());
            }

            // Salvarea pacientului actualizat
            return pacientPersistenta.save(currentPacient);
        } else {
            // Pacientul nu există în baza de date
            throw new RuntimeException("Pacientul nu a fost găsit.");
        }
    }


    public List<Pacient> cautaPacientiDupaNumeMedic(String numeMedic) {
        return pacientPersistenta.findPacientiByNumeMedic(numeMedic);
    }

//    public List<Pacient> getPacientiMedic(String numeMedic) {
//        return pacientPersistenta.findPacientiByNumeMedic2(numeMedic);
//    }
    public List<Object[]> getPacientiByMedicId(int medicId) {
    return pacientPersistenta.findPacientiByMedicId(medicId);
}
    public void updateConsultatieDetails(Integer idConsultatie, String diagnostic, String tratament, String simptome) {
        pacientPersistenta.updateConsultatieDetails(idConsultatie, diagnostic, tratament, simptome);
    }


    public List<Object[]> getPacientsByVarsta(int varsta) {
        return pacientPersistenta.findPacientsByVarsta(varsta);
    }
    public List<Object[]> findPacientsByMedic2(String Medic) {
        return pacientPersistenta.findPacientsByMedic2(Medic);
    }
    public List<Object[]> getPacientsByNume(String nume) {

        return pacientPersistenta.findPacientsByNume(nume);
    }
    public List<Integer> getAllVarstePacienti() {
        return pacientPersistenta.getAllVarstePacienti();
    }
    public List<Object[]> getPacientsByDiagnostic(String diagnostic) {
        return pacientPersistenta.findPacientsByDiagnostic(diagnostic);
    }






}
