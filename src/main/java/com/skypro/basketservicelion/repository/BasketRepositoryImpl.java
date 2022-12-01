package com.skypro.basketservicelion.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Repository
        //@RequestScope // - на каждый запрос мы создаём новую корзину. Данные сохраняться между запросами не будут.
@SessionScope // На каждую сессию (куку) создаётся одна корзина, то есть для каждого нового клиента создается новый объект-корзина
public class BasketRepositoryImpl {
    private final List<Integer> idsListOfProducts = new ArrayList<>();

    public void addProductToBasket(List<Integer> idsOfProducts) {
        idsListOfProducts.addAll(idsOfProducts);
    }

    public List<Integer> getIdsListOfProducts() {
        return this.idsListOfProducts;
    }
}
