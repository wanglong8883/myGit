package activemq.action;

import activemq.p2p.Consumer;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.*;

public class ConsumerA {
    public final String SELECTOR="receiver='A'";
    //1 连接工厂
    private ConnectionFactory connectionFactory;
    //2 连接对象
    private Connection connection;
    //3 Session对象
    private Session session;
    //4 生产者
    private MessageConsumer messageConsumer;
    //5 目标地址
    private Destination destination;
    public ConsumerA() {
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    "admin",
                    "admin",
                    "tcp://localhost:61616"
            );
            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            this.destination=this.session.createQueue("first");
            this.messageConsumer = this.session.createConsumer(this.destination,SELECTOR);
            System.out.println("Consumer A start...");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receiver(){
        try {
            this.messageConsumer.setMessageListener(new Listener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    class Listener implements MessageListener{
        BlockingQueue<Runnable> queue=new ArrayBlockingQueue<>(10000);
        ExecutorService executorService=new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                20,
                120L,
                TimeUnit.SECONDS,
                queue);
        @Override
        public void onMessage(Message message) {
            if(message instanceof MapMessage){
                MapMessage ret= (MapMessage) message;
                executorService.execute(new MessageTask(ret));
            }
        }
    }

    public static void main(String[] args) {
        ConsumerA c=new ConsumerA();
        c.receiver();
    }
}
