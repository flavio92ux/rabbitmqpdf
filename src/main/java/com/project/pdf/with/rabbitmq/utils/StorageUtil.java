package com.project.pdf.with.rabbitmq.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageUtil {

    // Define o diretório onde os arquivos serão salvos
    private static final String BASE_DIRECTORY = "uploads";

    /**
     * Salva um arquivo localmente e retorna o link de acesso.
     *
     * @param fileName Nome do arquivo a ser salvo.
     * @param fileData Dados do arquivo em formato byte[].
     * @return Caminho ou URL para acessar o arquivo salvo.
     * @throws IOException Em caso de falha ao salvar o arquivo.
     */
    public static String saveFile(String fileName, byte[] fileData) throws IOException {
        // Certifique-se de que o diretório base existe
        File baseDir = new File(BASE_DIRECTORY);
        if (!baseDir.exists()) {
            baseDir.mkdirs(); // Cria o diretório se não existir
        }

        // Gera o caminho completo para o arquivo
        Path filePath = Paths.get(BASE_DIRECTORY, fileName);

        // Salva os dados no arquivo
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(fileData);
        }

        // Retorna o link para o arquivo salvo (exemplo: caminho relativo ou URL local)
        return "/files/" + fileName;
    }
}
