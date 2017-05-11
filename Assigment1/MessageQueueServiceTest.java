package com.qs;

public class MessageQueueServiceTest {
	
	public static void main(String[] args) {
		MessageQueueService messageQueueService = new MessageQueueService();
		AbstractConsumer consumerA = new DefaultConsumer("Consumer A");
		AbstractConsumer consumerB = new DefaultConsumer("Consumer B");
		AbstractConsumer consumerC = new DefaultConsumer("Consumer C");
		messageQueueService.registerConsumer(consumerA);
		messageQueueService.registerConsumer(consumerB);
		messageQueueService.registerConsumer(consumerC);
		
		String jsonString1 = "1";
		String jsonString2 = "2";
		String jsonString3 = "3";
		String jsonString4 = "4";
		messageQueueService.publishMessage(jsonString1);
		messageQueueService.publishMessage(jsonString2);
		messageQueueService.publishMessage(jsonString3);
		messageQueueService.publishMessage(jsonString4);
		messageQueueService.publishMessage(jsonString1);
		
		consumerA.getExecutor().shutdown();
		consumerB.getExecutor().shutdown();
		consumerC.getExecutor().shutdown();
		
	}
}
