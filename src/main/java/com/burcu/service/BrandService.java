package com.burcu.service;

import com.burcu.dto.request.ProductAddRequestDto;
import com.burcu.entity.Brand;
import com.burcu.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public Brand save(List<ProductAddRequestDto.ProductAndBrand> brands) {
        Optional<Brand> optionalBrand = brandRepository.findByName(brands.get(0).getName());
        if (optionalBrand.isEmpty()){
            Brand brand = new Brand();
            brand.setName(brands.get(0).getName());
            brandRepository.save(brand);
            return brand;
        }
        return optionalBrand.get();
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }


    public Optional<Brand> findById(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isEmpty()){
            throw new NullPointerException("Marka bulunamadı");
        }
        return optionalBrand;
    }



}
