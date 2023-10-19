public class Sorter extends Thread {

    private final BlockingList<String> stringList;

    public Sorter(BlockingList<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            bubbleSort();
        }
    }

    private void bubbleSort() {
        int size = stringList.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++){
                if (stringList.get(j).compareTo(stringList.get(j+1)) > 0) {
                    String c = stringList.get(j);
                    stringList.set(j, stringList.get(j+1));
                    stringList.set(j+1, c);
                }
            }
        }
        System.out.println("sorted");
    }
}
