package ServerCabinetMedical.demo.REPOSITORY;

import ServerCabinetMedical.demo.MODEL.Utilizator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilizatorPersistenta extends CrudRepository<Utilizator, Long> {
   Utilizator findByNumeAndParola(String nume, String parola);
   Utilizator findRolByNumeAndParola(String nume, String parola);
   List<Utilizator> findRolByRol(String rol);
   Utilizator findRolByNume(String nume);
   List<Utilizator> findAll();


   Utilizator save(Utilizator utilizator);
   Utilizator findByNume(String nume);
//   List<Pacient> findAllP();








}
