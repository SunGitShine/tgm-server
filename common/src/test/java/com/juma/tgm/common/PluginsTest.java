package com.juma.tgm.common;

import org.junit.Test;
import org.mybatis.generator.api.ShellRunner;

/**
 * 功能 : 
 * 直接执行这个类,可以反向生成对应的类
 * @author : Bruce(刘正航) 09:03 2019-05-11
 */
public class PluginsTest {
    @Test
    public void run(){
        String config = this.getClass().getClassLoader().getResource("generatorConfig-base.xml").getFile();
        String[] arg = { "-configfile", config, "-overwrite" };
        ShellRunner.main(arg);
    }
}
