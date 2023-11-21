package ServerCabinetMedical.demo.Service;

import ServerCabinetMedical.demo.MODEL.Pacient;
import ServerCabinetMedical.demo.MODEL.Utilizator;
import ServerCabinetMedical.demo.REPOSITORY.UtilizatorPersistenta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private UtilizatorPersistenta utilizatorPersistenta;

    public  List<Utilizator> getUtilizatori()  {
        List<Utilizator> utilizatori = utilizatorPersistenta.findAll();

        return utilizatori;
    }
    public Utilizator adaugaUtilizator(Utilizator utilizator) {
        return utilizatorPersistenta.save(utilizator);
    }
    public void stergeUtilizator(Integer id) {
        utilizatorPersistenta.deleteById(Long.valueOf(id));
    }
    public Utilizator updateUtilizator(Utilizator utilizator) {
        Optional<Utilizator> existingUtilizator = utilizatorPersistenta.findById(Long.valueOf(utilizator.getId()));

        if (existingUtilizator.isPresent()) {
            Utilizator currentUtilizator = existingUtilizator.get();

            // Actualizarea câmpurilor cu valorile noi, dacă sunt furnizate în cerere
            if (utilizator.getNume() != null) {
                currentUtilizator.setNume(utilizator.getNume());
            }

            if (utilizator.getParola() != null) {
                currentUtilizator.setParola(utilizator.getParola());
            }

            if (utilizator.getRol() != null) {
                currentUtilizator.setRol(utilizator.getRol());
            }

            if (utilizator.getSpecialitate() != null) {
                currentUtilizator.setSpecialitate(utilizator.getSpecialitate());
            }
            if (utilizator.getCNP() != null) {
                currentUtilizator.setCNP(utilizator.getCNP());
            }

            // Salvarea utilizatorului actualizat
            return utilizatorPersistenta.save(currentUtilizator);
        } else {
            // Utilizatorul nu există în baza de date
            throw new RuntimeException("Utilizatorul nu a fost găsit.");
        }
    }
    public List<Utilizator> getUtilizatoriByRol(String rol) {
        return utilizatorPersistenta.findRolByRol(rol);
    }









}
