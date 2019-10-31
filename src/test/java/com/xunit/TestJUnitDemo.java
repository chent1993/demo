package com.xunit;

import org.junit.*;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnitDemo {

    @BeforeClass
    public static void beforeclass(){
        System.out.println("before class");
    }
    @AfterClass
    public static void afterclass(){
        System.out.println("after class");
    }
    @Before
    public void beforeTest(){
        System.out.println("before");
    }
    @After
    public void afterTest(){
        System.out.println("after");
    }
    @Test
    public void testDemo1(){
        System.out.println("demo1");
        Assert.assertTrue(true);
    }

    @Test
    public void testDemo2(){
        System.out.println("demo2");
        Assert.assertTrue(true);
    }
    @Test
    public void testDemo3(){
        System.out.println("demo3");
        Assert.assertTrue(true);
    }
}
