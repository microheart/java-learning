package com.iknowers.learning.spi;

/**
 * @author xushun
 * @date 2019/12/04
 */
public class OtherRead implements BookReadable {

    @Override
    public String readStyle(String book) {
        return "【其它读】：" + book ;
    }
}
