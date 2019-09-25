package com.xunit;

import org.junit.Assert;
import org.junit.Test;

public class TestJUnitDemo {
    @Test
    public void testDemo(){
        Assert.assertTrue(true);
    }

    @Test
    public void testDemo2(){
        Assert.assertTrue(false);
    }
}
