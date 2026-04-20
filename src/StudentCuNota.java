import java.io.FileWriter;
import java.io.*;
import java.util.ArrayList;

public final class StudentCuNota extends Student {
 final int Nota;
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

 void ModificaFormatie(Integer formatienoua)
 {
     this.formatie=formatienoua;
 }

 ArrayList<ArrayList<Student>> AranjareLista(ArrayList<Student> student)
 {
     ArrayList<Student> lista1=new ArrayList<>();
     ArrayList<Student> lista2=new ArrayList<>();
     ArrayList<Student> lista3=new ArrayList<>();
         for(Student x : student)
         {
             if(x.formatie == 911)
             {
                 lista1.add(x);
             }
             else if(x.formatie == 912)
             {
                 lista2.add(x);
             }
             else
             {
                 lista3.add(x);
             }
     }
     ArrayList<ArrayList<Student>> result = new ArrayList<>();
     result.add(lista1);
     result.add(lista2);
     result.add(lista3);
     return result;
 }

}
