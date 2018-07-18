package com.cathy.zookeeperdemo;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 连接集群 zkClient
 */
public class ZooKeeperHello2 {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZkClient zkClient = new ZkClient("10.168.12.43:2181,10.168.12.43:2182,10.168.12.43:2183");
        String node = "/app3";
        if (!zkClient.exists(node)) {
            zkClient.createPersistent(node, "hello zk");
        }
        System.out.println(zkClient.readData(node));
    }
}
