package com.iknowers.learning.spi;

/**
 * @author xushun
 * @date 2019/12/04
 */
public interface BookReadable {

    /**
     * 读书方式
     *
     * @param book
     * @return
     */
    String readStyle(String book);
}
