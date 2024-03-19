package com.bosonit.block13mongodb.service;

import com.bosonit.block13mongodb.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Persona agregarPersona(Persona persona) {
        return mongoTemplate.save(persona);
    }

    public List<Persona> obtenerPersonas(int pagina, int tamaño) {
        Pageable pageable = PageRequest.of(pagina, tamaño, Sort.by("nombre"));
        Query query = new Query().with(pageable);
        return mongoTemplate.find(query, Persona.class);
    }

    public Persona obtenerPersonaPorId(String id) {
        return mongoTemplate.findById(id, Persona.class);
    }

    public Persona actualizarPersona(String id, Persona persona) {
        persona.setId(id);
        return mongoTemplate.save(persona);
    }

    public void eliminarPersona(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Persona.class);
    }
}
