package chainofresponsibility.ex1;

public class Trouble {
    private int number; // 问题编号
    public Trouble(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return "[Trouble " + number + " ]";
    }
}
