public class Main {

    public static void main(String[] args) {
        Founder founder = new Founder(new Company(10));
        founder.start();
    }
}