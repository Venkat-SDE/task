package com.assignment.ennea.solutions.service;

import com.assignment.ennea.solutions.customexceptions.InValidDateException;
import com.assignment.ennea.solutions.entity.Products;
import com.assignment.ennea.solutions.repository.ProductRepository;
import com.assignment.ennea.solutions.util.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Class
 */
@Service
public class ProductService {

    /**
     * productRepository
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * converterService
     */
    @Autowired
    private ConverterService converterService;


    /**
     * @param file it takes csv file as param and stores the data
     * @return and then return the stored data
     * @throws Exception
     */
    public List<Products> saveProducts(MultipartFile file) throws Exception {

        List<Products> productsList = injectCSVFile(file);
        return productRepository.saveAll(productsList);
    }


    /**
     * @param supplierName which takes supplierName,
     * @param name         name
     * @param pageable     and pageable
     * @return then returns the list of data based on the given parameters with pagination
     */
    public Page<Products> fetchProductBySupplierName(String supplierName, String name, Pageable pageable) {

        if (name != null) {
            Page<Products> productsList = productRepository.findByName(name, pageable);
            List<Products> products = productsList.getContent();
            products = productsList.stream().map(productsObj -> {
                converterService.convertToDto(productsObj);
                return productsObj;
            }).collect(Collectors.toList());
            return new PageImpl<>(products, pageable, productsList.getTotalElements());
        }
        Page<Products> productsList = productRepository.findBySupplier(supplierName, pageable);
        List<Products> productsWithStock = new ArrayList<>();
        List<Products> products = productsList.getContent();
        products = productsList.stream().map(productsObj -> {
            converterService.convertToDto(productsObj);
            return productsObj;
        }).collect(Collectors.toList());
        for (Products product : products) {

            if (product.getStock() > 0)
                productsWithStock.add(product);
        }
        return new PageImpl<>(productsWithStock, pageable, productsList.getTotalElements());
    }


    /**
     * @param expirationDate which takes expirationDate,
     * @param pageable       and pageable
     * @return then returns the list of data based on the given parameters with pagination
     */
    public Page<Products> fetchNotExpiredProducts(String expirationDate, Pageable pageable) {

        Date date = extractingDate(expirationDate);
        Page<Products> productsList = productRepository.findAll(pageable);
        List<Products> notExpiredProducts = new ArrayList<>();
        List<Products> products = productsList.getContent();
        products = productsList.stream().map(productsObj -> {
            converterService.convertToDto(productsObj);
            return productsObj;
        }).collect(Collectors.toList());
        for (Products product : products) {
            if (product.getExpirationDate().after(date) || product.getExpirationDate().equals(date))
                notExpiredProducts.add(product);
        }
        return new PageImpl<>(notExpiredProducts, pageable, productsList.getTotalElements());
    }


    /**
     * @param file it takes csv file as param and extracts the data in it
     * @return and then returns the list of data
     * @throws Exception
     */
    public List<Products> injectCSVFile(MultipartFile file) throws Exception {

        List<Products> productsList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",", -1);
            productsList.add(convertingData(data));
        }
        return productsList;
    }


    /**
     * @param data it takes the data as array format
     * @return and then returns the data as object
     */
    public Products convertingData(String[] data) {
        String code = data[0];
        String name = data[1];
        String batch = data[2];
        int stock = Integer.parseInt(data[3]);
        int deal = Integer.parseInt(data[4]);
        int free = Integer.parseInt(data[5]);
        float mrp = Float.parseFloat(data[6]);
        float rate = Float.parseFloat(data[7]);
        Date expirationDate = extractingDate(data[8]);
        String company = data[9];
        String supplier = data[10];

        return Products.builder()
                .code(code)
                .name(name)
                .batch(batch)
                .stock(stock)
                .deal(deal)
                .free(free)
                .mrp(mrp)
                .rate(rate)
                .expirationDate(expirationDate)
                .company(company)
                .supplier(supplier)
                .build();
    }

    /**
     * @param date it takes the date in string format
     * @return and then returns the date as normal date
     */
    public Date extractingDate(String date) {
        final List<String> dateFormats = Arrays.asList("/  /", "dd/MM/yyyy");
        for (String format : dateFormats) {
            DateFormat dateFormat = new SimpleDateFormat(format);
            try {
                return dateFormat.parse(date);
            } catch (ParseException e) {
            }
        }
        throw new InValidDateException("Date should be in DD/MM/YYYY format !!!");
    }
}
