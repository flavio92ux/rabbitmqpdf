package com.project.pdf.with.rabbitmq.connections;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.stereotype.Component;

import com.project.pdf.with.rabbitmq.constants.RabbitmqConstants;

import jakarta.annotation.PostConstruct;

@Component
public class RabbitmqConnection {
    private static final String EXCHANCE_NAME = "anq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitmqConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANCE_NAME);
    }

    private Binding biding(Queue queue, DirectExchange directExchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add() {
        Queue filaEstoque = this.queue("teste3");

        DirectExchange troca = this.directExchange();

        Binding ligacao = this.biding(filaEstoque, troca);

        this.amqpAdmin.declareQueue(filaEstoque);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacao);
    }
}
