package com.qs;

public class DefaultConsumer extends AbstractConsumer {

	public DefaultConsumer(String consumerName) {
		super(consumerName);
	}

	@Override
	public void processMessage(Message message) {
		System.out.println("Processed Message " + message.getJsonObject());
		
	}

}
