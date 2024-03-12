package com.bosonit.block11uploaddownloadfiles.controller;

import com.bosonit.block11uploaddownloadfiles.model.Fichero;
import com.bosonit.block11uploaddownloadfiles.service.FileStorageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bosonit.block11uploaddownloadfiles.repository.FicheroRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;

@RestController
public class Filecontroller {

    // Ruta donde se almacenarán los archivos
    private String uploadPath = "";

    // Repositorio para interactuar con la base de datos de ficheros
    @Autowired
    private FicheroRepository ficheroRepository;

    // Servicio para operaciones de almacenamiento de archivos
    @Autowired
    private FileStorageService fileStorageService;

    // Endpoint para cargar un archivo
    @PostMapping("/upload")
    public Fichero storeFile(MultipartFile file, String categoria) throws IOException {
        // Obtener el nombre del archivo
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        // Verificar que la extensión del archivo coincida con la categoría especificada
        if (fileName.substring(fileName.indexOf(".") + 1).equals(categoria)) {
            // Resolver la ruta completa del archivo
            Path filePath = Paths.get(uploadPath).resolve(fileName);

            // Copiar el archivo al sistema de archivos
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Crear un objeto Fichero para almacenar en la base de datos
            Fichero fichero = new Fichero();
            fichero.setName(fileName);
            fichero.setFechaSubida(new Date());
            fichero.setCategoria(categoria);

            // Guardar el objeto Fichero en la base de datos
            return ficheroRepository.save(fichero);
        } else {
            // Lanzar una excepción si la categoría no coincide con la extensión del archivo
            throw new IOException("La categoría no coincide con la extensión del archivo");
        }
    }

    // Endpoint para establecer la ruta de carga de archivos
    @GetMapping("/setpath")
    public void setUploadPath(String newUploadPath) {
        uploadPath = newUploadPath;
    }

    // Endpoint para descargar un archivo por su ID
    @GetMapping("/downloadById")
    @Transactional(readOnly = true)
    public Resource downloadFileById(Long id) throws ChangeSetPersister.NotFoundException, IOException {
        // Buscar el fichero en la base de datos por su ID
        Fichero fichero = ficheroRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Obtener la ruta del archivo
        Path filePath = Paths.get(uploadPath).resolve(fichero.getName());

        // Leer los bytes del archivo
        byte[] fileBytes = Files.readAllBytes(filePath);

        // Crear un recurso de bytes a partir del contenido del archivo
        if (Files.exists(filePath) && Files.isReadable(filePath)) {
            return new ByteArrayResource(fileBytes);
        } else {
            // Lanzar una excepción si el archivo no existe o no se puede leer
            throw new FileNotFoundException("El archivo no existe o no se puede leer");
        }
    }

    // Endpoint para descargar un archivo por su nombre
    @GetMapping("/downloadByName")
    @Transactional(readOnly = true)
    public Resource downloadFileByName(String nombre) throws ChangeSetPersister.NotFoundException, FileNotFoundException {
        // Buscar el fichero en la base de datos por su nombre
        Fichero fichero = ficheroRepository.findByName(nombre)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Obtener la ruta del archivo
        Path filePath = Paths.get(uploadPath).resolve(fichero.getName());

        // Crear un recurso de sistema de archivos a partir del archivo
        FileSystemResource resource = new FileSystemResource(filePath.toFile());

        // Verificar si el archivo existe y es legible
        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            // Lanzar una excepción si el archivo no existe o no se puede leer
            throw new FileNotFoundException("El archivo no existe o no se puede leer");
        }
    }
}
