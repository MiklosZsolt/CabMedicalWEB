package ServerCabinetMedical.demo.CONTROLLER;

import ServerCabinetMedical.demo.MODEL.Pacient;
import ServerCabinetMedical.demo.Service.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saveFile")
public class SaveController {
    @Autowired
    private SaveService saveService;

    @PostMapping("/patients/json")
    public void savePatientsToJson(@RequestBody List<Pacient> patients) {
        String filePath = System.getProperty("user.home") + "/Desktop/file.json";

        saveService.savePatientsToJson(patients, filePath);
    }
    @PostMapping("/patients/txt")
    public void savePatientsToTxt(@RequestBody List<Pacient> patients) {
        String filePath = System.getProperty("user.home") + "/Desktop/file.txt";

        saveService.savePatientsToText(patients, filePath);
    }

}
