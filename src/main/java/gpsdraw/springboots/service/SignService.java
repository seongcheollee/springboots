package gpsdraw.springboots.service;


import gpsdraw.springboots.data.dto.SignInResultDto;
import gpsdraw.springboots.data.dto.SignUpResultDto;

// 예제 13.24
public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String role);

    SignInResultDto signIn(String id, String password) throws RuntimeException;

}