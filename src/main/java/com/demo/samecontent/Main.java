package com.demo.samecontent;

import com.demo.samecontent.exceptions.AccessException;
import com.demo.samecontent.exceptions.UsageException;
import com.demo.samecontent.services.FileContentScanner;
import com.demo.samecontent.util.StringUtil;

import java.io.File;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0 || !StringUtil.hasText(args[0])){
            throw new UsageException("missing required path argument");
        }
        String rootPath = args[0];
        File rootFile = new File(rootPath);

        if (!rootFile.exists()){
            throw new AccessException("File " + rootFile.getAbsolutePath() + " not found");
        }


        FileContentScanner contentScanner = new FileContentScanner();

        contentScanner.scan(rootFile, new PrintWriter(System.out));

        System.out.println("complete");


    }
}
