package com.maybe.util;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maybe on 2016/6/20
 * Maybe has infinite possibilities
 */
public class GetName {
    /**
     * 获取指定路径下的文件及文件夹名字
     *
     * @param path 文件夹路径
     * @return 返回一个由文件名字符串组成的数组
     */
    public String[] getDirectoryAndFileName(String path) {
        File file = new File(path);
        return file.list();
    }

    /**
     * 获取指定路径下的文件名字   ----过滤掉了文件夹,使用 list(FileFilter filter)
     *
     * @param path 文件夹路径
     * @return 返回一个由文件名字符串组成的数组
     */
    public String[] getFileName(String path) {
        File file = new File(path);
        File[] filesName = file.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        String[] stringArray = new String[filesName.length];
        for (int i = 0; i < filesName.length; i++) {
            String name = filesName[i].getName();

            stringArray[i] = name.substring(0, name.indexOf("."));
        }
        return stringArray;
    }

    /**
     * 获取指定路径下的所有文件名(过滤掉文件夹),可穿透子文件夹,文件名存放在 fileName 当中
     *
     * @param path     文件夹路径
     * @param fileName 文件名数组
     */

    public void getAllFileName(String path, ArrayList<String> fileName) {
        File file = new File(path);
        File[] files = file.listFiles();//文件数组
        String[] names = getFileName(path);//文件名数组
        if ((names != null) && (files != null)) {
            fileName.addAll(Arrays.asList(names));
            for (File a : files) {
                if (a.isDirectory()) {//如果是文件夹,调用自己
                    getAllFileName(a.getAbsolutePath(), fileName);
                }
            }
        } else {
            System.out.println("路径下不存在文件夹,也不存在文件");
        }
    }

    @Test
    public void test() {
        String[] directoryAndFileName = getDirectoryAndFileName("C:\\Users\\Administrator\\Desktop\\测试文件夹");
        System.out.println("获取文件及文件夹名字");
        for (String name : directoryAndFileName) {
            System.out.println(name);
        }
        System.out.println("--------------------------------");
        String[] fileName = getFileName("C:\\Users\\Administrator\\Desktop\\测试文件夹");
        System.out.println("只获取文件名字");
        for (String name : fileName) {
            System.out.println(name);
        }
        System.out.println("--------------------------------");
        ArrayList<String> listFileName = new ArrayList<String>();
        getAllFileName("C:\\Users\\Administrator\\Desktop\\测试文件夹", listFileName);
        System.out.println("获取文件---穿透文件夹");
        for (String name : listFileName) {
            System.out.println(name);
        }

    }
}