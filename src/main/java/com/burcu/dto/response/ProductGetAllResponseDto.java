package com.burcu.dto.response;


import com.burcu.utility.enums.Colour;
import com.burcu.utility.enums.Gender;
import com.burcu.utility.enums.Size;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductGetAllResponseDto {

    private String name;
    private String explanation;
    private Double price;
    private Long brandId;
    private Long modelId;
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Size size;
    @Enumerated(EnumType.STRING)
    private Colour colour;

    private String pattern;
    private String material;
    private String environment;
    private String fabricType;
    private String height;
}
