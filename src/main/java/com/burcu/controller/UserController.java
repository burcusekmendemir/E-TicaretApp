package com.burcu.controller;

import com.burcu.dto.request.UserRegisterRequestDto;
import com.burcu.dto.response.UserRegisterResponseDto;
import com.burcu.entity.User;
import com.burcu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.burcu.constants.RestApiUrls.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(REGISTER)
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto dto){
        return ResponseEntity.ok(userService.register(dto));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(GET_BY_ID)
    public ResponseEntity<Optional<User>> findById(Long id){
        return ResponseEntity.ok(userService.findById(id));
    }
}
