package pe.upeu.soap.service;

import pe.upeu.soap.entity.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    public List<Persona> lista();
    public Optional<Persona> findById(Integer id);
    public Persona add(Persona object);
    public Persona update(Persona object);
    public void delete(Integer id);

}
