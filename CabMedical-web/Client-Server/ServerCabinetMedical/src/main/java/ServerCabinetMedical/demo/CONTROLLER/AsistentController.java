package ServerCabinetMedical.demo.CONTROLLER;

import ServerCabinetMedical.demo.MODEL.Consultatii;
import ServerCabinetMedical.demo.MODEL.Pacient;
import ServerCabinetMedical.demo.Service.AsistentService;
import ServerCabinetMedical.demo.Service.MedicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/asistent")
public class AsistentController {
    @Autowired
    private AsistentService asistentService;
    @GetMapping("/getPatients")
    public ResponseEntity getPacients() throws JsonProcessingException {
        List<Pacient> pacients = asistentService.getPacients();
        return ResponseEntity.status(HttpStatus.OK).body(pacients);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> stergePacienti(@PathVariable Integer id) {
        asistentService.stergePacienti(id);
        return ResponseEntity.ok("Utilizatorul a fost sters cu succes.");
    }
    @PostMapping("/addPatient")
    public ResponseEntity<?> addPacienti(@RequestBody Pacient pacient) {
        Pacient savedPacient = asistentService.adaugaPacient(pacient);
        return ResponseEntity.ok(savedPacient);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Pacient> actualizeazaUtilizator(@PathVariable(value = "id") Integer id,
                                                             @RequestBody Pacient pacient) {
        pacient.setId(id);
        Pacient acualizeazaPacient = asistentService.updatePacient(pacient);
        if(acualizeazaPacient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(acualizeazaPacient);
    }


    @PostMapping("/Medic/{numeMedic}")
    public ResponseEntity<List<Pacient>> cautaPacientiDupaNumeMedic(@PathVariable String numeMedic) {
        List<Pacient> pacienti = asistentService.cautaPacientiDupaNumeMedic(numeMedic);
        return ResponseEntity.ok(pacienti);
    }
//    @PostMapping("/PacientiiMedicului")
//    public ResponseEntity<List<Pacient>> getPacientiMedic(@RequestParam("numeMedic") String numeMedic) {
//        List<Pacient> pacienti = asistentService.getPacientiMedic(numeMedic);
//        return ResponseEntity.ok(pacienti);
//    }
@PostMapping("/medicid/{medicId}")
public ResponseEntity<List<Object[]>> getPacientiByMedicId(@PathVariable int medicId) {
    List<Object[]> pacienti = asistentService.getPacientiByMedicId(medicId);
    return ResponseEntity.ok(pacienti);
}
    @PostMapping("/consultatieupdate")
    @Transactional
    public ResponseEntity<Void> updateConsultatieDetails(@RequestParam("idConsultatie") Integer idConsultatie, @RequestParam("diagnostic") String diagnostic, @RequestParam("tratament") String tratament, @RequestParam("simptome") String simptome) {
        asistentService.updateConsultatieDetails(idConsultatie, diagnostic, tratament, simptome);
        return ResponseEntity.ok().build();
    }



    @PostMapping("/varsta")
    public ResponseEntity<List<Object[]>> getPacientsByVarsta(@RequestBody Map<String, Integer> requestBody) {
        Integer varsta = requestBody.get("varsta");
        List<Object[]> pacienti = asistentService.getPacientsByVarsta(varsta);
        return ResponseEntity.ok(pacienti);
    }
    @PostMapping("/Medic")
    public List<Object[]> getPacientsByMedic(@RequestBody Map<String, String> requestBody) {
        String Medic = requestBody.get("Medic");
        return asistentService.findPacientsByMedic2(Medic);
    }
    @PostMapping("/nume")
    public ResponseEntity<List<Object[]>> getPacientibyNume(@RequestBody Map<String, String> requestBody) {
        String nume = requestBody.get("nume");
        List<Object[]> pacienti = asistentService.getPacientsByNume(nume);
        return ResponseEntity.ok(pacienti);
    }
    @GetMapping("/varste")
    public ResponseEntity<List<Integer>> getAllVarstePacienti() {
        List<Integer> varste = asistentService.getAllVarstePacienti();
        return ResponseEntity.ok(varste);
    }

    @PostMapping("/diagnostic")
    public ResponseEntity<List<Object[]>> getPacientiByDiagnostic(@RequestBody Map<String, String> requestBody) {
        String diagnostic = requestBody.get("diagnostic");
        List<Object[]> pacienti = asistentService.getPacientsByDiagnostic(diagnostic);
        return ResponseEntity.ok(pacienti);
    }







}
