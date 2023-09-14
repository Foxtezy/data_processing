

public class Main {

    public static void main(String[] args) {
        CalculatorManager leibniz = new CalculatorManager(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println(4 * leibniz.calculate());
    }
}