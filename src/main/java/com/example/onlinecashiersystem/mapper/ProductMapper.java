package com.example.onlinecashiersystem.mapper;

import com.example.onlinecashiersystem.api.ProductDto;
import com.example.onlinecashiersystem.data.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto mapToDto(Product product);
}
