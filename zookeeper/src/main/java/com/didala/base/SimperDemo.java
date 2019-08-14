package com.didala.base;

import com.didala.ZookeeperClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

/**
 * @Auther: jiangwei
 * @Date: 2019-08-13 17:45
 * @Description:
 */
public class SimperDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = ZookeeperClientFactory.getCuratorFramework();
        curatorFramework.start();

        createZnode(curatorFramework);
    }


    public static void createZnode(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/eph_seq", "jiangwei".getBytes());
    }

}
