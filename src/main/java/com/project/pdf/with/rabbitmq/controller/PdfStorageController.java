package com.project.pdf.with.rabbitmq.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.pdf.with.rabbitmq.entity.MergedPdf;
import com.project.pdf.with.rabbitmq.services.PdfStorageService;


@RestController
@RequestMapping(value = "pdfStorage")
public class PdfStorageController {
  private final PdfStorageService pdfStorageService;

  public PdfStorageController(PdfStorageService pdfStorageService) {
    this.pdfStorageService = pdfStorageService;
  }

  @GetMapping
  public List<MergedPdf> getAllPdfs() {
    List<MergedPdf> allPdfs = pdfStorageService.findAll();

    return allPdfs;
  }
}
