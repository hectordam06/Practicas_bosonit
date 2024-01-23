package org.jesuitas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReadCSV {

    public static List<Person> readCSVFile()throws InvalidLineFormatException {
        List<Person> personList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("recursos/people.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Person person = parseLine(line);
                personList.add(person);
            }




        }catch (IOException e) {
            System.err.println("Archivo inexistente");
        }

        return personList;
    }

    private static Person parseLine(String line) {
        String[] parts = line.split(":");
        try {
            if (!line.contains(":") || parts.length > 3) {
                System.err.println("Número inválido de campos: " + line);
                return null; // Devuelve un Person nulo para indicar un problema
            }

            String name = parts[0].trim();
            String town = (parts.length > 1) ? parts[1].trim() : "unknown";
            int age = (parts.length > 2) ? Integer.parseInt(parts[2].trim()) : 0;

            if (name.isEmpty()) {
                System.err.println("Se requiere nombre: " + line);
                return null; // Devuelve un Person nulo para indicar un problema
            }

            if (age == 0) {
                System.err.println("Warning: Edad es 0, edad desconocida para " + name);
            }

            return new Person(name, town, age);
        } catch (Exception e) {
            System.err.println("Error al procesar la línea: " + line);
            return null; // Devuelve un Person nulo para indicar un problema
        }
    }
    public static void main(String[] args) throws InvalidLineFormatException {
        List<Person> people = readCSVFile();

        // Filtrar y mostrar personas menores de 25 años
        System.out.println("Personas menores de 25 años:");
        people.stream()
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(person -> person.getAge() < 25)
                .forEach(System.out::println);

        // Filtrar y mostrar personas cuyo nombre no empieza con 'A'
        System.out.println("\nPersonas cuyo nombre no empieza por 'A':");
        people.stream()
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(person -> !person.getName().startsWith("A"))
                .forEach(System.out::println);

        // Obtener e imprimir el primer elemento cuya ciudad sea Madrid
        System.out.println("\nPrimera persona de  Madrid:");
        people.stream()
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(person -> person.getTown().equals("Madrid"))
                .findFirst()
                .ifPresent(System.out::println);

        // Obtener e imprimir el primer elemento cuya ciudad sea Barcelona
        System.out.println("\nPrimera persona de Barcelona:");
        people.stream()
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(person -> person.getTown().equals("Barcelona"))
                .findFirst()
                .ifPresent(System.out::println);
    }
}


