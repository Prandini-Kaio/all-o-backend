package br.forsign.allo.importacao.service;

import br.forsign.allo.common.file.exceptions.FileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

public interface FileStorageService {
    String upload(MultipartFile file, String path, String filename);

    String upload(MultipartFile file, String path);

    String upload(InputStream is, String path, String name) throws FileException;

    String upload(File file, String path, String filename);

    void delete(String uri);

    MultipartFile download(String uri);
}
