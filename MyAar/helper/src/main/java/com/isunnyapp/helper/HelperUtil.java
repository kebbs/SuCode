package com.isunnyapp.helper;

import android.content.Context;
import android.os.Environment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.mindpipe.android.logging.log4j.LogConfigurator;

public class HelperUtil {
    private static Logger log = Logger.getLogger("APP");
    public static String debugFile = Environment.getExternalStorageDirectory().getPath() + "/qiji/mopedDebug.txt";
    public static Context applicationContext;

    public static void initialize(Context context) {
        initialize(context, true);
    }

    public static void initialize(Context context, boolean crashOpen) {
        //异常监控
        new CrashHandler().setOnCrash(context, new CrashHandler.OnCrash() {
            @Override
            public void crash(String messag) {
                log.error(messag);
            }
        });
        applicationContext = context;
        initLog4j();
    }

    public static Context getApplicationContext() {
        if (applicationContext == null) {

        } else {

        }
        return applicationContext;
    }

    /**
     * 日志配置
     */
    private static void initLog4j() {
        try {
            final LogConfigurator logConfigurator = new LogConfigurator();
            logConfigurator.setFileName(debugFile);
            logConfigurator.setRootLevel(Level.INFO);
            logConfigurator.setLevel("org.apache", Level.INFO);
            logConfigurator.setFilePattern("%d{HH:mm:ss} %-5p[%c{2}] %m%n");
            logConfigurator.setMaxFileSize(1024 * 1024 * 1); //1M的记录空间
            logConfigurator.setImmediateFlush(true);
            logConfigurator.setUseFileAppender(true);
            logConfigurator.setMaxBackupSize(1);
            logConfigurator.configure();
        } catch (Exception e) {

        }
    }

}
