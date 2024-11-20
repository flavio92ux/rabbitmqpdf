package com.project.pdf.with.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.pdf.with.rabbitmq.dto.MergeRequest;
import com.project.pdf.with.rabbitmq.repository.PdfRepository;
import com.project.pdf.with.rabbitmq.utils.PdfUtil;
import com.project.pdf.with.rabbitmq.utils.StorageUtil;

@Component
public class PdfConsumer {
  @Autowired
  private PdfRepository repository;

  @RabbitListener(queues = "pdf-merge-queue")
  public void processPdfMerge(MergeRequest mergeRequest) {
    
    try {
      byte[] mergedPdf = PdfUtil.mergePdfs(mergeRequest.getFiles());

      String fileLink = StorageUtil.saveFile(mergeRequest.getName(), mergedPdf);

      System.out.println(fileLink);
      System.out.println("_______________");


    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
