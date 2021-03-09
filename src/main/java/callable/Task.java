package callable;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return String.valueOf(this.id);
    }

}
