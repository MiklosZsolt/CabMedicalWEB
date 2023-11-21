package ServerCabinetMedical.demo.CONTROLLER;

import ServerCabinetMedical.demo.MODEL.Pacient;
import ServerCabinetMedical.demo.MODEL.Utilizator;
import ServerCabinetMedical.demo.Service.AdminService;
import ServerCabinetMedical.demo.Service.UtilizatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/getUsers")
    public ResponseEntity getUtilizatori() throws JsonProcessingException {
        List<Utilizator> utilizatori = adminService.getUtilizatori();
        return ResponseEntity.status(HttpStatus.OK).body(utilizatori);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addUtilizator(@RequestBody Utilizator utilizator) {
        Utilizator savedUtilizator = adminService.adaugaUtilizator(utilizator);
        return ResponseEntity.ok(savedUtilizator);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> stergeUtilizator(@PathVariable Integer id) {
        adminService.stergeUtilizator(id);
        return ResponseEntity.ok("Utilizatorul a fost sters cu succes.");
    }
//    @PutMapping("/utilizatori")
//    public ResponseEntity<Utilizator> updateUtilizator(@RequestBody Utilizator utilizator) {
//        Utilizator utilizatorActualizat = adminService.actualizeazaUtilizator(utilizator);
//        return ResponseEntity.ok(utilizatorActualizat);
//    }
    @PostMapping("/updatenew/{id}")
    public ResponseEntity<Utilizator> actualizeazaUtilizator(@PathVariable(value = "id") Integer id,
                                                             @RequestBody Utilizator utilizator) {
        utilizator.setId(id);
        Utilizator utilizatorActualizat = adminService.updateUtilizator(utilizator);
        if(utilizatorActualizat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utilizatorActualizat);
    }
    @PostMapping("/by-rol/{rol}")
    public ResponseEntity<List<Utilizator>> getUtilizatoriByRol(@PathVariable String rol) {
        List<Utilizator> utilizatori = adminService.getUtilizatoriByRol(rol);
        return ResponseEntity.ok(utilizatori);
    }








}
