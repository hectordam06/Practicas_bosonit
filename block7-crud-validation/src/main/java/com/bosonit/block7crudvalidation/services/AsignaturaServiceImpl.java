package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.asignatura.AsignaturaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.asignatura.AsignaturaOutputDto;
import com.bosonit.block7crudvalidation.models.Asignatura;
import com.bosonit.block7crudvalidation.repositories.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {
    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public  AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaDTO) {

        return asignaturaRepository.save(new Asignatura(asignaturaDTO)).asignaturatoasignaturaOutputDto();
    }

    @Override
    public AsignaturaOutputDto getAsignatura(String id) {
        return asignaturaRepository.findById(id).get().asignaturatoasignaturaOutputDto();
    }

    @Override
    public List<AsignaturaOutputDto> getAllAsignaturas() {
        return asignaturaRepository.findAll().stream().map(Asignatura::asignaturatoasignaturaOutputDto).toList();
    }

    @Override
    public AsignaturaOutputDto updateAsignatura(String id, AsignaturaInputDto asignaturaInputDto) {
        Asignatura asignatura = asignaturaRepository.findById(id).orElse(null);
        if (asignatura != null) {
            asignatura.setAsignatura(asignaturaInputDto.getAsignatura());
            asignatura.setComents(asignaturaInputDto.getComents());
            asignatura.setInitialDate(asignaturaInputDto.getInitialDate());
            asignatura.setFinishDate(asignaturaInputDto.getFinishDate());
            asignatura.setEstudiantes(asignaturaInputDto.getEstudiantes());

            return asignaturaRepository.save(asignatura).asignaturatoasignaturaOutputDto();
        } else {

            // Manejar las excepciones

            return null;
        }
    }

    @Override
    public void deleteAsignatura(String id) {

        asignaturaRepository.deleteById(id);
    }

    @Override
    public List<AsignaturaOutputDto> getByIdEstudiante(String idEstudiante) {
        List<Asignatura> asignaturas = asignaturaRepository.findByEstudiantesId(idEstudiante);
        return asignaturas.stream()
                .map(Asignatura::asignaturatoasignaturaOutputDto)
                .toList();
    }
}
