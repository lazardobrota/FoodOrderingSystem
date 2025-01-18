package com.usermanagment.backend.service.impl;

import com.usermanagment.backend.dto.errormessage.ErrorMessageDto;
import com.usermanagment.backend.dto.errormessage.NewErrorMessageDto;
import com.usermanagment.backend.exception.FoodException;
import com.usermanagment.backend.mapper.ErrorMessageMapper;
import com.usermanagment.backend.model.ErrorMessage;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.params.SearchParams;
import com.usermanagment.backend.repository.IErrorMessageRepo;
import com.usermanagment.backend.repository.IUserRepo;
import com.usermanagment.backend.service.IErrorMessageService;
import com.usermanagment.backend.specification.ErrorSpecification;
import com.usermanagment.backend.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ErrorMessageService implements IErrorMessageService {

    private final IErrorMessageRepo errorRepo;
    private final ErrorMessageMapper errorMapper;
    private final IUserRepo userRepo;


    @Transactional
    @Override
    public Page<ErrorMessageDto> findAllErrorsOfOrderId(Pageable pageable, SearchParams searchParams) {
        ErrorSpecification specification = new ErrorSpecification(searchParams);
        return errorRepo.findAll(specification.filter(), pageable)
                .map(errorMapper::toErrorMessageDto);
    }

    @Transactional
    @Override
    public ErrorMessageDto addError(NewErrorMessageDto newErrorMessageDto) {
        User user =  userRepo.findByEmail(UserUtils.getEmail()).orElseThrow(() -> new FoodException("User not found with given id", HttpStatus.BAD_REQUEST));
        ErrorMessage errorMessage = errorMapper.toErrorMessage(newErrorMessageDto, user);
        return errorMapper.toErrorMessageDto(errorRepo.save(errorMessage));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public ErrorMessageDto saveError(ErrorMessage errorMessage) {
        return errorMapper.toErrorMessageDto(errorRepo.save(errorMessage));
    }
}
