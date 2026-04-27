package org.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;


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
        /*
        Calc c1= new Calc(6).Add(5).Mul(0).Add(5).Div(5);
        Calc c2=new Calc2(9).Toate(5);
        System.out.println(c1.x);
        System.out.println(c2.x);
        */


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
        //Am modificat prezenta dupa structura pusa pe classroom
        System.out.println("Prezenta: ");
        System.out.println("Verificam daca studentul:" + s2.nume + " este prezent...");
        if(s2.VerificarePrezenta(slista))System.out.println("Este prezent!");
        else System.out.println("Nu este prezent!");

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
        ArrayList<StudentCuNota> listaStudentiNote = new ArrayList<>();

        listaStudentiNote.add(new StudentCuNota(101, 231, "Popescu", "Andrei", true, 9));
        listaStudentiNote.add(new StudentCuNota(102, 231, "Ionescu", "Maria", true, 8));
        listaStudentiNote.add(new StudentCuNota(103, 231, "Dumitrescu", "Alexandru", false, 5));
        listaStudentiNote.add(new StudentCuNota(104, 232, "Popa", "Elena", true, 10));
        listaStudentiNote.add(new StudentCuNota(105, 232, "Stan", "Mihai", true, 7));
        listaStudentiNote.add(new StudentCuNota(106, 232, "Gheorghe", "Ana", false, 4));
        listaStudentiNote.add(new StudentCuNota(107, 233, "Constantin", "Bogdan", true, 6));
        listaStudentiNote.add(new StudentCuNota(108, 233, "Marin", "Ioana", true, 9));
        listaStudentiNote.add(new StudentCuNota(109, 233, "Vasile", "Cristian", false, 3));
        listaStudentiNote.add(new StudentCuNota(110, 234, "Dima", "Raluca", true, 10));
        //Excel Implementation
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Student Data");
        Map<String, Object[]> data=new TreeMap<String,Object[]>();
        data.put("1", new Object[] {"Nr Matricol","Formatie", "Nume", "Prenume","Prezent","Nota"});
        Integer it=2; String Afirmatie;
        for(StudentCuNota studenti:listaStudentiNote)
        {
            if(studenti.Prezent) Afirmatie="Da";
            else Afirmatie="Nu";
            data.put(it.toString(),new Object[]{studenti.nrMatricol,studenti.formatie,studenti.nume,studenti.prenume,Afirmatie,studenti.Nota});
            it++;

        }

        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {

            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

//Write the workbook in file system

        try {
            FileOutputStream out = new FileOutputStream(new File("Studenti.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Studenti.xlsx written successfully on disk.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try{
            FileInputStream filaStudenti = new FileInputStream(new File("Studenti.xlsx"));

//Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbookRead = new XSSFWorkbook(filaStudenti);

//Get first/desired sheet from the workbook
            XSSFSheet sheetRead = workbook.getSheetAt(0);

//Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + " ");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + " ");
                            break;
                    }
                }
                System.out.println("");
            }
            filaStudenti.close();

        }
        catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }

        Set<String> keyset2 = data.keySet();
        int rownum2 = 0;
        for (String key : keyset2) {
            Row row = sheet.createRow(rownum2++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }


        int lastDataRow = listaStudentiNote.size() + 1; // 11
        Row mediaRow = sheet.createRow(rownum2);
        mediaRow.createCell(0).setCellValue("Media Generala:");

        Cell mediaCell = mediaRow.createCell(5); // coloana F = Nota
        mediaCell.setCellFormula("AVERAGE(F2:F" + lastDataRow + ")"); //Seteaza formula pentru celula cu media

        CellStyle mediaStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        mediaStyle.setDataFormat(format.getFormat("0.00"));
        mediaCell.setCellStyle(mediaStyle);


        try {
            FileOutputStream out = new FileOutputStream(new File("Studenti.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Studenti.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
