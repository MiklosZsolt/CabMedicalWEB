package ServerCabinetMedical.demo.CONTROLLER;

import ServerCabinetMedical.demo.Service.AsistentService;
import ServerCabinetMedical.demo.Service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/medic")
public class MedicController {
    @Autowired
    private MedicService medicService;


    @PostMapping("/diagnostic")
    public ResponseEntity<List<Object[]>> getPacientiByDiagnostic(@RequestBody Map<String, String> requestBody) {
        String diagnostic = requestBody.get("diagnostic");
        List<Object[]> pacienti = medicService.getPacientsByDiagnostic(diagnostic);
        return ResponseEntity.ok(pacienti);
    }
    @PostMapping("/tratament")
    public ResponseEntity<List<Object[]>> getPacientsByTratament(@RequestBody Map<String, String> requestBody) {
        String tratament = requestBody.get("tratament");
        List<Object[]> pacienti = medicService.getPacientsByTratament(tratament);
        return ResponseEntity.ok(pacienti);
    }
    @PostMapping("/nume")
    public ResponseEntity<List<Object[]>> getPacientibyNume(@RequestBody Map<String, String> requestBody) {
        String nume = requestBody.get("nume");
        List<Object[]> pacienti = medicService.getPacientsByNume(nume);
        return ResponseEntity.ok(pacienti);
    }
}
