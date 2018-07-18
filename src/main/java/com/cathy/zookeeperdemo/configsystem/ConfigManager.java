package com.cathy.zookeeperdemo.configsystem;

import org.I0Itec.zkclient.ZkClient;

public class ConfigManager {
    private FtpConfig ftpConfig;

    public void loadFtpConfigFromDB(){
        //todo readdb
        ftpConfig=new FtpConfig(21, "192.168.1.1", "test", "123456");
    }

    public void updateFtpConfigToDB(int port, String host, String user, String password){
        if(ftpConfig==null){
            ftpConfig=new FtpConfig();
        }
        ftpConfig.setHost(host);
        ftpConfig.setPort(port);
        ftpConfig.setUser(user);
        ftpConfig.setPassword(password);

        //todo write db
    }

    public void syncFtpConfigToZK(){
        ZkClient zkClient=ZkUtil.getZkClient();
        if(!zkClient.exists(ZkUtil.FTP_CONFIG_NODE_NAME)){
            zkClient.createPersistent(ZkUtil.FTP_CONFIG_NODE_NAME,true);
        }
        zkClient.writeData(ZkUtil.FTP_CONFIG_NODE_NAME,ftpConfig);
        zkClient.close();
    }
}
