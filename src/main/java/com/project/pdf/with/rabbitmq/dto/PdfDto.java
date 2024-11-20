package com.project.pdf.with.rabbitmq.dto;

public record PdfDto(Long id, String name, String link) {

  public static PdfDto fromEntity(PdfDto pdfDto) {
    return new PdfDto(pdfDto.id, pdfDto.name, pdfDto.link);
  }
}
