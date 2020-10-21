package com.ingsw.restservice.storage;

import org.springframework.web.multipart.MultipartFile;


public interface StorageService {

    void init();

    String store(MultipartFile file);

    String delete(String fileUrl);


}
