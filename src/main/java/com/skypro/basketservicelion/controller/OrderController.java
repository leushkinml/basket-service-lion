package com.skypro.basketservicelion.controller;

import com.skypro.basketservicelion.service.BasketService;
import com.skypro.basketservicelion.service.BasketServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
/*
Запросы с телом:
* POST - создание
* PUT - изменение
* PATCH - изменение полей
-------------------------
Запросы с телом и без тела:
* DELETE - по соглашению считается запросом без тела
-------------------------
Запросы без тела:
* GET - получение
* есть ещё другие

*/

@RestController
@RequestMapping("/order")
// Раньше было так: @RequestMapping("/store/order"),
// но так мы в application.properties
// добавили свойство server.servlet.context-path=/store то здесь эту часть пути /order нужно убрать

public class OrderController {

    private final BasketService basketService; // создали поле интерфейса Сервис в Контроллере для того, чтобы потом сделать инъекцию зависимости.

    public OrderController(BasketServiceImpl basketService) {
        // - сделали инъекцию зависомости Контроллера от интерфейса Сервис через конструктор. Стринг будет обязан принести нам basketService и передать его в аргумент
        this.basketService = basketService;
    }

    @GetMapping("/add") // Браузер по умолчанию отправляет GET запросы. POST тоже может, но это сложнее
    public ResponseEntity<String> addProduct(@RequestParam("id") List<Integer> idsOfProducts) {
        // В скобках указали имя параметра id.
        // который который в запросе и в пути /store/order/add?id=1
        // Инече будет брать имя нашего Листа, то есть ids и нужно будет в запросе писать /store/order/add?idsOfProduct=1
        // добавлять в браузере можно через запятую 1,2,3,4,5 или через &: id=1&id=2&id=3&id=4&id=5.
        // Смешивать два стиля передачи параметров нельзя
        // Если нужно несколько параметров в методе, то пишем их через запятую
        // @RequestParam("id") List<Integer> idsOfProducts, @RequestParam("x") Integer x, @RequestParam("y") Integer y
        // а если один из параметров у нас может быть пустым или null, то в скобках указываем required: @RequestParam(value = "x", required = false) Integer x
        // при этом в браузере вводим их разделяя знаком ?
        this.basketService.addProductToBasket(idsOfProducts);
        return new ResponseEntity<>("Получены идентификаторы ID: " + idsOfProducts, HttpStatus.OK);
    }

//    @GetMapping("/set/{id}")
//    // В этом варианте в браузере не нужно писать id=1, можно просто написать 1, но только по одному значению вводить.
//    public String set(@PathVariable("id") int id) {
//        return "Вы ввели " + id;
//    }

    @GetMapping("/get")
    public ResponseEntity<List<Integer>> getProducts() {
        return new ResponseEntity<>(this.basketService.getProductsFromBasket(), HttpStatus.OK);
        //return List.of();
        //return Collections.emptyList();
    }
}
