package com.levi.xymap.service.impl;

import com.levi.xymap.entity.Document;
import com.levi.xymap.service.DocumentService;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2023/2/21 22:54
 * @Version 1.0
 **/
public class DocumentServiceImp implements DocumentService {
    @Override
    public List<List> readExcel(InputStream inputStream) {
        return null;
    }

    @Override
    public List<Document> readZip(InputStream inputStream) {
        return null;
    }

    @Override
    public List<Document> readZip(File file) throws IOException {
        List<Document> documents = new ArrayList<>();
        ZipFile zipFile = new ZipFile(file,0, Charset.forName("GBK"));
        Enumeration<ZipEntry> zipEnumeration = (Enumeration<ZipEntry>) zipFile.entries();
        return null;
    }

    @Override
    public List<Document> readZipIn(InputStream inputStream) throws IOException {
        List<Document> documents = new ArrayList<>();
        File tempFile = new File(System.getProperty("java.io.tmpdir") + File.separator + "temp.zip");
        FileOutputStream output = new FileOutputStream(tempFile);
        IOUtils.copyLarge(inputStream, output, 0, inputStream.available(), new byte[inputStream.available()]);
        output.close();
        readZip(tempFile);
        return documents;
    }
}
