package ru.morou.korekor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import ru.morou.korekor.controller.repr.ProductRepository;
import ru.morou.korekor.persist.model.Picture;
import ru.morou.korekor.persist.model.PictureData;
import ru.morou.korekor.persist.model.Product;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ru.morou.korekor.persist.repo.ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ru.morou.korekor.persist.repo.ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductRepository> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepository::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductRepository findById(Long id) {
        return new ProductRepository (productRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(ProductRepository productRepository) throws IOException {
        Product product = (productRepository.getId() != null) ? this.productRepository.findById(productRepository.getId()).get()
                : new Product();
        product.setName(productRepository.getName());
        product.setCategories(productRepository.getCategories());
        product.setBrand(productRepository.getBrand());
        product.setPrice(productRepository.getPrice());
        if (productRepository.getNewPictures() != null) {
            for (MultipartFile newPicture : productRepository.getNewPictures()) {
                logger.info("Product {} file {} size {}", product.getId(),
                        newPicture.getOriginalFilename(), newPicture.getSize());

                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(newPicture.getOriginalFilename(),
                        newPicture.getContentType(), new PictureData(newPicture.getBytes())));
            }
        }
        this.productRepository.save(product);
    }
}
