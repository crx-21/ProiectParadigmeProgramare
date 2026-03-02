public class Student {
    Integer nrMatricol;
    Integer formatie;
    String nume;
    String prenume;

    Student(Integer nrMatricol, Integer formatie, String nume, String prenume)
    {
        this.nrMatricol=nrMatricol;
        this.formatie=formatie;
        this.nume=nume;
        this.prenume=prenume;
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
}
