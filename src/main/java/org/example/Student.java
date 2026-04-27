package org.example;

import java.util.ArrayList;

public class Student implements Comparable<Student> {
    Integer nrMatricol;
    Integer formatie;
    String nume;
    String prenume;
    boolean Prezent;

   public Student()
    {

    }

   public Student(Integer nrMatricol, Integer formatie, String nume, String prenume,boolean Prezent)
    {
        this.nrMatricol=nrMatricol;
        this.formatie=formatie;
        this.nume=nume;
        this.prenume=prenume;
        this.Prezent=Prezent;
    }
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
    String getNume()
    {
        return this.nume;
    }
    String getPrenume()
    {
        return this.prenume;
    }
    Integer getFormatie()
    {
        return this.formatie;
    }
}
