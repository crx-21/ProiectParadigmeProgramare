public class Calc {
    int x=0;

    Calc(int x)
    {
        this.x=x;
    }
    Calc Add(int x)
    {
        x = x+5;
        return this;
    }
    Calc Sub(int x)
    {
        x= x-5;
        return this;
    }
    Calc Mul(int x)
    {
        x = x*5 ;
        return this;
    }
    Calc Div(int x)
    {
        x=x/5;
        return this;
    }

}
