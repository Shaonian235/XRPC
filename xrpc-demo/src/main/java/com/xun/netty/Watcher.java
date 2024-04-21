package xun.netty;

import org.apache.zookeeper.WatchedEvent;

/**
 * ClassName: Watcher
 * Package: xun.netty
 * Description:
 *
 * @Author ZJX
 * @Create 2024/4/18 12:03
 * @Version 1.0
 */
public class Watcher implements org.apache.zookeeper.Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
         if (watchedEvent.getType()==Event.EventType.None){
             if (watchedEvent.getState()==Event.KeeperState.SyncConnected){
                 System.out.println("连接成功他");
             }else if (watchedEvent.getState()==Event.KeeperState.Disconnected){
                 System.out.println("断开连接");
             }else if (watchedEvent.getState()==Event.KeeperState.Expired){
                 System.out.println("会话过期");
             }else if (watchedEvent.getState()==Event.KeeperState.AuthFailed){
                 System.out.println("认证失败");
             }else if (watchedEvent.getState()==Event.KeeperState.ConnectedReadOnly){
                 System.out.println("只读连接");
             }
         }else if (watchedEvent.getType()==Event.EventType.NodeCreated){
             System.out.println("节点创建");
         }else if (watchedEvent.getType()==Event.EventType.NodeDeleted){
             System.out.println("节点删除");
         }
    }
}
