package com.project.pdf.with.rabbitmq.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.project.pdf.with.rabbitmq.entity.MergedPdf;
import com.project.pdf.with.rabbitmq.services.PdfStorageService;

public class PdfStorageControllerTest {

    @Mock
    private PdfStorageService pdfStorageService;

    @InjectMocks
    private PdfStorageController pdfStorageController;

    public PdfStorageControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPdfs_Success() {
        MergedPdf pdf1 = new MergedPdf();
        pdf1.setId(1L);
        pdf1.setName("merged1.pdf");
        pdf1.setLink("http://localhost:3001/files/merged1.pdf");

        MergedPdf pdf2 = new MergedPdf();
        pdf2.setId(2L);
        pdf2.setName("merged2.pdf");
        pdf2.setLink("http://localhost:3001/files/merged2.pdf");

        List<MergedPdf> mockPdfList = Arrays.asList(pdf1, pdf2);

        when(pdfStorageService.findAll()).thenReturn(mockPdfList);

        List<MergedPdf> result = pdfStorageController.getAllPdfs();

        assertEquals(2, result.size());
        assertEquals("merged1.pdf", result.get(0).getName());
        assertEquals("http://localhost:3001/files/merged2.pdf", result.get(1).getLink());

        verify(pdfStorageService, times(1)).findAll();
    }

    @Test
    public void testGetAllPdfs_EmptyList() {
        when(pdfStorageService.findAll()).thenReturn(List.of());

        List<MergedPdf> result = pdfStorageController.getAllPdfs();

        assertTrue(result.isEmpty());
        verify(pdfStorageService, times(1)).findAll();
    }
}

