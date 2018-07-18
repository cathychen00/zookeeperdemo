package com.cathy.zookeeperdemo.configsystem;

import org.I0Itec.zkclient.ZkClient;

public class ZkUtil {
    public static final String FTP_CONFIG_NODE_NAME = "/config/ftp";
    public static final String CORE_SERVER_NODE_NAME="/myserver";

    public static ZkClient getZkClient() {
        return new ZkClient("10.168.12.43:2181,10.168.12.43:2182,10.168.12.43:2183");
    }
}
