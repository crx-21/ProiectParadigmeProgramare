package org.example;

public class StudentFactory {

    public static StudentIdentification createStudent(String type, Integer nrMatricol, Integer formatie, String nume, String prenume, boolean prezent, Integer nota) {
        if(type == null) return new Student(nrMatricol,formatie,nume,prenume,prezent);

        if (type.equalsIgnoreCase("CU_NOTA")) {
            return new StudentCuNota(nrMatricol, formatie, nume, prenume, prezent, nota);
        }
        if(type.equalsIgnoreCase("FARA_NOTA")){
            return new Student(nrMatricol,formatie,nume,prenume,prezent);
        }

        return new Student(nrMatricol, formatie, nume, prenume, prezent);
    }
}
