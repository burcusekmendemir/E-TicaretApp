package com.burcu.service;

import com.burcu.dto.request.ProductAddRequestDto;
import com.burcu.entity.Model;
import com.burcu.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    public Model save(List<ProductAddRequestDto.ProductAndModel> models) {
        Optional<Model> optionalModel = modelRepository.findByName(models.get(0).getName());
        if (optionalModel.isEmpty()){
            Model model = new Model();
            model.setName(models.get(0).getName());
            modelRepository.save(model);
            return model;
        }
        return optionalModel.get();
    }

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public Optional<Model> findById(Long id) {
        Optional<Model> optionalModel = modelRepository.findById(id);
        if (optionalModel.isEmpty()){
            throw new NullPointerException("Model not found");
        }
        return optionalModel;
    }



}
