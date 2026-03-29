import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;

//Implementate: Lab4 Prima parte: Citim nume si dam numere asociate numelor. Citim fisier in hashmap. Lab3 Tema:sortare in functie de grupa si dupa nume!!!, HashMap, TreeMap care trebuiau, Merge ParsareDate acum, nu mai am randuri multe goale din cauza scannerului.

//Mai trebuie implementate: Generam metoda de .equals si hashcode

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

        TreeMap<String,Student> tMapLista=new TreeMap<>();

        tMapLista.put("Cel destept",s);
        tMapLista.put("Cel mai putin destept",s1);
        tMapLista.put("Cel mai destept dintre destepti",s2);

       //System.out.println(tMapLista.get("Cel mai destept dintre destepti"));

        //HashMap<Integer,String> StudentiNumere=new HashMap<>(); //Prima Parte HashMap
        HashMap<Integer, String> StudentiNote=new HashMap<>();
        HashMap<String, Integer> StudentiMatricol=new HashMap<>();

        for(Student x:slista)
        {
            System.out.println(x);
        }
        System.out.println("   !!!Sfarsit prima lista din program!!!");
      //Am scos prezenta ca e destul de annoying, cere input mereu de la tastatura.
      /*  System.out.println("Prezenta: ");
        for(Student x:slista)
        {
            String numeCautat=sc.nextLine();
            x.VerificarePrezenta(numeCautat);
        }
*/
        ArrayList<Student> sflista=new ArrayList<>();
        try {
            Scanner scf=new Scanner(new File("C:\\ULBS\\Paradigme de Programare\\Proiect2\\src\\Input"));
            while(scf.hasNextLine())
            {

                String linie = scf.nextLine();
                if (linie.isEmpty()) continue;
                if (linie.contains("mere")) System.out.println("Am gasit mere!");

                Student student = Student.ParsareDate(linie); // ParsareDate
                sflista.add(student);
                StudentiMatricol.put(student.getNume(), student.nrMatricol);
            }
            Collections.sort(sflista);
            for(Student studenti:sflista) {
                System.out.println(studenti);

            }
            System.out.println("   !!!Sfarsit a doua lista din program (Collections.sort)!!!");
            FileWriter output = new FileWriter("C:\\ULBS\\Paradigme de Programare\\Proiect2\\src\\Output");

            for (Student student : sflista) {
                output.write(student.getNume() + " " + student.getFormatie() + "\n");
            }

            output.close();
            /*          //Prima parte introducere hashmap
            Integer i=1;
            for(Student studenti:sflista)
            {
                StudentiNumere.put(i,studenti.prenume);
                i++;
            }


            System.out.println("Cauta in Hashmap dupa numar: ");
            System.out.println(StudentiNumere.get(sc.nextInt()));
            System.out.println(StudentiNumere);
*/
            Scanner scf2=new Scanner(new File("C:\\ULBS\\Paradigme de Programare\\Proiect2\\src\\Input2"));
            while(scf2.hasNextLine())
            {
                Integer nrMatricol=scf2.nextInt();
                Integer Nota= scf2.nextInt();
                StudentiNote.put(nrMatricol,Nota.toString());
            }
            System.out.println(StudentiNote); //Afisate pentru cautare usoara
            System.out.println(StudentiMatricol); //Afisate pentru cautare usoara
            String numeDeCautatNota= "";
            System.out.print("Cauta un nume ca sa aflii nota: ");
            numeDeCautatNota=sc.nextLine();
            System.out.println(StudentiNote.get(StudentiMatricol.get(numeDeCautatNota)));
            System.out.println("Date scrise in fisier cu succes!");
        } catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }
    }
}
