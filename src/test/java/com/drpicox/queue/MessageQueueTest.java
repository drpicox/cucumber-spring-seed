package com.drpicox.queue;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class MessageQueueTest {

    private String receivedString;
    private int receiveCount;
    private String receiveOrder;

    @Test
    public void deliverMessage() {
        receivedString = null;

        MessageQueue messageQueue = new MessageQueue();
        messageQueue.send(new String("hello"));
        messageQueue.addMessageListener(String.class, s -> receivedString = s);
        messageQueue.deliverMessages();

        assertEquals("hello", receivedString);
    }

    @Test
    public void deliverTwoMessages() {
        receiveCount = 0;

        MessageQueue messageQueue = new MessageQueue();
        messageQueue.send("first message");
        messageQueue.send("second message");
        messageQueue.addMessageListener(String.class, (String s) -> receiveCount++);
        messageQueue.deliverMessages();

        assertEquals(2, receiveCount);
    }

    @Test
    public void deliverToTwoReceivers() {
        receiveCount = 0;

        MessageQueue messageQueue = new MessageQueue();
        messageQueue.send("message");
        messageQueue.addMessageListener(String.class, (String s) -> receiveCount++);
        messageQueue.addMessageListener(String.class, (String s) -> receiveCount++);
        messageQueue.deliverMessages();

        assertEquals(2, receiveCount);
    }

    @Test
    public void deliverMessagesOnce() {
        receiveCount = 0;

        MessageQueue messageQueue = new MessageQueue();
        messageQueue.send("message");
        messageQueue.addMessageListener(String.class, (String s) -> receiveCount++);
        messageQueue.deliverMessages();
        messageQueue.deliverMessages();

        assertEquals(1, receiveCount);
    }

    @Test
    public void deliverOnlyToCorrectTypeReceivers() {
        receiveCount = 0;

        MessageQueue messageQueue = new MessageQueue();
        messageQueue.send("message");
        messageQueue.addMessageListener(Long.class, (Long s) -> receiveCount++);
        messageQueue.addMessageListener(String.class, (String s) -> receiveCount++);
        messageQueue.deliverMessages();

        assertEquals(1, receiveCount);
    }

    @Test
    public void receiversCanDeliverNewMessages() {
        receiveCount = 0;

        MessageQueue messageQueue = new MessageQueue();
        messageQueue.send("message");
        messageQueue.addMessageListener(Long.class, l -> receiveCount++);
        messageQueue.addMessageListener(String.class, s -> messageQueue.send(3L));
        messageQueue.deliverMessages();

        assertEquals(1, receiveCount);
    }

    @Test
    public void doNotDeliverMessagesIfDeliverMessagesIsNotInvoked() {
        receiveCount = 0;

        MessageQueue messageQueue = new MessageQueue();
        messageQueue.send("message");
        messageQueue.addMessageListener(String.class, (String s) -> receiveCount++);

        assertEquals(0, receiveCount);
    }

    @Test(expected=NonSerializableException.class)
    public void nonSerializableMessagesFailAtSend() {
        class NonSerializable {}
        LinkedList<NonSerializable> noSerializableList = new LinkedList<>();
        noSerializableList.add(new NonSerializable());

        MessageQueue messageQueue = new MessageQueue();
        messageQueue.send(noSerializableList);
    }
}
