package com.xunit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestJUnitDemoChildren.class,
        TestJUnitDemo.class,
        TestJUnitDemoChildren2.class

})
public class TestSuite {
}
