package ServerCabinetMedical.demo.Service;

import ServerCabinetMedical.demo.MODEL.Utilizator;
import ServerCabinetMedical.demo.MODEL.Pacient;

import ServerCabinetMedical.demo.REPOSITORY.UtilizatorPersistenta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UtilizatorService {
    @Autowired
    private UtilizatorPersistenta utilizatorPersistenta;

    public String getRol(String nume, String parola) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Utilizator utilizator = this.utilizatorPersistenta.findRolByNumeAndParola(nume, parola);
        if (utilizator != null) {
            String json = objectMapper.writeValueAsString(utilizator.getRol());
            return json;
        } else {
            return "Utilizatorul nu a fost gasit";
        }


    }
    public Integer getIdUtilizatorByNumeAndParola(String nume, String parola) {
        Utilizator utilizator = utilizatorPersistenta.findByNumeAndParola(nume, parola);
            return utilizator.getId();

    }


//    public  List<Utilizator> getUtilizatori()  {
//        List<Utilizator> utilizatori = utilizatorPersistenta.findAll();
//
//        return utilizatori;
//    }


}
