package com.iknowers.learning.spi;

/**
 * @author xushun
 * @date 2019/12/04
 */
public class IntensiveRead implements BookReadable {

    @Override
    public String readStyle(String book) {
        return "【精读】：" + book ;
    }
}
