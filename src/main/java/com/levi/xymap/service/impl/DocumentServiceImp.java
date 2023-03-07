package com.levi.xymap.service.impl;

import com.levi.xymap.entity.Document;
import com.levi.xymap.service.DocumentService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

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
@Service
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
    public List<Document> readZip(File file)  {
        List<Document> documents = new ArrayList<>();
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("new ZipFile压缩出现问题");
        }
        Enumeration<ZipEntry> zipEnumeration = (Enumeration<ZipEntry>) zipFile.entries();
        while(zipEnumeration.hasMoreElements() == true ){
            ZipEntry zipEntry = zipEnumeration.nextElement();
            if(zipEntry.isDirectory()){
                continue;
            }
            InputStream inputStream = null;
            try {
                String fileName = zipEntry.getName();
                Document document = Document.getDocByName(fileName); // 初始化文件名称

                inputStream = zipFile.getInputStream(zipEntry);// 复制文件内容
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                IOUtils.copy(inputStream, bos);
                document.setContent(bos.toByteArray());
                documents.add(document);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }

        }
        return documents;
    }

    @Override
    public List<Document> readZipIn(InputStream inputStream) throws IOException {
        List<Document> documents = new ArrayList<>();
        File tempFile = new File(System.getProperty("java.io.tmpdir") +File.separator + System.currentTimeMillis() + "temp.zip");
        FileOutputStream output = new FileOutputStream(tempFile);
        IOUtils.copyLarge(inputStream, output, 0, inputStream.available(), new byte[inputStream.available()]);
        output.close();
        IOUtils.closeQuietly(output);
        documents = readZip(tempFile);
        return documents;
    }
}
