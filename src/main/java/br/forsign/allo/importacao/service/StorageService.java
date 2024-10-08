package br.forsign.allo.importacao.service;

import br.forsign.allo.common.AwsService;
import br.forsign.allo.common.file.exceptions.FileException;
import br.forsign.allo.common.utils.CustomMultipartFile;
import br.forsign.allo.importacao.exception.FileStorageException;
import br.forsign.allo.importacao.service.files.FileService;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * @author prandini
 * created 10/6/24
 */

@Service
@CommonsLog
public class StorageService implements FileStorageService{

    private static String bucketName = "bucket-allo";

    private static String CAMINHO_ALLO = "allo/";

    private static String HEADER_LOCATION = "location";

    @Value("${allo.storage.upload.enabled}")
    private boolean storageEnabled = true;

    @Resource
    private FileService fileService;

    @Resource
    private AwsService awsService;

    @Override
    public String upload(MultipartFile file, String path, String filename) {
        if(!storageEnabled) {
            log.info("Integração com storage desabilitada, pulando requisição de upload.");
        }

        try{
            log.info(String.format("Enviando arquivo %s para o storage.", path));

            filename = this.cleanFilename(filename);
            path = CAMINHO_ALLO.concat(path).concat(File.separator).concat(filename);
            String response = this.awsService.uploadFile(file, bucketName, path);
            log.info(String.format("Arquivo %s enviado para o storage.", path));

            return response;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new FileStorageException(e);
        }
    }

    @Override
    public String upload(MultipartFile file, String path) {
        return upload(file, path, file.getOriginalFilename());
    }

    @Override
    public String upload(File file, String path, String filename) {
        return this.upload(file, path, filename);
    }

    @Override
    public String upload(InputStream is, String path, String name) throws FileException {
        File file = this.fileService.createTmpFile(is, name);
        String result = this.upload(file, path, name);
        file.delete();
        return result;
    }

    @Override
    public void delete(String url) {
//        if(!storageEnabled){
//            log.info("Integração com storage desligada, pulando requisição de delete..");
//            return;
//        }
//
//        try{
//
//        }
    }

    @Override
    public MultipartFile download(String uri) {
        if(!storageEnabled){
            log.info("Integração com storage desligada, pulando requisição de delete..");
            return null;
        }

        try{
            log.info(String.format("Arquivo localizado em %s.", uri));

            String objectName = uri.replace("https://bucket-allo.s3-sa-east-1.amazonaws.com/", "");

            CustomMultipartFile multipartfile = awsService.downloadObject(bucketName, objectName);

            return multipartfile;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new FileStorageException(e);
        }
    }

    private String cleanFilename(String name){
        return name.toLowerCase()
                .replaceAll(" ", "_")
                .replaceAll("ç", "c")
                .replaceAll("[êé]", "e")
                .replaceAll("[áäãà]", "a")
                .replaceAll("[íì]", "i")
                .replaceAll("[ôóòõ]", "o")
                .replaceAll("[úũûù]", "u")
                .replaceAll("ñ", "n")
                .replaceAll("[&)(><]", "");
    }
}
