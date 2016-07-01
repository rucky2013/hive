package com.hive;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:applicationContext.xml"});
        while (true) {
            try {
                Application.class.wait();
            }
            catch (Throwable e) {

            }
        }
	}
}
