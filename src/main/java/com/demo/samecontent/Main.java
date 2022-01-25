package com.demo.samecontent;

import com.demo.samecontent.exceptions.AccessException;
import com.demo.samecontent.exceptions.UsageException;
import com.demo.samecontent.services.ContentScanner;
import com.demo.samecontent.services.FileContentScanner;
import com.demo.samecontent.util.FileUtil;
import com.demo.samecontent.util.StringUtil;

import java.io.File;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        File rootFile = FileUtil.getRootFromArgs(args);
        ContentScanner contentScanner = new FileContentScanner();
        contentScanner.scan(rootFile, new PrintWriter(System.out));
    }
}
