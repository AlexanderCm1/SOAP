package pe.upeu.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upeu.soap.entity.Persona;

public interface PersonaRepository  extends JpaRepository<Persona,Integer> {

}
