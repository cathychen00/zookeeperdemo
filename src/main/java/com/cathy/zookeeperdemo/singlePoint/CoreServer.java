package com.cathy.zookeeperdemo.singlePoint;

import com.cathy.zookeeperdemo.configsystem.ZkUtil;
import org.I0Itec.zkclient.ZkClient;

public class CoreServer {
    private String hostName;
    public CoreServer(String hostName){
        this.hostName=hostName;
    }

    public void start(){
        ZkClient zk=ZkUtil.getZkClient();
        if (!zk.exists(ZkUtil.CORE_SERVER_NODE_NAME)){
            zk.createPersistent(ZkUtil.CORE_SERVER_NODE_NAME);
        }
        zk.createEphemeralSequential(ZkUtil.CORE_SERVER_NODE_NAME + "/server", hostName);
        System.out.println("core server "+hostName+" start ");
    }
}
