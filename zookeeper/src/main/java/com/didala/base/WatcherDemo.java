package com.didala.base;

import com.didala.ZookeeperClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;


/**
 * @Auther: jiangwei
 * @Date: 2019-08-14 08:41
 * @Description:
 */
public class WatcherDemo {


    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = ZookeeperClientFactory.getCuratorFramework();
        curatorFramework.start();
        addListenerWithNode(curatorFramework);
        addListenerWithChild(curatorFramework);
        System.in.read();

    }

    /**
     *   监听 watch 节点
     * @param curatorFramework
     * @throws Exception
     */
    public static void addListenerWithNode(CuratorFramework curatorFramework) throws Exception {
        final NodeCache nodeCache = new NodeCache(curatorFramework, "/watch", false);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() {
                System.out.println("receive Node Changed");
                System.out.println(nodeCache.getCurrentData().getPath()+"---"+new String(nodeCache.getCurrentData().getData()));
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }


    public static void addListenerWithChild(CuratorFramework curatorFramework) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/watch", true);

        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println(event.getType()+"->"+new String(event.getData().getData()));
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);

    }


}
