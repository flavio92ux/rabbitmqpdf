package com.project.pdf.with.rabbitmq.consumer;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.pdf.with.rabbitmq.dto.MergeRequest;
import com.project.pdf.with.rabbitmq.entity.MergedPdf;
import com.project.pdf.with.rabbitmq.repository.PdfRepository;
import com.project.pdf.with.rabbitmq.utils.PdfUtil;
import com.project.pdf.with.rabbitmq.utils.StorageUtil;

@Service
public class PdfConsumer {
  @Autowired
  private PdfRepository repository;

  @RabbitListener(queues = "pdf-merge-queue")
  public void processPdfMerge(MergeRequest mergeRequest) {
    
    try {
      byte[] mergedPdf = PdfUtil.mergePdfs(mergeRequest.getFiles());

      String fileLink = StorageUtil.saveFile(mergeRequest.getName(), mergedPdf);

      MergedPdf mergedPdfRecord = new MergedPdf();
      mergedPdfRecord.setName(mergeRequest.getName());
      mergedPdfRecord.setLink(fileLink);
      mergedPdfRecord.setCreatedAt(LocalDateTime.now());

      repository.save(mergedPdfRecord);


      System.out.println("PDF mesclado e salvo com sucesso: " + fileLink);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
