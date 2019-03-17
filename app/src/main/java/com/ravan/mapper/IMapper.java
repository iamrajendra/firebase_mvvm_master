package com.ravan.mapper;

public interface IMapper<From, To> {

    To map(From from);
}
