package com.demo.samecontent.util;


import org.junit.Assert;
import org.junit.Test;

import static com.demo.samecontent.util.StringUtil.hasText;

public class StringUtilTest {


    @Test
    public void testHasText(){
        Assert.assertFalse(hasText(""));
        Assert.assertFalse(hasText("  "));
        Assert.assertFalse(hasText("  \t"));
        Assert.assertFalse(hasText("\n\n\n   \n\n   \t \n \n"));
        Assert.assertFalse(hasText(null));
        Assert.assertTrue(hasText("null"));
        Assert.assertTrue(hasText("null\n\t  "));
        Assert.assertTrue(hasText("   null\n\t  "));
    }
}