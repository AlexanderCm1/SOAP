package pe.upeu.soap.endpoint;


import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import pe.upeu.soap.entity.Persona;
import pe.upeu.soap.service.PersonaService;
import pe.upeu.ws.services.persona.CreatePersonaRequest;
import pe.upeu.ws.services.persona.CreatePersonaResponse;
import pe.upeu.ws.services.persona.GetPersonaByIdRequest;
import pe.upeu.ws.services.persona.GetPersonaByIdResponse;
import pe.upeu.ws.services.persona.ObjectFactory;
import pe.upeu.ws.services.persona.PersonaType;
import pe.upeu.ws.services.persona.UpdatePersonaRequest;
import pe.upeu.ws.services.persona.UpdatePersonaResponse;


@Endpoint
public class PersonaEndpoint {
    private static final String NAMESPACE_URI = "http://ws.upeu.pe/services/persona/";
    
    
    @Autowired
    private PersonaService service;


    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaByIdRequest")
    public GetPersonaByIdResponse findByid(@RequestPayload GetPersonaByIdRequest request){
    	ObjectFactory objectFactory = new ObjectFactory();
        GetPersonaByIdResponse response = objectFactory.createGetPersonaByIdResponse();
        Optional<Persona> persona = service.findById(request.getId());
        if(persona.isEmpty())
        	return null;
        PersonaType personaType = new PersonaType();
        BeanUtils.copyProperties(persona.get(), personaType);

        response.setPersonaType(personaType);
        return response;
    }
    
    
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPersonaRequest")
    public CreatePersonaResponse add(@RequestPayload CreatePersonaRequest request) {
    	ObjectFactory objectFactory = new ObjectFactory();
    	CreatePersonaResponse response = objectFactory.createCreatePersonaResponse();
    	Persona p = new Persona();
    	p.setNombre(request.getPersonaType().getNombre());
    	p.setApellido(request.getPersonaType().getApellido());
    	p.setDni(request.getPersonaType().getDni());
    	p.setTelefono(request.getPersonaType().getTelefono());
    	p.setEmail(request.getPersonaType().getTelefono());
    	
    	Persona newperson = new Persona();
    	newperson = service.add(p);
    	
    	PersonaType personaType = new PersonaType();
    	BeanUtils.copyProperties(newperson, personaType);
    	
    	response.setPersonaType(personaType);
    	return response;
    }
    
    
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonaRequest")
    public UpdatePersonaResponse update(@RequestPayload UpdatePersonaRequest request) {
    	ObjectFactory objectFactory = new ObjectFactory();
    	UpdatePersonaResponse response = objectFactory.createUpdatePersonaResponse();
    	Optional<Persona> persona = service.findById(request.getPersonaType().getId());
    	if(persona.isEmpty())
    		return null;
    	
    	
    	persona.get().setNombre(request.getPersonaType().getNombre());
    	persona.get().setApellido(request.getPersonaType().getApellido());
    	persona.get().setDni(request.getPersonaType().getDni());
    	persona.get().setTelefono(request.getPersonaType().getTelefono());
    	persona.get().setEmail(request.getPersonaType().getEmail());
    	
    	service.update(persona.get());
    	
    	PersonaType personaType = new PersonaType();
    	BeanUtils.copyProperties(persona.get(), personaType);
    	response.setPersonaType(personaType);
    	return response;
    	
    }


}
