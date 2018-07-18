package com.cathy.zookeeperdemo.configsystem;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;

public class ConfigApp {
    private FtpConfig ftpConfig;

    private void getFtpConfig(){
        ZkClient zkClient=ZkUtil.getZkClient();
        ftpConfig=(FtpConfig)zkClient.readData(ZkUtil.FTP_CONFIG_NODE_NAME);
        System.out.println(ftpConfig.toString());

        zkClient.subscribeDataChanges(ZkUtil.FTP_CONFIG_NODE_NAME, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("node changed!");
                System.out.println("node="+s);
                System.out.println("o="+o.toString());

                ftpConfig=(FtpConfig)o;
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("node deleted!");
                System.out.println("node="+s);
                ftpConfig=null;
            }
        });
    }

    public void run() throws InterruptedException {
        getFtpConfig();
        upload();
        download();
    }

    private void upload() throws InterruptedException {
        System.out.println("开始上传文件");
        System.out.println("ftpconfig="+ftpConfig.toString());
        //todo 上传
        TimeUnit.SECONDS.sleep(10);
        System.out.println("上传文件完成");
    }

    private void download() throws InterruptedException {
        System.out.println("开始下载文件");
        System.out.println("ftpconfig="+ftpConfig);
        //todo 下载
        TimeUnit.SECONDS.sleep(5);
        System.out.println("下载文件完成");
    }
}
