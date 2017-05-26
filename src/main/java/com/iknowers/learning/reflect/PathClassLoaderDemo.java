package com.iknowers.learning.reflect;

import java.io.*;

/**
 *
 * 1. 自定义路径下查找自定义的class类文件
 * 2. 确保安全性：Java字节码很容易被反编译，对我们自己的要加载的类做特殊处理
 * 3. 实现类的热部署
 *
 * @author Shun Xu
 */
public class PathClassLoaderDemo extends ClassLoader {
    private String classPath;

    public PathClassLoaderDemo(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader pcl = new PathClassLoaderDemo("C:\\Devp\\Java\\learning\\java-learning\\target\\classes");
        Class c = pcl.loadClass("com.iknowers.learning.reflect.SimpleObj");
        System.out.println(c.newInstance());

        printClassLoader(PathClassLoaderDemo.class.getClassLoader());

    }

    public static void printClassLoader(ClassLoader classLoader) {
        if (classLoader == null)
            return;
        ClassLoader parent = classLoader.getParent();
        printClassLoader(parent);
        System.out.println(classLoader);
    }
}