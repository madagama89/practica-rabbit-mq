package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String QUEUE_NAME = "RequestOrders";
    private static String messageJson = "";

    public static void main(String[] argv) {

        OrdersTacos order = RequestOrder();

        try {
        Gson gson = new Gson();
            messageJson = gson.toJson(order);
        System.out.println(messageJson);
        }
        catch (JsonSyntaxException e) {
            System.out.println("Tenemos un error para convertir la orden, no quedamos sin cosinero " + e);
        }

        try {
            requestOrder();
        }
        catch (IOException | TimeoutException e) {
            System.out.println("Tenemos problemas para enviar la orden de tacos, se quemaron: " + e);
        }
    }

    static void requestOrder() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, messageJson.getBytes());
            System.out.println(" [x] Orden tacos enviada '" + messageJson + "'");
        }
    }

   static OrdersTacos RequestOrder()
    {
        OrdersTacos order = new OrdersTacos();
        ArrayList<Menu> orderList = new ArrayList<>();
        orderList.add(new Menu(1, "Tacos al pastor", "Sin picante"));
        orderList.add(new Menu(2, "Tacos de carnitas", "Re quete bien picante"));

        order.setNumberOrder(4);
        order.setNameCustomer("Cantinflas");
        order.setNumberTable(10);
        order.setOptionMenu(orderList);

       System.out.println(order.numberOrder);

        return order;
    }
}