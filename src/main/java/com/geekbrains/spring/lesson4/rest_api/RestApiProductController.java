package com.geekbrains.spring.lesson4.rest_api;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.errors_handler.exceptions.InvalidStateOfObjectException;
import com.geekbrains.spring.lesson4.errors_handler.exceptions.NotFoundException;
import com.geekbrains.spring.lesson4.filter.ProductFilterService;
import com.geekbrains.spring.lesson4.services.ProductService;
import com.geekbrains.spring.lesson4.services.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/product/")
public class RestApiProductController {

    private ProductService service;
    private ProductSpecificationService specificationService;

    @Autowired
    public void setSpecificationService(ProductSpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public List<Product> getAll(@RequestParam (name = "range", required = false) Double[] range,
                                @RequestParam (name = "word", required = false) String word){
        return service.findAllByFiltering(specificationService.getByFilter(new ProductFilterService()
                .setRange(range)
                .setWord(word)));
    }

    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable Long id){
        return service.findById(id).orElseThrow(()->new NotFoundException("Product by id: "+id+" not found."));
    }

    @PostMapping(value = "/")
    public Product add(@RequestBody Product product){
       if (product.getId()!=null)
           throw new InvalidStateOfObjectException("The id field of the incoming object matters.");
       return service.update(product);
    }

    @PutMapping(value = "/{id}")
    public Product edit(@RequestBody Product product, @PathVariable Long id) {
        service.findById(id).orElseThrow(()->new NotFoundException("Product by id: "+id+" not found."));
        return service.update(product.setId(id));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
