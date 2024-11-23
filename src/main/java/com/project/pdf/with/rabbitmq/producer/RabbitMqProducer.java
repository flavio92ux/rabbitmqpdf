package com.project.pdf.with.rabbitmq.producer;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.project.pdf.with.rabbitmq.dto.MergeRequest;

@Component
public class RabbitMqProducer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void sendPdfMergeRequest(String name, List<MultipartFile> files) {
    MergeRequest mergeRequest = new MergeRequest(name, files);

    this.rabbitTemplate.convertAndSend("pdf-merge-exchange", "pdf.merge", mergeRequest);
  }
}
