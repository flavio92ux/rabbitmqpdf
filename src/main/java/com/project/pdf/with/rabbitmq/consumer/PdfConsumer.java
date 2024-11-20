package com.project.pdf.with.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.pdf.with.rabbitmq.dto.MergeRequest;
import com.project.pdf.with.rabbitmq.repository.PdfRepository;

@Component
public class PdfConsumer {
  @Autowired
  private PdfRepository repository;

  @RabbitListener(queues = "pdf-merge-queue")
  public void processPdfMerge(MergeRequest mergeRequest) {
    // byte[] mergedPdf = PdfUtil.mergePdfs(request.getFiles());
  }

}
