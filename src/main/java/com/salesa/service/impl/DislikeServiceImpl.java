package com.salesa.service.impl;

import com.salesa.dao.DislikeDao;
import com.salesa.service.DislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DislikeServiceImpl implements DislikeService {
    @Autowired
    private DislikeDao dislikeDao;


    @Override
    public Integer checkDislike(int senderId, int receiverId) {
        return dislikeDao.checkDislike(senderId, receiverId);
    }

    @Override
    public void updateDislike(int senderId, int receiverId) {
        dislikeDao.updateDislike(senderId, receiverId);
    }
}
