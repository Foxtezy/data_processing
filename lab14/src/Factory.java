public class Factory {

    private final A a = new A();

    private final B b = new B();

    private final C c = new C();

    private final AB ab = new AB();

    private final Widget widget = new Widget();

    private final Thread aProducer = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            a.produce();
        }
    });

    private final Thread bProducer = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            b.produce();
        }
    });

    private final Thread cProducer = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            c.produce();
        }
    });

    private final Thread abProducer = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            ab.produce(a, b);
        }
    });

    private final Thread widgetProducer = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            widget.produce(ab, c);
        }
    });

    public void run() {
        aProducer.start();
        bProducer.start();
        cProducer.start();
        abProducer.start();
        widgetProducer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Count of widgets: " + widget.getCount())));
    }
}
