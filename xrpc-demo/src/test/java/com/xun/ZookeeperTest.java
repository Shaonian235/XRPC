import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * ClassName: ZookeeperTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author ZJX
 * @Create 2024/4/17 17:21
 * @Version 1.0
 */
public class ZookeeperTest {
    ZooKeeper zooKeeper;

    @Before
    public void testZK() {
        // 创建一个zk实例,定义连接参数
        String connectString = "127.0.0.1:2181";
        int sessionTimeout = 5000;
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //创建一个永久节点
    @Test
    public void testCreatePNote() {
        String path = null;
        try {
            path = zooKeeper.create("/xunclass", "hello".getBytes(), OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(path);
    }

    @Test
    public void testDeleteNote() {
        try {
            //version -1代表删除所有版本,version乐观锁
            zooKeeper.delete("/xunclass", -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        } finally {
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void testCheckVersion() {
        try {
            Stat exists = zooKeeper.exists("/xunclass", null);
            //当前节点的数据版本
            int version = exists.getVersion();
            //当前节点的ACL数据版本
            int aversion = exists.getAversion();
            // 当前子节点的版本
            int cversion = exists.getCversion();
            System.out.println("version:" + version + " aversion:" + aversion + " cversion:" + cversion);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

