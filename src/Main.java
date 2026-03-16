import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Collections;

//Mai trebuie implementate hashmap,treemap etc.
//Lab3 Tema:sortare in functie de grupa si dupa nume (daca doi elevi au acelasi nume si aceeasi grupa sa ii sorteze dupa nume)

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        Student s=new Student(333,231,"Popescu","Marcel",true);
        Student s1=new Student(393,211,"Ioan","Arbore",false);
        Student s2=new Student(323,221,"Travolta","John",true);

        ArrayList<Student> slista=new ArrayList<>();
        slista.add(s);
        slista.add(s1);
        slista.add(s2);



        for(Student x:slista)
        {
            System.out.println(x);
        }

        System.out.println("Prezenta: ");
        for(Student x:slista)
        {
            String numeCautat=sc.nextLine();
            x.VerificarePrezenta(numeCautat);
        }

        ArrayList<Student> sflista=new ArrayList<>();
        try {
            Scanner scf=new Scanner(new File("C:\\ULBS\\Paradigme de Programare\\Proiect2\\src\\Input"));
            while(scf.hasNextLine())
            {
                Integer nrMatricol = scf.nextInt();
                Integer formatie = scf.nextInt();
                String nume= scf.next();
                String prenume= scf.next();
                boolean Prezent = scf.nextBoolean();
                if (sc.nextLine().contains("mere")) System.out.println("Am gasit mere!");
                sflista.add(new Student(nrMatricol,formatie,nume,prenume,Prezent));
            }
            Collections.sort(sflista);
            for(Student studenti:sflista)
            {
                System.out.println(studenti);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }
    }
}
