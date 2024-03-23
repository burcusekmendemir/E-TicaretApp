package com.burcu.dto.request;

import com.burcu.utility.enums.Colour;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ColourRequestDto {

    private Colour colour;
}
