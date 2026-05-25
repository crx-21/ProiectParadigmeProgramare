package org.example;

import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Student extends StudentIdentification implements Comparable<Student>,  StudentDecorator  {
    private Integer nrMatricol;
    private Integer formatie;
    private String nume;
    private String prenume;
    private boolean prezent;

    @Override
    public void IdentificareStudent()
    {
    System.out.println("Sunt Student fara nota!");
    }

   public Student()
    {

    }

    public Student(Integer nrMatricol, Integer formatie, String nume, String prenume, boolean prezent)
    {
        this.nrMatricol=nrMatricol;
        this.formatie=formatie;
        this.nume=nume;
        this.prenume=prenume;
        this.prezent=prezent;
    }

    public Integer getNrMatricol() { return nrMatricol; }
    public void setNrMatricol(Integer nrMatricol) { this.nrMatricol = nrMatricol; }
    public Integer getFormatie() { return formatie; }
    public void setFormatie(Integer formatie) { this.formatie = formatie; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public boolean isPrezent() { return prezent; }
    public void setPrezent(boolean prezent) { this.prezent = prezent; }
    @Override
    public String toString()
    {
        return nrMatricol + "," + formatie + "," + nume + "," + prenume;
    }

    static Student ParsareDate(String linie)
    {
        String[] parts = linie.split(",");
        return new Student(
                Integer.parseInt(parts[0].trim()),  // nrMatricol
                Integer.parseInt(parts[1].trim()),  // formatie
                parts[2].trim(),                    // nume
                parts[3].trim(),                    // prenume
                Boolean.parseBoolean(parts[4].trim()) // Prezenta
        );
    }

    boolean VerificarePrezenta(ArrayList <Student> listaPrezenta)
    {
        boolean Verificare=false;
        for(Integer i=0;i<listaPrezenta.size();i++) {
            if (nume.equalsIgnoreCase(listaPrezenta.get(i).nume))
            {
                Verificare=true;
                break;
            }
            else
            {
                Verificare=false;
            }
        }
        if(Verificare==true) //Am folosit aceasta variabila pentru a da bypass compilatorului. El crede ca nu tot timpul se va intra in blockurile if,for,while.
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public int compareTo(Student other) {
        //Am implementat Integer.compare pentru ca java poate pune in cache un integer intre -127 si 127 iar noi folosim si numere precum 900
        int groupCompare = Integer.compare(this.formatie, other.formatie);
        if (groupCompare != 0) return groupCompare;

        int numeCompare = this.nume.compareTo(other.nume);
        if (numeCompare != 0) return numeCompare;

        return this.prenume.compareTo(other.prenume);


    }

    @Override
    public void ScrieDecorator(FileWriter fila) {
        try{
            String template="NUME,PRENUME,FORMATIE";
            byte[] strToBytes = template.getBytes();
            fila.write(Arrays.toString(strToBytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
