package com.taotao.controller;

import com.taotao.common.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-9-16 下午 19:49
 */
public class FTPTest {

    @Test
    public void testFtpClient()throws Exception{
        //创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        //创建ftp连接,默认是21端口
        ftpClient.connect("10.99.54.206",21);
        //登录ftp服务器，使用用户名和密码
        ftpClient.login("ftpuser","123456");
        //上传文件
        //读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Pictures\\QQ浏览器截图\\DB05D106B908D22D866C176DB3194EB5.jpg"));
        //设置上传的路径
        ftpClient.changeWorkingDirectory("/www/images");
        //修改上传文件的格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //第一个参数:服务器端文档名
        //第二个参数:上传文档的inputStream
        ftpClient.storeFile("hello.jpg",inputStream);
        //关闭连接
        ftpClient.logout();
    }


    @Test
    public void testFtpUtil()throws Exception{
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Pictures\\QQ浏览器截图\\F6B1EDD7A600944FBC23268B2DFE1631.jpg"));
        FtpUtil.uploadFile("10.99.54.206",21,"ftpuser","123456","/www/images","2018/09/16","hello1.jpg",inputStream);
    }
}
