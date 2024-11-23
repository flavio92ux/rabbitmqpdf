package com.project.pdf.with.rabbitmq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.pdf.with.rabbitmq.entity.MergedPdf;

@Repository
public interface PdfRepository extends JpaRepository<MergedPdf, Long> {

}
