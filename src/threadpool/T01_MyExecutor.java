package threadpool;

import java.util.concurrent.Executor;

public class T01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
//        new Thread(command).run();
    }

    public static void main(String[] args) {
        new T01_MyExecutor().execute(()-> System.out.println("Hello executor"));
    }
}
