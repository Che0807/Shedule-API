package com.example.scheduleapi.controller;


import com.example.scheduleapi.dto.SignUpRequestDto;
import com.example.scheduleapi.dto.SignUpResponseDto;
import com.example.scheduleapi.dto.UpdatePasswordRequestDto;
import com.example.scheduleapi.dto.UserResponseDto;
import com.example.scheduleapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 -> POST /users
    @PostMapping
    public ResponseEntity<SignUpResponseDto> postUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto responseDto = userService.signUp(
                signUpRequestDto.getUsername(),
                signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 사용자 조회 -> GET /users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findByID(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 비밀번호 수정 -> PATCH /users/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchPassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {
        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 탈퇴 -> DELETE /users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
