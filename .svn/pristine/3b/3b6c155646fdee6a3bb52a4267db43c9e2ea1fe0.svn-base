package com.ccx.models.message;

import com.ccx.models.Constants;
import org.comet4j.core.CometConnection;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.comet4j.core.event.ConnectEvent;
import org.comet4j.core.event.DropEvent;
import org.comet4j.core.listener.ConnectListener;
import org.comet4j.core.listener.DropListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgPushListener  extends ConnectListener implements ServletContextListener {


	@Override
	public void contextInitialized(ServletContextEvent arg0) {
			System.out.println("=========================");
			System.out.println("初始化消息机制");
			System.out.println("=========================");
		 //  CometEngine.userConnectMap=new TimerConcurrentHashMap<>(Constants.HALF_HOUR,10000);
	       CometContext cc = CometContext.getInstance();
	       cc.registChannel(Constants.PUSH_CHANNEL_MODEL);
	       cc.registChannel(Constants.PUSH_CHANNEL_VAL);
	       CometEngine engine = CometContext.getInstance().getEngine();
	       engine.addConnectListener(this);
			//添加销毁监听器
			engine.addDropListener(new DropListener() {
				@Override
				public boolean handleEvent(DropEvent arg0) {
					return true;
				}
			});
	}


	@Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
		System.out.println("断开.........");
	}

	@Override
	public boolean handleEvent(ConnectEvent connectEvent) {
		System.out.println("========建立连接=========");
		final CometConnection conn = connectEvent.getConn();
		MsgPush.saveConnection(conn);
		return true;
	}
}
