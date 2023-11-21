package ServerCabinetMedical.demo.Service;
import ServerCabinetMedical.demo.MODEL.Pacient;
import ServerCabinetMedical.demo.REPOSITORY.PacientPersistenta;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveService {
    @Autowired
    private PacientPersistenta pacientPersistenta;

    public void savePatientsToJson(List<Pacient> patients, String filePath) {
        // Crearea unui obiect ObjectMapper pentru serializarea/deserializarea JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Serializarea listei de pacienți în format JSON cu newline după fiecare pacient
            String json = objectMapper.writeValueAsString(patients);

            // Adăugarea newline după fiecare pacient
            json = json.replace("},", "},\n");

            // Salvarea conținutului JSON în fișierul specificat
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(json);
            fileWriter.close();

            System.out.println("Lista de pacienți a fost salvată în fișierul JSON.");
        } catch (IOException e) {
            System.err.println("A apărut o eroare la salvarea listei de pacienți în fișierul JSON: " + e.getMessage());
        }
    }



    public void savePatientsToText(List<Pacient> patients, String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Pacient patient : patients) {
                String line = "id: " + patient.getId() +  "  Nume: " + patient.getNume() + ", Varsta: " + patient.getVarsta() + ", Cnp: " + patient.getCNP();
                writer.println(line);
            }
            System.out.println("Lista de pacienți a fost salvată în fișierul text.");
        } catch (IOException e) {
            System.err.println("A apărut o eroare la salvarea listei de pacienți în fișierul text: " + e.getMessage());
        }
    }

}

