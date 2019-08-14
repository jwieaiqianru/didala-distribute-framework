package com.didala.base;

import com.didala.ZookeeperClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @Auther: jiangwei
 * @Date: 2019-08-14 09:17
 * @Description:
 */
public class LockDemo {


    public static void main(String[] args) {
        CuratorFramework curatorFramework = ZookeeperClientFactory.getCuratorFramework();
        curatorFramework.start();

       final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");

        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "->尝试竞争锁");
                try {
                    lock.acquire();
                    System.out.println(Thread.currentThread().getName()+"->成功获得了锁");
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            },"Thread --  " + i).start();


        }

    }


}
