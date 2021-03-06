package com.salesa.service.impl;

import com.salesa.dao.ImageDao;
import com.salesa.entity.Image;
import com.salesa.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public void saveAdvertImage(Image image, int advertId) {
        imageDao.saveAdvertImage(image, advertId);
    }

    @Override
    public Image getAdvertImageById(int imageId) {
        return imageDao.getAdvertImageById(imageId);
    }

    @Override
    public List<Image> getAdvertImages(int advertId) {
        return imageDao.getAdvertImages(advertId);
    }

    @Override
    public void remove(int imageId) {
        imageDao.remove(imageId);
    }
}
