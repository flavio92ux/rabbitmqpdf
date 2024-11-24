package com.project.pdf.with.rabbitmq.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MergedPdfTest {

    @Test
    public void testNoArgsConstructor() {
        MergedPdf mergedPdf = new MergedPdf();

        assertNotNull(mergedPdf);
    }

    @Test
    public void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();

        MergedPdf mergedPdf = new MergedPdf(1L, "test.pdf", "http://localhost/files/test.pdf", now);

        assertEquals(1L, mergedPdf.getId());
        assertEquals("test.pdf", mergedPdf.getName());
        assertEquals("http://localhost/files/test.pdf", mergedPdf.getLink());
        assertEquals(now, mergedPdf.getCreatedAt());
    }

    @Test
    public void testPartialConstructor() {
        MergedPdf mergedPdf = new MergedPdf("test.pdf", "http://localhost/files/test.pdf");

        assertEquals("test.pdf", mergedPdf.getName());
        assertEquals("http://localhost/files/test.pdf", mergedPdf.getLink());
        assertEquals(0L, mergedPdf.getId());
        assertNull(mergedPdf.getCreatedAt());
    }

    @Test
    public void testSettersAndGetters() {
        MergedPdf mergedPdf = new MergedPdf();
        LocalDateTime now = LocalDateTime.now();

        mergedPdf.setId(1L);
        mergedPdf.setName("updated.pdf");
        mergedPdf.setLink("http://localhost/files/updated.pdf");
        mergedPdf.setCreatedAt(now);

        assertEquals(1L, mergedPdf.getId());
        assertEquals("updated.pdf", mergedPdf.getName());
        assertEquals("http://localhost/files/updated.pdf", mergedPdf.getLink());
        assertEquals(now, mergedPdf.getCreatedAt());
    }
}

