package org.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.stream.Collectors;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class Main
{

    private static final Object printLock = new Object();


    public static void Afisare(List<Student> Lista)
    {
        synchronized (printLock)
        {
            for (Student stu : Lista)
            {
                System.out.println(stu);
            }
        }
    }

    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        Student s = (Student) StudentFactory.createStudent("FARA_NOTA", 333, 231, "Popescu", "Marcel", true, null);
        Student s1 = (Student) StudentFactory.createStudent("FARA_NOTA", 393, 211, "Ioan", "Arbore", false, null);
        Student s2 = (Student) StudentFactory.createStudent("FARA_NOTA", 323, 221, "Travolta", "John", true, null);

        ArrayList<Student> slista = new ArrayList<>();
        slista.add(s);
        slista.add(s1);
        slista.add(s2);

        TreeMap<String, Student> tMapLista = new TreeMap<>();
        tMapLista.put("Cel destept", s);
        tMapLista.put("Cel mai putin destept", s1);
        tMapLista.put("Cel mai destept dintre destepti", s2);

        HashMap<Integer, String> StudentiNote = new HashMap<>();
        HashMap<String, Integer> StudentiMatricol = new HashMap<>();

        for (Student x : slista)
        {
            System.out.println(x);
        }
        System.out.println("   !!!Sfarsit prima lista din program!!!");

        System.out.println("Prezenta: ");
        System.out.println("Verificam daca studentul:" + s2.getNume() + " este prezent...");
        if (s2.VerificarePrezenta(slista)) System.out.println("Este prezent!");
        else System.out.println("Nu este prezent!");

        ArrayList<Student> sflista = new ArrayList<>();
        try {
            Scanner scf = new Scanner(new File("C:\\ULBS\\Paradigme de Programare\\Proiect2\\src\\Input"));
            while (scf.hasNextLine())
            {
                String linie = scf.nextLine();
                if (linie.isEmpty()) continue;
                if (linie.contains("mere")) System.out.println("Am gasit mere!");

                Student student = Student.ParsareDate(linie);
                sflista.add(student);
                StudentiMatricol.put(student.getNume(), student.getNrMatricol());
            }
            Collections.sort(sflista);
            for (Student studenti : sflista)
            {
                System.out.println(studenti);
            }
            System.out.println("   !!!Sfarsit a doua liste din program (Collections.sort)!!!");

            FileWriter output = new FileWriter("C:\\ULBS\\Paradigme de Programare\\Proiect2 cu Maven\\Proiect\\src\\main\\java\\org\\example\\Output");
            int i=0; //stiu ca nu ii practic :(
            for (Student student : sflista)
            {
                if(i==0)student.ScrieDecorator(output);
                output.write(student.getNume() + " " + student.getFormatie() + "\n");
                i++;
            }
            output.close();

            Scanner scf2 = new Scanner(new File("C:\\ULBS\\Paradigme de Programare\\Proiect2\\src\\Input2"));
            while (scf2.hasNextLine())
            {
                Integer nrMatricol = scf2.nextInt();
                Integer Nota = scf2.nextInt();
                StudentiNote.put(nrMatricol, Nota.toString());
            }
            System.out.println(StudentiNote);
            System.out.println(StudentiMatricol);

            String numeDeCautatNota = "";
            System.out.print("Cauta un nume ca sa aflii nota: ");
            numeDeCautatNota = sc.nextLine();
            System.out.println(StudentiNote.get(StudentiMatricol.get(numeDeCautatNota)));
            System.out.println("Date scrise in fisier cu succes!");

        } catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }

        ArrayList<StudentCuNota> listaStudentiNote = new ArrayList<>();
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 101, 231, "Popescu", "Andrei", true, 9));
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 102, 231, "Ionescu", "Maria", true, 8));
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 103, 231, "Dumitrescu", "Alexandru", false, 5));
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 104, 232, "Popa", "Elena", true, 10));
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 105, 232, "Stan", "Mihai", true, 7));
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 106, 232, "Gheorghe", "Ana", false, 4));
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 107, 233, "Constantin", "Bogdan", true, 6));
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 108, 233, "Marin", "Ioana", true, 9));
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 109, 233, "Vasile", "Cristian", false, 3));
        listaStudentiNote.add((StudentCuNota) StudentFactory.createStudent("CU_NOTA", 110, 234, "Dima", "Raluca", true, 10));

        // Excel Implementation
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Student Data");
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"Nr Matricol", "Formatie", "Nume", "Prenume", "Prezent", "Nota"});

        Integer it = 2;
        String Afirmatie;
        for (StudentCuNota studenti : listaStudentiNote)
        {
            if (studenti.isPrezent()) Afirmatie = "Da";
            else Afirmatie = "Nu";
            data.put(it.toString(), new Object[]{studenti.getNrMatricol(), studenti.getFormatie(), studenti.getNume(), studenti.getPrenume(), Afirmatie, studenti.getNota()});
            it++;
        }

        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(new File("Studenti.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Studenti.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream filaStudenti = new FileInputStream(new File("Studenti.xlsx"));
            XSSFWorkbook workbookRead = new XSSFWorkbook(filaStudenti);
            XSSFSheet sheetRead = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType())
                    {
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

        } catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }

        Set<String> keyset2 = data.keySet();
        int rownum2 = 0;
        for (String key : keyset2)
        {
            Row row = sheet.createRow(rownum2++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
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
        mediaCell.setCellFormula("AVERAGE(F2:F" + lastDataRow + ")");

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

        List<String> StudentiCu10 = listaStudentiNote.stream()
                .filter(snota -> snota.getNota() > 9)
                .map(snota -> snota.getPrenume())
                .collect(Collectors.toList());
        System.out.println("Studenti cu nota 10: " + StudentiCu10);

        List<String> StudentiCu5 = listaStudentiNote.stream()
                .filter(snota -> snota.getNota() < 5)
                .map(snota -> snota.getPrenume())
                .collect(Collectors.toList());
        System.out.println("Studenti cu note sub 5: " + StudentiCu5);

        List<StudentCuNota> listaActualizata = listaStudentiNote.stream()
                .map(snote -> {
                    if (snote.getNota() < 4) snote.setNota(4);
                    return snote;
                })
                .collect(Collectors.toList());
        System.out.println("Studentii cu 4 actualizati: " + listaActualizata);

        int sumaNote = listaStudentiNote.stream()
                .mapToInt(snota -> snota.getNota())
                .reduce(0, Integer::sum);
        System.out.println("Suma notelor: " + sumaNote);

        double mediaNote = listaStudentiNote.stream()
                .mapToInt(snota -> snota.getNota())
                .average()
                .orElse(0.0);
        System.out.println("Media notelor: " + mediaNote);

        StudentCuNota sgrade = (StudentCuNota) StudentFactory.createStudent("CU_NOTA", 555, 3, "Totu", "Cristian", true, 9);
        s.IdentificareStudent();
        sgrade.IdentificareStudent();

        ArrayList<Student> sflistaParalel = new ArrayList<>();
        sflistaParalel.add(s);
        sflistaParalel.add(s1);
        sflistaParalel.add(s2);


        Thread PrimaLista = new Thread(() ->
        {
            synchronized (printLock) {
                System.out.println("Sunt primul thread: ");
            }
            Afisare(sflistaParalel);
        });

        Thread DouaLista = new Thread(() ->
        {
            synchronized (printLock) {
                System.out.println("Sunt al doilea thread: ");
            }
            Afisare(sflista);
        });

        PrimaLista.start();
        DouaLista.start();

        try {
            PrimaLista.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            DouaLista.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String[]> datacsv = new ArrayList<String[]>();
        datacsv.add(new String[] { "101", "231", "Popescu", "Andrei", "true" });
        datacsv.add(new String[] { "101", "231", "Popescu", "Andrei", "true" });
        datacsv.add(new String[] { "101", "231", "Popescu", "Andrei", "true" });

        //CSV Implementation:
        File file = new File("C:\\ULBS\\Paradigme de Programare\\Proiect2 cu Maven\\Proiect\\src\\main\\java\\org\\example\\Output");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "Nr Matricol", "Formatie", "Nume", "Prenume", "Prezent", "Nota"};
            writer.writeNext(header);

            // add data to csv
           writer.writeAll(datacsv);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Thread CSV = new Thread(() ->
        {
            synchronized (printLock) {
                System.out.println("Sunt CSV thread: ");
            }
            try {

                // Create an object of filereader
                // class with CSV file as a parameter.
                FileReader filereader = new FileReader(file);

                // create csvReader object passing
                // file reader as a parameter
                CSVReader csvReader = new CSVReader(filereader);
                String[] nextRecord;

                // we are going to read data line by line
                while ((nextRecord = csvReader.readNext()) != null) {
                    for (String cell : nextRecord) {
                        System.out.print(cell + "\t");
                    }
                    System.out.println();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread XLSX = new Thread(() ->
        {
            synchronized (printLock) {
                System.out.println("Sunt XLSX thread: ");
            }
            try (FileInputStream fileread = new FileInputStream("Studenti.xlsx");
                 XSSFWorkbook workbookread = new XSSFWorkbook(fileread)) {

                XSSFSheet sheetread = workbookread.getSheetAt(0);

                Iterator<Row> rowIterator = sheetread.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t");
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t");

                                break;
                            default:
                                break;
                        }
                    }
                    System.out.println();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }



        });

        XLSX.start();
        CSV.start();

        try {
            XLSX.join();
            CSV.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}