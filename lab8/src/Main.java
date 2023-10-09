

public class Main {

    public static void main(String[] args) {
        CalculatorManager leibniz = new CalculatorManager(Integer.parseInt(args[0]));
        leibniz.calculate();
    }
}