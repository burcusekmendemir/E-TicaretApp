package com.burcu.mapper;

import com.burcu.dto.request.ProductAddRequestDto;
import com.burcu.dto.response.ColourResponseDto;
import com.burcu.dto.response.ProductGetAllResponseDto;
import com.burcu.dto.response.SizeAndGenderFindAllResponseDto;
import com.burcu.dto.response.SizeResponseDto;
import com.burcu.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);


    Product fromProductAddRequestDto(ProductAddRequestDto dto);

    ProductGetAllResponseDto fromProductToProductGetAllResponseDto(Product product);

    SizeAndGenderFindAllResponseDto fromProductToSizeAndGenderFindAllResponseDto(Product product);

    SizeResponseDto fromProductToSizeResponseDto(Product product);

    ColourResponseDto fromProductToColourResponseDto(Product product);
}
