package com.assignment.ennea.solutions.controller;

import com.assignment.ennea.solutions.entity.Products;
import com.assignment.ennea.solutions.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.QueryParam;
import java.text.ParseException;

/**
 * Controller Class
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * productService
     */
    @Autowired
    private ProductService productService;

    /**
     * @param file which takes csv file as parameter
     * @return then returns the responseEntity with http status code and body
     * @throws Exception
     */
    @PostMapping(value = "/")
    public ResponseEntity<String> saveProducts(@RequestParam(value = "file") MultipartFile file) throws Exception {

        productService.saveProducts(file);
        return ResponseEntity.status(HttpStatus.OK).body("Data Inserted successfully!!!");
    }

    /**
     * @param supplierName which takes supplierName,
     * @param pageNo       PageNo
     * @param pageSize     pageSize
     * @param name         and name
     * @return then returns the list of data based on the given parameters with pagination
     */
    @GetMapping(value = "/{supplierName}")
    public Page<Products> fetchProductBySupplierName(@PathVariable String supplierName, @RequestParam("page") Integer pageNo,
                                                     @RequestParam("size") Integer pageSize, @QueryParam("name") String name) {

        return productService.fetchProductBySupplierName(supplierName, name, PageRequest.of(pageNo, pageSize));
    }

    /**
     * @param expirationDate which takes expirationDate,
     * @param pageNo         PageNo
     * @param pageSize       pageSize
     * @return then returns the list of data based on the given parameters with pagination
     * @throws ParseException
     */
    @GetMapping(value = "/")
    public Page<Products> fetchNotExpiredProducts(@RequestParam("page") Integer pageNo,
                                                  @RequestParam("size") Integer pageSize,
                                                  @RequestParam("date") String expirationDate) throws ParseException {

        return productService.fetchNotExpiredProducts(expirationDate, PageRequest.of(pageNo, pageSize));

    }

}
