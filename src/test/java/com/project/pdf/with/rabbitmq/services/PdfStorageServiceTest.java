package com.project.pdf.with.rabbitmq.services;

import com.project.pdf.with.rabbitmq.entity.MergedPdf;
import com.project.pdf.with.rabbitmq.repository.PdfRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PdfStorageServiceTest {

    @Mock
    private PdfRepository pdfRepository;

    @InjectMocks
    private PdfStorageService pdfStorageService;

    @Test
    public void testFindAll() {
        MergedPdf pdf1 = new MergedPdf(1L, "Document 1", "http://localhost/document1.pdf", null);
        MergedPdf pdf2 = new MergedPdf(2L, "Document 2", "http://localhost/document2.pdf", null);
        List<MergedPdf> expectedPdfs = Arrays.asList(pdf1, pdf2);

        Mockito.when(pdfRepository.findAll(Mockito.any(Sort.class))).thenReturn(expectedPdfs);

        List<MergedPdf> result = pdfStorageService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Document 1", result.get(0).getName());
        assertEquals("Document 2", result.get(1).getName());
    }

    @Test
    public void testCreate() {
        MergedPdf pdf = new MergedPdf("Document 1", "http://localhost/document1.pdf");
        MergedPdf savedPdf = new MergedPdf(1L, "Document 1", "http://localhost/document1.pdf", null);

        Mockito.when(pdfRepository.save(Mockito.any(MergedPdf.class))).thenReturn(savedPdf);

        MergedPdf result = pdfStorageService.create(pdf);

        assertNotNull(result);
        assertEquals("Document 1", result.getName());
        assertEquals("http://localhost/document1.pdf", result.getLink());
        assertEquals(1L, result.getId());
    }
}


