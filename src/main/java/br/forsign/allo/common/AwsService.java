package br.forsign.allo.common;

import br.forsign.allo.common.file.exceptions.FileException;
import br.forsign.allo.common.utils.CustomMultipartFile;
import br.forsign.allo.importacao.exception.FileStorageException;
import br.forsign.allo.importacao.exception.FileStorageExceptionMessages;
import br.forsign.allo.importacao.service.files.FileService;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/*
 * @author prandini
 * created 10/6/24
 */

@Service
@CommonsLog
public class AwsService {

    @Resource
    private AmazonS3Client amazonS3Client;
    @Autowired
    private FileService fileService;

    public void createS3Bucket(String bucketName) {
        if(amazonS3Client.doesBucketExist(bucketName)) {
            log.info(String.format("Bucket com o nome %s já existe", bucketName));
            return;
        }
        amazonS3Client.createBucket(bucketName);
    }

    public List<Bucket> listBuckets() {
        return amazonS3Client.listBuckets();
    }

    public void deleteBucket(String bucketName) {
        try{
            amazonS3Client.deleteBucket(bucketName);
        }catch (AmazonServiceException e){
            log.error(e.getErrorMessage());
            return;
        }
    }

    public PutObjectResult putObject(String bucketName, String key, File file) {
        return amazonS3Client.putObject(bucketName, key, file);
    }

    public String uploadFile(MultipartFile file, String bucketName, String filename) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        amazonS3Client.putObject(bucketName, filename, file.getInputStream(), objectMetadata);

        return amazonS3Client.getUrl(bucketName, filename).toString();
    }

    public List<S3ObjectSummary> listObjects(String bucketName){
        ObjectListing objects = amazonS3Client.listObjects(bucketName);

        return objects.getObjectSummaries();
    }

    public CustomMultipartFile downloadObject(String bucketName, String objectName){
        S3Object s3Object = amazonS3Client.getObject(bucketName, objectName);

        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        try {
            String path = "." + File.separator + objectName;
            File file = this.fileService.createEmptyTmpFile(path);
            FileUtils.writeByteArrayToFile(file, inputStream.readAllBytes());
            return new CustomMultipartFile(file);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new FileStorageException(FileStorageExceptionMessages.erroCriandoArquivo(e.getMessage()));
        }
    }

    public void deleteObject(String bucketName, String objectName){
        amazonS3Client.deleteObject(bucketName, objectName);
    }

    public void moveObject(String bucketSourceName, String objectName, String targetBucketName){
        amazonS3Client.copyObject(bucketSourceName, objectName, targetBucketName, objectName);
    }



}
