package com.ravan.repository;

import com.ravan.mapper.ArticleMapper;
import com.ravan.mapper.UserMapper;
import com.ravan.model.Article;
import com.ravan.model.User;

public class UserRepo   extends FirebaseDatabaseRepository<User> {

    public UserRepo() {
        super(new UserMapper());
    }

    @Override
    protected String getRootNode() {
        return "users";

    }
}
