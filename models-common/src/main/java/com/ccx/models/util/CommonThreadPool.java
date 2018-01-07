package com.ccx.models.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:线程池
 * @author:lilong
 * @Date: 2017/12/20
 */
public enum  CommonThreadPool {
    INSTANCE;
    private ExecutorService instance;
    CommonThreadPool(){
        instance=new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1000));
    }

    public  ExecutorService getInstanse(){
        return instance;
    }
}
