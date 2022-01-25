package com.demo.samecontent.services;

import com.demo.samecontent.domain.HashPair;
import com.demo.samecontent.util.FileUtil;
import com.demo.samecontent.util.Visitor;

import java.io.File;

import static com.demo.samecontent.util.FileUtil.recursiveScan;

public class FileContentScanner extends ContentScanner<File> {

    @Override
    protected void executeRoot(File rootFile, Visitor<File> visitor) {
        recursiveScan(rootFile, visitor);
    }

    @Override
    protected HashPair extractPair(File item) {
        return FileUtil.calculatePair(item);
    }
}
