package com.burcu.mapper;

import com.burcu.dto.request.UserRegisterRequestDto;
import com.burcu.dto.response.UserRegisterResponseDto;
import com.burcu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel ="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    User fromUserRegisterRequestDto(final UserRegisterRequestDto dto);
    UserRegisterResponseDto fromUserToUserRegisterResponseDto(final User user);
}
