package com.sdj.dragphotoview;

/*
 *                   _ooOoo_
 *                  o8888888o
 *                  88" . "88
 *                  (| -_- |)
 *                  O\  =  /O
 *               ____/`---'\____
 *             .'  \\|     |//  `.
 *            /  \\|||  :  |||//  \
 *           /  _||||| -:- |||||-  \
 *           |   | \\\  -  /// |   |
 *           | \_|  ''\---/''  |   |
 *           \  .-\__  `-`  ___/-. /
 *         ___`. .'  /--.--\  `. . __
 *      ."" '<  `.___\_<|>_/___.'  >'"".
 *     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *     \  \ `-.   \_ __\ /__ _/   .-` /  /
 *======`-.____`-.___\_____/___.-`____.-'======
 *                   `=---='
 *^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *         佛祖保佑       永无BUG
 */

import android.text.TextUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CommonExecutor {
    private final static ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
    public final static Executor execSingle = Executors.newSingleThreadExecutor();

    public final static Executor getSingleExecutor() {
        return getSingleExecutor(5);
    }

    public final static Executor getSingleExecutor(
            int queueSize) {
        return getExecutor(1, 1, queueSize);
    }

    public final static Executor getExecutor(int corePoolSize,
                                             int maximumPoolSize,
                                             int queueSize) {
        return getExecutor(corePoolSize, maximumPoolSize,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public final static Executor getExecutor(int corePoolSize,
                                             int maximumPoolSize,
                                             BlockingQueue<Runnable> workQueue) {
        return getExecutor(corePoolSize, maximumPoolSize,
                30, TimeUnit.SECONDS,
                workQueue);
    }

    public final static Executor getExecutor(int corePoolSize,
                                             int maximumPoolSize,
                                             long keepAliveTime,
                                             TimeUnit unit,
                                             BlockingQueue<Runnable> workQueue) {
        return getExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, unit,
                workQueue, discardOldestPolicy);
    }

    public final static Executor getExecutor(int corePoolSize,
                                             int maximumPoolSize,
                                             long keepAliveTime,
                                             TimeUnit unit,
                                             BlockingQueue<Runnable> workQueue,
                                             RejectedExecutionHandler handler) {
        MyThreadPoolExecutor threadPoolExecutor = new MyThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, unit,
                workQueue, handler);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        Thread thread = Thread.currentThread();
        StackTraceElement[] stackTraceElements = thread.getStackTrace();
        if (stackTraceElements != null) {
            String thisClassName = CommonExecutor.class.getName();
            for (int i = 2; i < stackTraceElements.length; i++) {
                StackTraceElement element = stackTraceElements[i];
                String className = element.getClassName();
                if (!TextUtils.equals(className, thisClassName)) {
                    threadPoolExecutor.setThreadName(className + "_" + element.getLineNumber());
                    break;
                }
            }
        }
        return threadPoolExecutor;
    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {
        private String threadName;

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        public void setThreadName(String name) {
            threadName = name;
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            if (!TextUtils.isEmpty(threadName)) {
                t.setName(threadName);
            }
            super.beforeExecute(t, r);
        }
    }
}
