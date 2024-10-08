package br.forsign.allo.importacao.service.files;

import br.forsign.allo.common.file.exceptions.FileException;
import br.forsign.allo.importacao.exception.FileStorageException;
import br.forsign.allo.importacao.exception.FileStorageExceptionMessages;
import jakarta.annotation.PostConstruct;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/*
 * @author prandini
 * created 10/6/24
 */

@Component
@CommonsLog
public class FileService {

    @Value("${allo.system.tmp.path:/tmp/}")
    private String tmp_path;

    @PostConstruct
    private void verificarEIniciarDiretorioTemporario() {

        // diretorio do usuario
        Path path = Paths.get(System.getProperty("user.dir").concat(tmp_path));

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                log.error("Erro ao criar o diretório temporário: " + e.getMessage());
            }
        }
    }

    public File createFile(String fileName, String content) throws IOException {
        File file = new File(System.getProperty("user.dir").concat(tmp_path).concat(fileName).concat(".txt"));
        try(BufferedWriter writer =  new BufferedWriter(new FileWriter(file))){
            writer.write(content);
        }

        file.deleteOnExit();
        return file;
    }

    public File createFile(MultipartFile multipartFile, String pathString, String filename){
        if(!pathString.endsWith("/"))
            pathString.concat("/");

        Path path = Path.of(getFullFilenameTmp(pathString.concat(filename)));
        try{
            File newFile = path.toFile();
            File parent = newFile.getParentFile();
            if(parent != null && !parent.exists() && !parent.mkdirs())
                throw new FileStorageException(FileStorageExceptionMessages.erroCriandoArquivo("Falha ao criar diretórios do arquivo desejado."));

            multipartFile.transferTo(path);
            File file = path.toFile();
            file.deleteOnExit();
            return file;
        }catch(IOException e){
            throw new FileStorageException(FileStorageExceptionMessages.erroCriandoArquivo(e.getMessage()));
        }
    }

    public File createFile(MultipartFile multipartFile, String pathString){
        return createFile(multipartFile, pathString, multipartFile.getOriginalFilename());
    }

    public File createFile(File file, String path) {
        return this.createFile(file, path, file.getName());
    }

    public File createFile(File file, String path, String filename) {
        try{
            File newFile = new File(getFullFilenameTmp(path.concat("/").concat(filename)));
            File parent = newFile.getParentFile();
            if(parent != null && !parent.exists() && !parent.mkdirs())
                throw new FileStorageException(FileStorageExceptionMessages.erroCriandoArquivo("Falha ao criar diretórios do arquivo desejado."));

            newFile.createNewFile();
            Files.copy(Path.of(file.getAbsolutePath()), Path.of(getFullFilenameTmp(path.concat("/").concat(file.getName()))), REPLACE_EXISTING);
            newFile.deleteOnExit();
            return newFile;
        }catch (IOException e){
            throw new FileStorageException(FileStorageExceptionMessages.erroCriandoArquivo(e.getMessage()));
        }
    }

    public File createPDFFile(String fileName) {
        File file = new File(System.getProperty("user.dir").concat(tmp_path).concat(fileName).concat(".pdf"));
        file.deleteOnExit();
        return file;
    }

    public boolean deleteTmpFile(String path){
        File file = new File(System.getProperty("user.dir").concat(tmp_path).concat(path));
        return deleteTmpFile(file);
    }

    public boolean deleteTmpFile(File file){
        return file.delete();
    }

    public File createTmpFile(InputStream is, String filename) throws FileException{
        try{
            File tmp = new File(getFullFilenameTmp(filename));
            try (FileOutputStream outputStream = new FileOutputStream(tmp)) {
                int read;
                byte[] bytes = new byte[1024];
                while ((read = is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            }

            tmp.deleteOnExit();
            return tmp;
        }catch(IOException | NullPointerException e){
            throw new FileStorageException("Erro criando arquivo temporário: " + e.getMessage(), e);
        }
    }

    public File createEmptyTmpFile(String filename){
        File file = new File(getFullFilenameTmp(filename));
        file.deleteOnExit();
        return file;
    }

    public File getFile() {
        return null;
    }

    public String getFullFilenameTmp(String filename) {
        return System.getProperty("user.dir").concat(tmp_path).concat(filename);
    }
}
