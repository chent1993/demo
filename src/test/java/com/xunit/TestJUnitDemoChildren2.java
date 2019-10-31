package com.xunit;

import org.junit.*;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnitDemoChildren2 extends TestJUnitDemo{

    @BeforeClass
    public static void beforeclassChildren(){
        System.out.println("children2 before class");
    }
    @AfterClass
    public static void afterclassChildren(){
        System.out.println("children2 after class");
    }
    @Before
    public void beforeTestChildren(){
        System.out.println("children2 before");
    }
    @After
    public void afterTestChildren(){
        System.out.println("children2 after");
    }
    @Test
    public void testDemo1Children(){
        System.out.println("children2 demo1");
        Assert.assertTrue(true);
    }

    @Test
    public void testDemo2Children(){
        System.out.println("children2 demo2");
        Assert.assertTrue(true);
    }
    @Test
    public void testDemo3Children(){
        System.out.println("children2 demo3");
        Assert.assertTrue(true);
    }
}
