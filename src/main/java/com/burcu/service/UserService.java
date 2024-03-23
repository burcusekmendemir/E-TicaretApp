package com.burcu.service;

import com.burcu.dto.request.UserRegisterRequestDto;
import com.burcu.dto.response.UserRegisterResponseDto;
import com.burcu.entity.User;
import com.burcu.exception.ErrorType;
import com.burcu.exception.TryMeAppException;
import com.burcu.mapper.UserMapper;
import com.burcu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    /**
     * Bu methodun amacı, kullanıcı bilgilerini veritabanına kaydetmektedir.
     * Eğer şifreler uyuşmaz ve girilen email de sistemde zate kayıtlı ise
     * hata verir, değil ise kullanıcı kaydedilir.
     */

    public UserRegisterResponseDto register(UserRegisterRequestDto dto) {
        User user= UserMapper.INSTANCE.fromUserRegisterRequestDto(dto);

        if (!user.getPassword().equals(user.getRePassword()) || user.getPassword().isBlank() || userRepository.existsByEmail(user.getEmail())) {
            throw new TryMeAppException(ErrorType.BAD_REEQUEST_ERROR);
        }
        userRepository.save(user);
        return UserMapper.INSTANCE.fromUserToUserRegisterResponseDto(user);
    }

    //Tüm kullanıcıları listeleyen method.
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //Kullanıcıyı id ile bulan method.
    public Optional<User> findById(Long id) {
        Optional<User> optionalUser=userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new TryMeAppException(ErrorType.USER_NOT_FOUND);
        }
        return optionalUser;
    }
}
