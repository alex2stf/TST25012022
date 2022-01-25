package com.demo.samecontent.services;

import com.demo.samecontent.domain.HashPair;
import com.demo.samecontent.util.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.CompletableFuture.supplyAsync;


public abstract class ContentScanner<C> {

    static final Logger log = LoggerFactory.getLogger(ContentScanner.class);

    protected abstract void executeRoot(C rootItem, Visitor<C> visitor);

    protected abstract HashPair extractPair(C item);

    public void scan(C rootFile, Writer writer){
        List<CompletableFuture> futureList = new ArrayList<>();
        final ExecutorService threadPool = Executors.newFixedThreadPool(1);
        final Map<String, List<String>> buffer = new Hashtable<>();

        executeRoot(rootFile, file -> {
            CompletableFuture<HashPair> future = supplyAsync(() -> {
                HashPair pair = extractPair(file);
                if (pair != null) {
                    checkBuffer(buffer, pair.getHash(), pair.getName());
                }
                return pair;
            }, threadPool);
            futureList.add(future);
        });

        CompletableFuture[] futures = new CompletableFuture[futureList.size()];
        futureList.toArray(futures);
        try {
            CompletableFuture.allOf(futures).get();
        } catch (InterruptedException e) {
            log.error("Interrupted ", e);
        } catch (ExecutionException e) {
            log.error("Execution failed ", e);
        }
        threadPool.shutdown();

        buffer.forEach((key, list) -> {
            if (list.size() < 2){
                //DO NOTHING for single elements array
                return;
            }
            for (String s: list){
                doWrite(writer, s + " ");
            }
            doWrite(writer, "\n");

        });

        try {
            writer.flush();
        } catch (IOException e) {
            log.error("Flush failed ", e);
        }

        try {
            writer.close();
        } catch (IOException e) {
            log.error("Close failed ", e);
        }
    }


    private void checkBuffer(Map<String, List<String>> buffer, String key, String value){
        List<String> items;
        if (buffer.containsKey(key)){
            items = buffer.get(key);
        } else {
            items = new ArrayList<>();
        }
        items.add(value);
        buffer.put(key, items);
    }

    private void doWrite(Writer w, String text){
        try {
            w.write(text);
        } catch (IOException e) {
            log.error("Write failed ", e);
        }
    }

}
