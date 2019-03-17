package com.ravan.mapper;

import com.ravan.entity.UserEntity;
import com.ravan.model.User;

public class UserMapper  extends FirebaseMapper<UserEntity, User>  {
    @Override
    public User map(UserEntity userEntity) {
        User user  =  new User();
        user.setEmail(userEntity.getEmail());
        user.setName(userEntity.getName());
        user.setImageUrl(userEntity.getImageUrl());

        return user;
    }
}
