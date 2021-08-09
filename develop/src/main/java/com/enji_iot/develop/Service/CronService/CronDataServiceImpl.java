package com.enji_iot.develop.Service.CronService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


/**
 * 
 * 定时器服务
 *
 */
@Service("CronDataService")
public class CronDataServiceImpl {
	
	// 线程层
	@Autowired
	private TaskExecutor taskExecutor ;

	@Autowired
	private CronServiceThread cronServiceThread ;
	
	@PostConstruct
	protected void execOnceTask()  {
		// 系统启动后，执行的单次任务
		// 设置所有的网关都为离线
		taskExecutor.execute(cronServiceThread.new NodeStatusOffLine());
		
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	private void HistoryDataTable() {
		// 
		taskExecutor.execute(cronServiceThread.new HistoryTableData());
	}
	
	
	@Scheduled(cron = "*/50 * * * * ?")
	private void deviceNodeOffLine() {
		// 
//		taskExecutor.execute(cronServiceThread.new DeviceNodeOffLine());
	}
	
}
