package com.hive.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
*
* @RunWith(SpringJUnit4ClassRunner.class)   表示该测试用例是运用junit4进行测试，也可以换成其他测试框架

* @ContextConfiguration：locations：可以通过该属性手工指定 Spring 配置文件所在的位置，可以指定一个或多个 Spring 配置文件。如下所示：

* @ContextConfiguration(locations={“xx/yy/beans1.xml”,” xx/yy/beans2.xml”})

* inheritLocations：是否要继承父测试用例类中的 Spring 配置文件，默认为 true。

* @Rollback(false) ：表示该测试用例回滚
* @author zhangyl
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext-test.xml"},inheritLocations=false)
@Transactional
public class BaseTest {
}
