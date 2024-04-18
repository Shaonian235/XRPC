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

             }
         }
    }
}
