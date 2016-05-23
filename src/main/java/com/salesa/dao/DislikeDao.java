package com.salesa.dao;

public interface DislikeDao {
    Integer checkDislike(int senderId, int receiverId);
    void updateDislike(int senderId, int receiverId);
}
