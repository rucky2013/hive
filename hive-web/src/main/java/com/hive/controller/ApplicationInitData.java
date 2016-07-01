package com.hive.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
@Component
public class ApplicationInitData implements ApplicationListener<ContextRefreshedEvent>{
	private Logger logger=LoggerFactory.getLogger(ApplicationInitData.class);
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initRedisData();
	}
	/**
	 * 初始化redis数据
	 */
	private void initRedisData() {
		if(logger.isDebugEnabled()){
			logger.debug("create initRedisDate...........");
		}
	}

}
