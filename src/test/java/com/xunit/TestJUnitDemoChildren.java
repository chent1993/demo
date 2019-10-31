package com.xunit;

import org.junit.*;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnitDemoChildren extends TestJUnitDemo{

    @BeforeClass
    public static void beforeclassChildren(){
        System.out.println("children before class");
    }
    @AfterClass
    public static void afterclassChildren(){
        System.out.println("children after class");
    }
    @Before
    public void beforeTestChildren(){
        System.out.println("children before");
    }
    @After
    public void afterTestChildren(){
        System.out.println("children after");
    }
    @Test
    public void testDemo1Children(){
        System.out.println("children demo1");
        Assert.assertTrue(true);
    }

    @Test
    public void testDemo2Children(){
        System.out.println("children demo2");
        Assert.assertTrue(true);
    }
    @Test
    public void testDemo3Children(){
        System.out.println("children demo3");
        Assert.assertTrue(true);
    }
}
