package com.project.pdf.with.rabbitmq.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.pdf.with.rabbitmq.dto.PdfCreationDto;
import com.project.pdf.with.rabbitmq.dto.PdfDto;
import com.project.pdf.with.rabbitmq.entity.MergedPdf;
import com.project.pdf.with.rabbitmq.services.PdfStorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    return allPdfs; // Ajustar retorno para dto
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MergedPdf createPdf(@RequestBody PdfCreationDto PdfCreationDto) {
    return pdfStorageService.create(PdfCreationDto.toEntity());
  }
  

}
