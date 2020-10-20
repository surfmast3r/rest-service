package com.ingsw.restservice.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file);

    String delete(String fileUrl);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    Path getImagesFolderLocation();

    void deleteAll();

}
