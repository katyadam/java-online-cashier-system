package com.example.onlinecashiersystem.service.api;

import com.example.onlinecashiersystem.api.ProductPlaneDto;
import com.example.onlinecashiersystem.data.model.Product;
import com.example.onlinecashiersystem.data.model.ProductPlane;
import com.example.onlinecashiersystem.data.repository.ProductPlaneRepository;
import com.example.onlinecashiersystem.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductPlaneService {

    private final ProductPlaneRepository productPlaneRepository;
    private final UserService userService;

    @Autowired
    public ProductPlaneService(
            ProductPlaneRepository productPlaneRepository,
            UserService userService
    ) {
        this.productPlaneRepository = productPlaneRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public ProductPlane findById(Long id) {
        return productPlaneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductPlane with userId " + id + " was not found!"));
    }

    @Transactional(readOnly = true)
    public Set<Product> findProducts(Long id) {
        return findById(id).getProductSet();
    }

    @Transactional(readOnly = true)
    public Page<ProductPlane> findAll(Pageable pageable) {
        return productPlaneRepository.findAll(pageable);
    }

    @Transactional
    public ProductPlane createProductPlane(ProductPlaneDto productPlaneDto) {
        ProductPlane newProductPlane = new ProductPlane();
        newProductPlane.setName(productPlaneDto.name());
        newProductPlane.setProductSet(new HashSet<>());
        newProductPlane.setUser(userService.findById(productPlaneDto.userId()));

        productPlaneRepository.save(newProductPlane);
        return newProductPlane;
    }

    @Transactional
    public ProductPlane updateProductPlane(Long id, ProductPlaneDto productPlaneDto) {
        ProductPlane toUpdate = findById(id);
        toUpdate.setName(productPlaneDto.name());
        toUpdate.setUser(userService.findById(productPlaneDto.userId()));

        productPlaneRepository.save(toUpdate);
        return toUpdate;
    }

    @Transactional
    public ProductPlane deleteProductPlane(Long id) {
        ProductPlane toDelete = findById(id);
        productPlaneRepository.delete(toDelete);

        return toDelete;
    }

    @Transactional
    public void deleteAllProductPlanes() {
        productPlaneRepository.deleteAll();
    }
}
