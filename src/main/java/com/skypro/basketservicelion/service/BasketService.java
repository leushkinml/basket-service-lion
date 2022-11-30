package com.skypro.basketservicelion.service;

import java.util.List;

public interface BasketService {

    void addProductToBasket(List<Integer> idsOfProducts);

    List<Integer> getProductsFromBasket();
}
