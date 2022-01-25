package com.demo.samecontent.util;


import com.demo.samecontent.exceptions.AccessException;
import com.demo.samecontent.exceptions.UsageException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.io.File;

import static com.demo.samecontent.util.FileUtil.getRootFromArgs;
import static org.junit.Assert.assertThrows;

public class FileUtilTest {

    @Test
    public void testThrows(){
        assertThrows(UsageException.class, () -> getRootFromArgs(new String[]{}));
        assertThrows(UsageException.class, () -> getRootFromArgs(new String[]{null}));
        assertThrows(UsageException.class, () -> getRootFromArgs(new String[]{""}));
        assertThrows(AccessException.class, () -> getRootFromArgs(new String[]{"xxxx-invalid.file"}));
    }

}
