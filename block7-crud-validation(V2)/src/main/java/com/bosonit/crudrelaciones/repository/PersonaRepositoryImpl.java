package com.bosonit.crudrelaciones.repository;

import com.bosonit.crudrelaciones.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class PersonaRepositoryImpl implements PersonaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Persona> searchWithPagination
            (String user,
             String name,
             String surname,
             Date created_date,
             Date termination_date,
             String orderBy,
             int page,
             int size)
    {

        //crear objeto de tipo persona
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);
        //Lista de predicados
        List<Predicate> predicates = new ArrayList<>();
        //como si fueran los where
        if (user != null) {
            predicates.add(cb.equal(root.get("usuario"), user));
        }
        if (name != null) {
            predicates.add(cb.equal(root.get("name"), name));
        }
        if (surname != null) {
            predicates.add(cb.equal(root.get("surname"), surname));
        }
        if (created_date != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("created_date"), created_date));
        }
        if (termination_date != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("created_date"), termination_date));
        }
        //agergar los predicados
        query.where(predicates.toArray(new Predicate[0]));

        if (orderBy != null) {
            if (orderBy.equals("user")) {
                query.orderBy(cb.asc(root.get("usuario")));
            } else if (orderBy.equals("name")) {
                query.orderBy(cb.asc(root.get("name")));
            }
        }
        //Crear consulta TypedQuery
        TypedQuery<Persona> typedQuery = entityManager.createQuery(query);

        // Paginación
        typedQuery.setFirstResult(page * size);
        typedQuery.setMaxResults(size);
        // Obtener los resultados de la consulta
        List<Persona> resultList = typedQuery.getResultList();
        long totalCount = getCountWithFilters(user, name, surname, created_date, termination_date);
        // Crear y devolver una instancia de Page con los resultados y la información de paginación
        return new PageImpl<>(resultList, PageRequest.of(page, size), totalCount);
    }
    // Método  para obtener el total de resultados con los filtros aplicados
    private Long getCountWithFilters(String user, String name, String surname, Date startDate, Date endDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Persona> root = query.from(Persona.class);

        query.select(cb.count(root));

        List<Predicate> predicates = new ArrayList<>();

        if (user != null) {
            predicates.add(cb.equal(root.get("usuario"), user));
        }
        if (name != null) {
            predicates.add(cb.equal(root.get("name"), name));
        }
        if (surname != null) {
            predicates.add(cb.equal(root.get("surname"), surname));
        }
        if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("created_date"), startDate));
        }
        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("created_date"), endDate));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getSingleResult();
    }
}
