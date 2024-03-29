package com.levi.xymap.service;

import com.levi.xymap.entity.Document;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public interface DocumentService {
    List<List> readExcel(InputStream inputStream);
    List<Document> readZip(InputStream inputStream);
    List<Document> readZip(File file) throws IOException;
    List<Document> readZipIn(InputStream inputStream) throws IOException;
}
