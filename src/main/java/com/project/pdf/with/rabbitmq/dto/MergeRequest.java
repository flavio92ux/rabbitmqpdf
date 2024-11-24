package com.project.pdf.with.rabbitmq.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MergeRequest implements Serializable {
  private String name;
  private List<byte[]> files;

  public MergeRequest() {
  }

  public MergeRequest(String name, List<MultipartFile> multipartFiles) {
    this.name = name;
    this.files = multipartFiles.stream()
        .map(file -> {
          try {
            return file.getBytes();
          } catch (Exception e) {
            throw new RuntimeException("Erro ao processar arquivo: " + file.getOriginalFilename(), e);
          }
        })
        .toList();
  }
}
