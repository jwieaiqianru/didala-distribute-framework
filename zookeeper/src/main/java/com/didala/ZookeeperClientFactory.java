package com.didala;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Auther: jiangwei
 * @Date: 2019-08-14 08:04
 * @Description:
 */
public class ZookeeperClientFactory {

    private static final String ZK_ADDRESS = "10.211.55.4:2181";


    public static CuratorFramework  getCuratorFramework() {
        return   CuratorFrameworkFactory.builder().connectString(ZK_ADDRESS).sessionTimeoutMs(30000)
                .retryPolicy(new ExponentialBackoffRetry(
3000, 3)).build();
    }

}
