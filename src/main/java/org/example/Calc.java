package org.example;

public class Calc {
    public int x = 0;

    public Calc(int x) {
        this.x = x;
    }

    public Calc Add(int x) {
        this.x = this.x + x;  // ✅ modify this.x using the parameter
        return this;
    }

    public Calc Sub(int x) {
        this.x = this.x - x;  // ✅
        return this;
    }

    public Calc Mul(int x) {
        this.x = this.x * x;  // ✅
        return this;
    }

    public Calc Div(int x) {
        this.x = this.x / x;
        return this;
    }
}