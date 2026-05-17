package org.example;
import java.io.FileWriter;
import java.io.*;
import java.util.ArrayList;

public class StudentCuNota extends Student {
    private int nota;

    public StudentCuNota(Integer nrMatricol, Integer formatie, String nume, String prenume, boolean prezent, Integer nota) {
        super(nrMatricol, formatie, nume, prenume, prezent);
        this.nota = nota;
    }

    @Override
    public void IdentificareStudent() {
        System.out.println("Sunt student cu nota!");
    }

    void ScriereFisier() {
        try {
            FileWriter output = new FileWriter("C:\\ULBS\\Paradigme de Programare\\Proiect2\\src\\Output2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void FunctieTelescop() {
        getNume();
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
