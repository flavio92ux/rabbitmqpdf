package com.project.pdf.with.rabbitmq.dto;

import com.project.pdf.with.rabbitmq.entity.MergedPdf;

public record PdfCreationDto(String name, String link) {
  public MergedPdf toEntity() {
    return new MergedPdf(name, link);
  }

}
