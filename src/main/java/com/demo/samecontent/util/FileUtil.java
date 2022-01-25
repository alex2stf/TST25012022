package com.demo.samecontent.util;

import com.demo.samecontent.domain.HashPair;
import com.demo.samecontent.services.ContentScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtil {
    static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static void recursiveScan(File root, Visitor<File> fileVisitor){
        File innerFiles[] = root.listFiles();
        if (innerFiles == null || innerFiles.length <= 0){
            return;
        }


        for (File f: innerFiles){
            if (f.isDirectory() && !f.getName().startsWith(".")){
                recursiveScan(f, fileVisitor);
            }
            else if(f.isFile() && !f.isHidden()){
                fileVisitor.visit(f);
            }
        }
    }


    public static HashPair calculatePair(File file){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("file " + file.getAbsolutePath() + " not found", e);
            return null;
        }
        String md5 = null;
        try {
            md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fileInputStream);
        } catch (IOException e) {
            log.error("file " + file.getAbsolutePath() + " digest failed", e);
            return null;
        }
        return new HashPair(file.getAbsolutePath(), md5);
    }
}
