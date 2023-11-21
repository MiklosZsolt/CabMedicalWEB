package ServerCabinetMedical.demo.Service;

import ServerCabinetMedical.demo.REPOSITORY.PacientPersistenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicService {
    @Autowired
    private PacientPersistenta pacientPersistenta;

    public List<Object[]> getPacientsByDiagnostic(String diagnostic) {
        return pacientPersistenta.findPacientsByDiagnostic(diagnostic);
    }
    public List<Object[]> getPacientsByTratament(String tratament) {
        return pacientPersistenta.findPacientsByTratament(tratament);
    }
    public List<Object[]> getPacientsByNume(String nume) {
        return pacientPersistenta.findPacientsByNume(nume);
    }
}
