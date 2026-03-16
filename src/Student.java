import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student implements Comparable<Student> {
    Integer nrMatricol;
    Integer formatie;
    String nume;
    String prenume;
    boolean Prezent;

    Student(Integer nrMatricol, Integer formatie, String nume, String prenume,boolean Prezent)
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

    String ParsareDate()
    {
        return toString();
    }

    void VerificarePrezenta(String numedeCautat)
    {
       if(Prezent==true && this.nume.equalsIgnoreCase(numedeCautat)) System.out.println("Studentul cu numele: " + nume + " " + "este prezent!");
       else System.out.println("Numele nu a fost gasit sau studentul cu numele: " + " " + numedeCautat + " este absent!");

    }

    @Override
    public int compareTo(Student other) {

        if (this.formatie != other.formatie) {
            return Integer.compare(this.formatie, other.formatie);
        }

        if (!this.nume.equals(other.nume)) {
            return this.nume.compareTo(other.nume);
        }

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
