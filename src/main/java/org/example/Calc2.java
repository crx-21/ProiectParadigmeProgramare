package org.example;
public class Calc2 extends Calc {
    public Calc2(int x) {
        super(x);
    }
    public Calc2 Toate(int x)
    {
        this.x = this.x + x - this.x * x / x;
        return this;
    }
}
