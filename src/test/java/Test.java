import org.junit.jupiter.api.Test;
import org.example.Calc;
import org.example.Calc2;
import org.example.Student;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

class CalcTest {
    Calc calcs=new Calc(10);
    Calc2 calcs2=new Calc2(20);
    ArrayList<Student> studentTest=new ArrayList<>();
    studentTest.add(new Student())
    @Test
    public void TestMinus() {
        calcs.Sub(4);
        Assertions.assertEquals(6, calcs.x); //
    }

    @Test
    public void TestCalc2() {
        calcs2.Toate(30);
        Assertions.assertEquals(30, calcs2.x); //
    }

    @Test
    public void TestStudent()
    {

    }

}
