package com.project.pdf.with.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.project.pdf.with.rabbitmq.dto.EstoqueDto;

@Component
public class EstoqueConsumer {

  @RabbitListener(queues = "teste3")
  private void consumidor(EstoqueDto estoqueDto) {
    System.out.println(estoqueDto.codigoProduto);
    System.out.println(estoqueDto.quantidade);
    System.out.println("------------------------------------");

    if (estoqueDto == null || estoqueDto.codigoProduto == null) {
      throw new IllegalArgumentException("Dados inválidos!");
    }
  }
}

