package org.example;
import com.google.gson.Gson;
import com.rabbitmq.client.*;
import com.rabbitmq.tools.json.JSONUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private final static String QUEUE_NAME = "RequestOrders";

    public static void main(String[] argv) {

        listenOrders();


    }

    static void  listenOrders()
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] A la espera de nuevas ordenes. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery)  -> {
                 String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Orden recibida '" + message + "'");
                showOrderReceived(message);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException | TimeoutException e ) {
            System.out.println("Tenemos problemas para recibir ordenes: " + e);
            throw new RuntimeException(e);
        }

    }

    static void showOrderReceived(String message)
    {
        Gson gson = new Gson();
        OrdersTacos order =  gson.fromJson(message, OrdersTacos.class);

        System.out.println("===NUEVA ORDEN===");
        System.out.print("Numero de orden de tacos -> ");
        System.out.println(order.numberOrder);
        System.out.print("Nombre del cliente -> ");
        System.out.println(order.nameCustomer);
        System.out.print("Tacos solicitados -> ");
        order.optionMenu.forEach(menu -> {
            System.out.print(menu.nameItem);
            System.out.print(" , ");
            System.out.print(menu.description);

            System.out.println(" | ");
        });
    }
}