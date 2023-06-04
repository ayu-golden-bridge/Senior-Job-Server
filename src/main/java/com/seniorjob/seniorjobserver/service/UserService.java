package com.seniorjob.seniorjobserver.service;

import com.seniorjob.seniorjobserver.domain.entity.UserEntity;
import com.seniorjob.seniorjobserver.dto.UserDto;
import com.seniorjob.seniorjobserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserDto userDto){
        UserEntity userEntity = userDto.toEntity();
        return userRepository.save(userEntity);
    }
}
