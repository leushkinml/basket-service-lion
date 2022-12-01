package com.skypro.basketservicelion.service;

import com.skypro.basketservicelion.repository.BasketRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepositoryImpl basketRepositoryImpl; // - готовимся сделать инъекцию зависимости Сервиса от Репозитория.

    public BasketServiceImpl(BasketRepositoryImpl basketRepositoryImpl) { // - делаем инъекцию зависимости Сервиса от Репозитория через конструктор
        this.basketRepositoryImpl = basketRepositoryImpl;
    }

    @Override
    public void addProductToBasket(List<Integer> idsOfProducts) {
        // Этот метод принимает только целочисленные значения, так как использован List<Integer>
        this.basketRepositoryImpl.addProductToBasket(idsOfProducts);
    }
    @Override
    public List<Integer> getProductsFromBasket() {
        // Выводит список ID всех записанных значений
        return this.basketRepositoryImpl.getIdsListOfProducts();
    }
}
