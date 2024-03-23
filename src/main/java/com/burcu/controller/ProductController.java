package com.burcu.controller;

import com.burcu.dto.request.ColourRequestDto;
import com.burcu.dto.request.ProductAddRequestDto;
import com.burcu.dto.request.SizeAndGenderRequestDto;
import com.burcu.dto.request.SizeRequestDto;
import com.burcu.dto.response.ColourResponseDto;
import com.burcu.dto.response.ProductGetAllResponseDto;
import com.burcu.dto.response.SizeAndGenderFindAllResponseDto;
import com.burcu.dto.response.SizeResponseDto;
import com.burcu.entity.*;
import com.burcu.mapper.ProductMapper;
import com.burcu.service.*;
import com.burcu.utility.enums.Colour;
import com.burcu.utility.enums.Gender;
import com.burcu.utility.enums.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.burcu.constants.RestApiUrls.*;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelService modelService;
    private final BrandService brandService;
    private final ImageService imageService;

    @PostMapping(ADD)
    public ResponseEntity<Void> add(@RequestBody ProductAddRequestDto dto) {
        Product product= ProductMapper.INSTANCE.fromProductAddRequestDto(dto);
        Category category = categoryService.save(dto.getCategories());
        Model model = modelService.save(dto.getModels());
        Brand brand = brandService.save(dto.getBrands());
        Image image= imageService.upload(product.getId(), (MultipartFile) dto.getImages().get(0));
        //List<Image> imageList = imageService.addSideImages(product.getId(),  dto.getImages().get(1));

        product.setCategoryId(category.getId());
        product.setModelId(model.getId());
        product.setBrandId(brand.getId());
        product.setMainImage(image);
//        product.setSideImages(imageList);

        productService.save(product);

        return ResponseEntity.ok().build();
    }


    @GetMapping(GET_ALL)
    public ResponseEntity<List<ProductGetAllResponseDto>> getAll(){
        return ResponseEntity.ok(productService.findAll());
    }


    /**
     * Beden ve cinsiyete göre ürünleri listeyen method.
     */
    @PostMapping(GET_ALL_BY_SIZE_AND_GENDER)
    public ResponseEntity<List<SizeAndGenderFindAllResponseDto>> findAllBySizeAndGender(@RequestBody SizeAndGenderRequestDto dto){
        return productService.findAllBySizeAndGender(dto);
    }

    /**
     * Bedene göre ürünleri listeleyen method.
     */
    @PostMapping(GET_ALL_BY_SIZE)
    public ResponseEntity<List<SizeResponseDto>> findAllBySize(@RequestBody SizeRequestDto dto){
        return productService.findAllBySize(dto);
    }


    /**
     * Renk seçimine göre ürünleri listeleyen method.
     */
    @PostMapping(GET_ALL_BY_COLOUR)
    public ResponseEntity<List<ColourResponseDto>> findAllByColour(@RequestBody ColourRequestDto dto){
        return productService.findAllByColour(dto);
    }


    /**
     * Listeleme yapılabilecek özelliklerin başlık alt başlık şeklinde listesini veren method.
     */
    @PostMapping(LIST_PRODUCT_FEATURES)
   public Map<String, Object> listProductFeatures(){
        Map<String, Object> mapList=new HashMap<>();

        mapList.put("brands",brandService.findAll());
        mapList.put("categories",categoryService.findAll());
        mapList.put("models",modelService.findAll());
        mapList.put("genders", Gender.values());
        mapList.put("sizes", Size.values());
        mapList.put("colours", Colour.values());

        return mapList;
   }






}
