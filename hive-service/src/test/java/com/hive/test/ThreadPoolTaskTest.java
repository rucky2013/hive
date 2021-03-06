package com.hive.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
/**
 * 多线程
 * @author Damon
 *
 */
public class ThreadPoolTaskTest extends BaseTest{
//	@Autowired
//	private ThreadPoolTaskExecutor pool;
	@Test
	public void getAcitiveNum() {
		ThreadPoolExecutor pool=new ThreadPoolExecutor(5, 1000,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(24));
		ExecutorService executor=Executors.newFixedThreadPool(2);
		executor.execute(new OrderThread(""));
		
		for(int i=0;i<1026;i++){
/*			FutureTask<Integer> futureTask=new FutureTask<>(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(10000);
					return 1;
				}
			}) ;*/
			Future<Integer> future=pool.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(1000);
					return 1;
				}
			});
			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("ActiveCount:"+pool.getActiveCount());
		System.out.println("PoolSize:"+pool.getPoolSize());
	}
	class OrderThread extends Thread{
		private String orderId;
		public OrderThread(String orderId) {
			this.orderId=orderId;
		}
		@Override
		public void run() {
			System.out.println(orderId);
			//业务处理
			super.run();
		}
	}
}
