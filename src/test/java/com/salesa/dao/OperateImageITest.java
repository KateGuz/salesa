package com.salesa.dao;

import com.salesa.entity.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/WEB-INF/root-context.xml"})
public class OperateImageITest {

    @Autowired
    private ImageDao imageDao;

    @Test
    public void testSaveImage() throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src/test/resources/img/1-kenny006.jpg"));
        byte[] rawImageBytes = new byte[5_000_000];
        int pictureSize = 0;
        int readBytes;
        while ((readBytes = bufferedInputStream.read(rawImageBytes, pictureSize, 1024)) != -1) {
            pictureSize += readBytes;
        }
        bufferedInputStream.close();

        Image image = new Image();
        image.setType("P");
        image.setContent(Arrays.copyOf(rawImageBytes, pictureSize));
        imageDao.saveAdvertImage(image, 1);
    }

    @Test
    public void testGetAdvertImage() throws Exception {
        Image advertImage = imageDao.getAdvertImage(1);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("src/test/resources/img/testGetAdvertImage.jpg"));
        bufferedOutputStream.write(advertImage.getContent());
        bufferedOutputStream.close();
    }

}
