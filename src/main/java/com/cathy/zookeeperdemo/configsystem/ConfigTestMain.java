package com.cathy.zookeeperdemo.configsystem;

public class ConfigTestMain {
    public static void main(String[] args) throws InterruptedException {
        ConfigManager configManager=new ConfigManager();
        ConfigApp configApp=new ConfigApp();

        configManager.loadFtpConfigFromDB();
        configManager.syncFtpConfigToZK();

        configApp.run();

        configManager.updateFtpConfigToDB(21,"localhost","admin","admin");
        configManager.syncFtpConfigToZK();

        configApp.run();
    }
}
