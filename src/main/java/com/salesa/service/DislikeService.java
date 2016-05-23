package com.salesa.service;

public interface DislikeService {
    Integer checkDislike(int senderId, int receiverId);
    void updateDislike(int senderId, int receiverId);
}
