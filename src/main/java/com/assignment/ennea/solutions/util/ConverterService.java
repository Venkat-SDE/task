package com.assignment.ennea.solutions.util;

import com.assignment.ennea.solutions.entity.Products;
import com.assignment.ennea.solutions.model.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ConverterService
 */
@Component
public class ConverterService {

    /**
     * modelMapper
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param products then converts entityToDto
     * @return and returns the productDto class
     */
    public ProductDto convertToDto(Products products) {
        return modelMapper.map(products, ProductDto.class);
    }


    /**
     * @param productDto then converts dtoToEntity
     * @return then returns the product class
     */
    public Products convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Products.class);
    }

}
