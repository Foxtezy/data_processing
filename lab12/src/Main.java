
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BlockingList<String> stringList = new BlockingList<>();
        Thread sorter = new Sorter(stringList);
        sorter.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (true) {
                String inputString = reader.readLine();
                if (inputString.equals(" ")) {
                    System.out.println(stringList);
                } else if (inputString.equals(":q")){
                    break;
                } else {
                    for (int i = 0; i < inputString.length(); i += 80) {
                        int endIndex = Math.min(i + 80, inputString.length());
                        String substring = inputString.substring(i, endIndex);
                        stringList.add(substring);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sorter.interrupt();
    }
}