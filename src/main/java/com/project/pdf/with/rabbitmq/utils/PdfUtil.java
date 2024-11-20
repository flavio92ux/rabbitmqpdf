package com.project.pdf.with.rabbitmq.utils;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.utils.PdfMerger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class PdfUtil {
    public static byte[] mergePdfs(List<byte[]> pdfFiles) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfDocument mergedPdf = new PdfDocument(new PdfWriter(outputStream));
        PdfMerger pdfMerger = new PdfMerger(mergedPdf);

        for (byte[] file : pdfFiles) {
            try (InputStream inputStream = new ByteArrayInputStream(file)) {
                PdfDocument pdf = new PdfDocument(new PdfReader(inputStream));
                pdfMerger.merge(pdf, 1, pdf.getNumberOfPages());
                pdf.close();
            }
        }

        mergedPdf.close();

        return outputStream.toByteArray();
    }
}
