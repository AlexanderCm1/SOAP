package pe.upeu.soap.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.upeu.soap.entity.Persona;
import pe.upeu.soap.repository.PersonaRepository;
import pe.upeu.soap.service.PersonaService;

import java.util.List;
import java.util.Optional;


@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository repo;

    @Transactional(readOnly = true)
    @Override
    public List<Persona> lista() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Persona> findById(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    @Override
    public Persona add(Persona object) {
        return repo.save(object);
    }

    @Transactional
    @Override
    public Persona update(Persona object) {
        return repo.save(object);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
