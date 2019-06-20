package ru.morou.korekor.service;

import ru.morou.korekor.controller.repr.ProductRepository;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductRepository> findAll();

    ProductRepository findById(Long id);

    void deleteById(Long id);

    void save(ProductRepository product) throws IOException;
}
