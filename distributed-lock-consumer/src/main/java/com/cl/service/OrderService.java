package com.cl.service;

public interface OrderService {

    Boolean order(Long id);

    Boolean orderRedisson(Long id);
}
