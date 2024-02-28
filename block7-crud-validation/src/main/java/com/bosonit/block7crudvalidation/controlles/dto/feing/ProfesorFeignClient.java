package com.bosonit.block7crudvalidation.controlles.dto.feing;

import com.bosonit.block7crudvalidation.controlles.dto.profesor.ProfesorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profesor-service", url = "http://localhost:8081")

public interface ProfesorFeignClient {

    @GetMapping("/profesores/{id}")
    ProfesorOutputDto getProfesor(@PathVariable String id);
}