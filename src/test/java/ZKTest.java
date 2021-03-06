import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ZKTest{
    private ZkClient zk;

    private String nodeName = "/myApp4";

    @Before
    public void initTest() {
        zk = new ZkClient("10.168.12.43:2181,10.168.12.43:2182,10.168.12.43:2183");
    }

    @After
    public void dispose() {
        zk.close();
        System.out.println("zkclient closed!");
    }

    @Test
    public void testListener() throws InterruptedException {
        //监听指定节点的数据变化

        zk.subscribeDataChanges(nodeName, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("node data changed!");
                System.out.println("node=>" + s);
                System.out.println("data=>" + o);
                System.out.println("--------------");
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("node data deleted!");
                System.out.println("s=>" + s);
                System.out.println("--------------");

            }
        });

        System.out.println("ready!");

        //junit测试时，防止线程退出
        while (true) {
            TimeUnit.SECONDS.sleep(5);
        }
    }


    @Test
    public void testUpdateConfig() throws InterruptedException {
        if (!zk.exists(nodeName)) {
            zk.createPersistent(nodeName);
        }
        zk.writeData(nodeName, "1");
        zk.writeData(nodeName, "2");
        zk.delete(nodeName);
        zk.delete(nodeName);//删除一个不存在的node，并不会报错
    }
}
