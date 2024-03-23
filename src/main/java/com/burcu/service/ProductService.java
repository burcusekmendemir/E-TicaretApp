package com.burcu.service;

import com.burcu.dto.request.ColourRequestDto;
import com.burcu.dto.request.SizeAndGenderRequestDto;
import com.burcu.dto.request.SizeRequestDto;
import com.burcu.dto.response.ColourResponseDto;
import com.burcu.dto.response.ProductGetAllResponseDto;
import com.burcu.dto.response.SizeAndGenderFindAllResponseDto;
import com.burcu.dto.response.SizeResponseDto;
import com.burcu.entity.Product;

import com.burcu.mapper.ProductMapper;
import com.burcu.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<ProductGetAllResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductGetAllResponseDto> productList= new ArrayList<>();
        for (Product product : products) {
            productList.add(ProductMapper.INSTANCE.fromProductToProductGetAllResponseDto(product));
        }
        return productList;
    }


    public ResponseEntity<List<SizeAndGenderFindAllResponseDto>> findAllBySizeAndGender(SizeAndGenderRequestDto dto) {
        List<Product> products = productRepository.findAllBySizeAndGender(dto.getSize(), dto.getGender());
        List<SizeAndGenderFindAllResponseDto> productList= new ArrayList<>();
        for (Product product : products) {
            productList.add(ProductMapper.INSTANCE.fromProductToSizeAndGenderFindAllResponseDto(product));
        }
        return ResponseEntity.ok(productList);
    }

    public ResponseEntity<List<SizeResponseDto>> findAllBySize(SizeRequestDto dto) {
        List<Product> products = productRepository.findAllBySize(dto.getSize());
        List<SizeResponseDto> productList= new ArrayList<>();
        for (Product product : products) {
            productList.add(ProductMapper.INSTANCE.fromProductToSizeResponseDto(product));
        }
        return ResponseEntity.ok(productList);
    }

    public ResponseEntity<List<ColourResponseDto>> findAllByColour(ColourRequestDto dto) {
        List<Product> products=productRepository.findAllByColour(dto.getColour());
        List<ColourResponseDto> productlist=new ArrayList<>();
        for (Product product: products) {
            productlist.add(ProductMapper.INSTANCE.fromProductToColourResponseDto(product));
        }
        return ResponseEntity.ok(productlist);
    }


    public Optional<Product> findById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new NullPointerException("Product not found");
        }
        return product;
    }
}
