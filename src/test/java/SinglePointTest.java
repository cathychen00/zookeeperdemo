import com.cathy.zookeeperdemo.singlePoint.ClientServer;
import com.cathy.zookeeperdemo.singlePoint.CoreServer;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SinglePointTest {
    @Test
    public void startServer1() throws InterruptedException {
        CoreServer server = new CoreServer("server1");
        server.start();

        while (true) {
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    public void startServer2() throws InterruptedException {
        CoreServer server = new CoreServer("server2");
        server.start();

        while (true) {
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    public void clientTest() throws InterruptedException {
        ClientServer clientServer = new ClientServer();
        clientServer.run();

        TimeUnit.SECONDS.sleep(5);

        clientServer.run();
    }
}
