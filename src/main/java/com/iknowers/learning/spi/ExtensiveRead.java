package com.iknowers.learning.spi;

/**
 * @author xushun
 * @date 2019/12/04
 */
public class ExtensiveRead implements BookReadable {

    @Override
    public String readStyle(String book) {
        return "【泛读】：" + book ;
    }
}
