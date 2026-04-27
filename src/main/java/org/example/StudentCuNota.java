package org.example;
import java.io.FileWriter;
import java.io.*;

public class StudentCuNota extends Student {
    int Nota;
    StudentCuNota(Integer nrMatricol, Integer formatie, String nume, String prenume,boolean Prezent,Integer Nota)
    {
        this.nrMatricol=nrMatricol;
        this.formatie=formatie;
        this.nume=nume;
        this.prenume=prenume;
        this.Prezent=Prezent;
        this.Nota=Nota;
    }

    void ScriereFisier()
    {
        try
        {
            FileWriter output=new FileWriter("C:\\ULBS\\Paradigme de Programare\\Proiect2\\src\\Output2");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void FunctieTelescop()
    {
        getNume();
    }


}
