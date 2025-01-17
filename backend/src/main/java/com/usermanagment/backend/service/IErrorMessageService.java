package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.errormessage.ErrorMessageDto;
import com.usermanagment.backend.dto.errormessage.NewErrorMesageDto;

import java.util.Set;

public interface IErrorMessageService {

    Set<ErrorMessageDto> findAllErrorsOfOrderId(Long id);

    ErrorMessageDto addError(NewErrorMesageDto newErrorMesageDto);
}
