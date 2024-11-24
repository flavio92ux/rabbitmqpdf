package com.project.pdf.with.rabbitmq.dto;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MergeRequestTest {

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        MergeRequest mergeRequest = new MergeRequest();

        // Assert
        assertNotNull(mergeRequest);
        assertNull(mergeRequest.getName());
        assertNull(mergeRequest.getFiles());
    }

    @Test
    public void testConstructorWithMultipartFiles() {
        // Arrange
        String name = "TestMerge";
        MultipartFile file1 = new MockMultipartFile("file1", "file1.pdf", "application/pdf", new byte[]{1, 2, 3});
        MultipartFile file2 = new MockMultipartFile("file2", "file2.pdf", "application/pdf", new byte[]{4, 5, 6});
        List<MultipartFile> multipartFiles = List.of(file1, file2);

        // Act
        MergeRequest mergeRequest = new MergeRequest(name, multipartFiles);

        // Assert
        assertEquals(name, mergeRequest.getName());
        assertNotNull(mergeRequest.getFiles());
        assertEquals(2, mergeRequest.getFiles().size());
        assertArrayEquals(new byte[]{1, 2, 3}, mergeRequest.getFiles().get(0));
        assertArrayEquals(new byte[]{4, 5, 6}, mergeRequest.getFiles().get(1));
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        MergeRequest mergeRequest = new MergeRequest();
        String name = "TestName";
        List<byte[]> files = List.of(new byte[]{1, 2, 3}, new byte[]{4, 5, 6});

        // Act
        mergeRequest.setName(name);
        mergeRequest.setFiles(files);

        // Assert
        assertEquals(name, mergeRequest.getName());
        assertEquals(files, mergeRequest.getFiles());
    }
}

