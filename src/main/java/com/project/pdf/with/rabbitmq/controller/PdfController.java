package com.project.pdf.with.rabbitmq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.pdf.with.rabbitmq.producer.RabbitMqProducer;

@RestController
@RequestMapping("pdf")
public class PdfController {

  @Autowired
  private RabbitMqProducer rabbitMqProducer;

  @PostMapping("/merge")
  public ResponseEntity<String> mergePdfs(@RequestParam("name") String name, @RequestParam("files") List<MultipartFile> files) {
    if (files.isEmpty()) {
      return ResponseEntity.badRequest().body("No files provided");
    }

    rabbitMqProducer.sendPdfMergeRequest(name, files);

    return ResponseEntity.accepted().body("PDFs enviados para processamento");
  }

}
