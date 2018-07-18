package com.cathy.zookeeperdemo.singlePoint;

import com.cathy.zookeeperdemo.configsystem.ZkUtil;
import org.I0Itec.zkclient.ZkClient;

import java.util.Arrays;
import java.util.List;

public class ClientServer {

    private String getCoreServer(){
        ZkClient zkClient=ZkUtil.getZkClient();
        List<String> servers=zkClient.getChildren(ZkUtil.CORE_SERVER_NODE_NAME);
        if(servers==null||servers.size()==0){
            return null;
        }

        System.out.println("servers:");
        for(String s:servers){
            System.out.println(s);
        }

        Object[] arr=servers.toArray();
        Arrays.sort(arr);
        String data=zkClient.readData(ZkUtil.CORE_SERVER_NODE_NAME+"/"+arr[0].toString());
        System.out.println("get coreserver node="+arr[0]+" data="+data);
        return data;
    }

    public void run(){
        System.out.println("客户端运行 运行服务"+getCoreServer());
    }
}
