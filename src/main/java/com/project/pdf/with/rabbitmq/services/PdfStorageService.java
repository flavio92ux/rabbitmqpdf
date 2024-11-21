package com.project.pdf.with.rabbitmq.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.pdf.with.rabbitmq.entity.MergedPdf;
import com.project.pdf.with.rabbitmq.repository.PdfRepository;

@Service
public class PdfStorageService {
  private final PdfRepository pdfRepository;

  public PdfStorageService(PdfRepository pdfRepository) {
    this.pdfRepository = pdfRepository;
  }

  public List<MergedPdf> findAll() {
    return pdfRepository.findAll();
  }

  public MergedPdf create(MergedPdf PdfDto) {
    return pdfRepository.save(PdfDto);
  }

}
