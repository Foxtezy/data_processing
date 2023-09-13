

public class Main {

    public static void main(String[] args) {
        Leibniz leibniz = new Leibniz();
        System.out.println(4 * leibniz.calculate(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }
}