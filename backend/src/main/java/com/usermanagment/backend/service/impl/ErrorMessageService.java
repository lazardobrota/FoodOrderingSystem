package com.usermanagment.backend.service.impl;

import com.usermanagment.backend.dto.errormessage.ErrorMessageDto;
import com.usermanagment.backend.dto.errormessage.NewErrorMesageDto;
import com.usermanagment.backend.exception.FoodException;
import com.usermanagment.backend.mapper.ErrorMessageMapper;
import com.usermanagment.backend.model.ErrorMessage;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.repository.IErrorMessageRepo;
import com.usermanagment.backend.repository.IUserRepo;
import com.usermanagment.backend.service.IErrorMessageService;
import com.usermanagment.backend.service.IUserService;
import com.usermanagment.backend.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ErrorMessageService implements IErrorMessageService {

    private final IErrorMessageRepo errorRepo;
    private final ErrorMessageMapper errorMapper;
    private final IUserRepo userRepo;


    @Override
    public Set<ErrorMessageDto> findAllErrorsOfOrderId(Long id) {
        return errorRepo.findErrorsByOrderId(id).stream().map(errorMapper::toErrorMessageDto).collect(Collectors.toSet());
    }

    @Override
    public ErrorMessageDto addError(NewErrorMesageDto newErrorMesageDto) {
        User user =  userRepo.findByEmail(UserUtils.getEmail()).orElseThrow(() -> new FoodException("User not found with given id", HttpStatus.BAD_REQUEST));
        ErrorMessage errorMessage = errorMapper.toErrorMessage(newErrorMesageDto, user);
        return errorMapper.toErrorMessageDto(errorRepo.save(errorMessage));
    }
}
