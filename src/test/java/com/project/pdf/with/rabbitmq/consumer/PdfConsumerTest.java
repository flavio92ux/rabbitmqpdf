package com.project.pdf.with.rabbitmq.consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.project.pdf.with.rabbitmq.dto.MergeRequest;
import com.project.pdf.with.rabbitmq.entity.MergedPdf;
import com.project.pdf.with.rabbitmq.repository.PdfRepository;
import com.project.pdf.with.rabbitmq.utils.PdfUtil;
import com.project.pdf.with.rabbitmq.utils.StorageUtil;

public class PdfConsumerTest {

    @InjectMocks
    private PdfConsumer pdfConsumer;

    @Mock
    private PdfRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

@Test
public void testProcessPdfMerge_Success() {
    byte[] file1 = {10, 20, 30};
    byte[] file2 = {40, 50, 60};
    byte[] mergedPdf = {1, 2, 3};

    MergeRequest mergeRequest = new MergeRequest();
    mergeRequest.setName("mergedDocument");
    mergeRequest.setFiles(Arrays.asList(file1, file2));

    try (MockedStatic<PdfUtil> pdfUtilMock = Mockito.mockStatic(PdfUtil.class);
         MockedStatic<StorageUtil> storageUtilMock = Mockito.mockStatic(StorageUtil.class)) {

        pdfUtilMock.when(() -> PdfUtil.mergePdfs(Arrays.asList(file1, file2))).thenReturn(mergedPdf);
        storageUtilMock.when(() -> StorageUtil.saveFile("mergedDocument.pdf", mergedPdf))
                       .thenReturn("/path/to/mergedDocument.pdf");

        pdfConsumer.processPdfMerge(mergeRequest);

        ArgumentCaptor<MergedPdf> captor = ArgumentCaptor.forClass(MergedPdf.class);
        verify(repository).save(captor.capture());

        MergedPdf savedPdf = captor.getValue();
        assertEquals("mergedDocument", savedPdf.getName());
        assertEquals("http://localhost:3001/path/to/mergedDocument.pdf", savedPdf.getLink());
    }
}


}

