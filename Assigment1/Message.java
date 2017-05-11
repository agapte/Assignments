package com.qs;

import java.util.ArrayList;
import java.util.List;

public class Message {
	
	String jsonObject;
	List<AbstractConsumer> consumerList = new ArrayList<>();
	List<IMessageProcessedListner> listeners =  new ArrayList<>();
	
	public Message(String jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	public void registerConsumer(AbstractConsumer consumer){
		consumerList.add(consumer);
	}
	
	public void registerMessageProcessedLister(IMessageProcessedListner listner){
		listeners.add(listner);
	}
	
	public synchronized void deRegisterConsumer(AbstractConsumer consumer) {
		consumerList.remove(consumer);
		if ( consumerList.isEmpty())
		{
			for (IMessageProcessedListner listener : listeners) {
				listener.messageProcessed(this);
			}
		}
	}
	
	public String getJsonObject() {
		return jsonObject;
	}


}

