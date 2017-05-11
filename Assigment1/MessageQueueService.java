package com.qs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MessageQueueService implements IMessageProcessedListner{
	
	private LinkedList<Message> jsonMessageQueue = new LinkedList<Message>();
	private List<AbstractConsumer> consumerList = new ArrayList<>();
	
	
	public void publishMessage(String message)
	{
		Message messageObject = new Message(message);
		jsonMessageQueue.push(messageObject);
		
		for (AbstractConsumer consumer : consumerList) {
			messageObject.registerConsumer(consumer);
			messageObject.registerMessageProcessedLister(this);
		}
		
		for (AbstractConsumer consumer : consumerList) {
			consumer.deliverMessage(messageObject);
		}
	}
	
	public void registerConsumer(AbstractConsumer consumer)
	{
		consumerList.add(consumer);
	}

	@Override
	public void messageProcessed(Message messageObject) {
		jsonMessageQueue.remove(messageObject);
	}
	
	
}
