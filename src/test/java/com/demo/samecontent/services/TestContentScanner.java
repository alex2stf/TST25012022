package com.demo.samecontent.services;

import com.demo.samecontent.domain.HashPair;
import com.demo.samecontent.util.Visitor;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

public class TestContentScanner extends ContentScanner<HashPair> {
    @Override
    protected void executeRoot(HashPair rootItem, Visitor<HashPair> visitor) {
        for (int i = 0; i < 20; i++){
            visitor.visit(new HashPair("name_" + i, "hash_" + i));
        }

        for (int i = 10; i < 20; i+=2){
            visitor.visit(new HashPair("name_" + i, "hash_" + i));
        }
    }

    @Override
    protected HashPair extractPair(HashPair item) {
        return item;
    }

    @Test
    public void testLogic(){
        TestContentScanner testContentScanner = new TestContentScanner();
        StringWriter writer = new StringWriter();
        testContentScanner.scan(new HashPair("demo", "demo"), writer);

        Assert.assertEquals("name_10 name_10 \n" +
                "name_12 name_12 \n" +
                "name_14 name_14 \n" +
                "name_16 name_16 \n" +
                "name_18 name_18 \n" +
                "", writer.toString());
    }

}
