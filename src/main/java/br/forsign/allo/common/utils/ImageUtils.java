package br.forsign.allo.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImageUtils {

    public static String saveImageFile(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return "";
        }

        try {
            byte[] bytes = file.getBytes();

            String directoryPath = "src/main/resources/" + path;

            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs(); // Cria os diretórios se não existirem
            }

            UUID uuid = UUID.randomUUID();
            var nameImage = uuid.toString() + '.' + file.getContentType().split("/")[1];
            // Salva o arquivo no diretório especificado
            File uploadedFile = new File(directory, nameImage);
            try (FileOutputStream fos = new FileOutputStream(uploadedFile)) {
                fos.write(bytes);
            }

            return nameImage;
        } catch (IOException e) {
            return "erro";
        }
    }
}
