package com.qs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractConsumer {
	
	private String name = null;
	private ExecutorService executor= null ;
	private static final int DEFAULT_NUM_THREADS = 1;
	
	public AbstractConsumer(String consumerName) {
		name = consumerName;
		//TODO Have a common executor depending on number of Consumers
		executor = Executors.newFixedThreadPool(DEFAULT_NUM_THREADS, Executors.defaultThreadFactory());
	}

	public void deliverMessage(final Message message)
	{
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				processMessage(message);
				deRegisterConsumer(message);
			}
		});
	}
	
	
	public abstract void processMessage(Message message);
	
	private void deRegisterConsumer(Message message)
	{
		// After processing is complete
		message.deRegisterConsumer(this);
	}
	
	public String getName() {
		return name;
	}
	
	public ExecutorService getExecutor() {
		return executor;
	}
}
