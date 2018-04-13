package threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());
        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        //由于产生精灵线程(守护线程、后台线程)
        System.in.read();

    }

    static class R implements Runnable{
        int time;
        R(int t){
            this.time=t;
        }
        @Override
        public void run() {

        }
    }
}
