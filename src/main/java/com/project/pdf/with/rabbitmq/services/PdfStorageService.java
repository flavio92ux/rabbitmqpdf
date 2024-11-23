package com.project.pdf.with.rabbitmq.services;

import java.util.List;

import org.springframework.data.domain.Sort;
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
    return pdfRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
  }

  public MergedPdf create(MergedPdf PdfDto) {
    return pdfRepository.save(PdfDto);
  }

}
