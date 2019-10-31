package com.group;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestGroups {

    @Category({SlowGroup.class,FastGroup.class})
    @Test
    public void demo1(){
        Assert.assertTrue(true);
    }
    @Category(FastGroup.class)
    @Test
    public void demo2(){
        Assert.assertEquals("diff two value",10,11);
        Assert.assertTrue(true);
    }
    @Category(SlowGroup.class)
    @Test
    public void demo3(){
    // assertThat("actual value close to 10",10,equalTo(11));
     //assertThat("actual value close to 10",10.2,closeTo(10.0,0.1));
        //anyOf任何一个匹配都可以；；hasItems
     assertThat("actual value close to 10",10.2,anyOf(closeTo(10.0,0.1),equalTo(10.2)));

    }
    @Category(FastGroup.class)
    @Test
    public void demo4(){
        Assert.assertTrue(true);
    }
}
