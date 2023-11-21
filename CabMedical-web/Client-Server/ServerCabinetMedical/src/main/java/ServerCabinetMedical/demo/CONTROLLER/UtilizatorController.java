package ServerCabinetMedical.demo.CONTROLLER;

import ServerCabinetMedical.demo.MODEL.Utilizator;
import ServerCabinetMedical.demo.MODEL.Pacient;

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
@RequestMapping("/utilizatori")
public class UtilizatorController {

    @Autowired
    private UtilizatorService utilizatorService;

//    @GetMapping("/")
//    @ResponseBody
//    public List<Utilizator> getUsers() {
//        return utilizatorService.getUsers();
//    }
    @GetMapping("/getRol")
    @ResponseBody
    public String getUserCredentials(@RequestParam String nume, @RequestParam String parola) throws JsonProcessingException {
        return this.utilizatorService.getRol(nume, parola);
    }
    @PostMapping("/id/{nume}/{parola}")
    public ResponseEntity<Integer> getUtilizatorIdByNumeAndParola(@PathVariable("nume") String nume, @PathVariable("parola") String parola){
        Integer utilizatorId = utilizatorService.getIdUtilizatorByNumeAndParola(nume, parola);
        return ResponseEntity.ok(utilizatorId);
    }




//    @GetMapping("/getUsers")
//    public ResponseEntity getUtilizatori() throws JsonProcessingException {
//        List<Utilizator> utilizatori = utilizatorService.getUtilizatori();
//        return ResponseEntity.status(HttpStatus.OK).body(utilizatori);
//    }

}
