package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.errormessage.ErrorMessageDto;
import com.usermanagment.backend.dto.errormessage.NewErrorMessageDto;
import com.usermanagment.backend.model.ErrorMessage;
import com.usermanagment.backend.params.SearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IErrorMessageService {

    Page<ErrorMessageDto> findAllErrorsOfOrderId(Pageable pageable, SearchParams searchParams);

    ErrorMessageDto addError(NewErrorMessageDto newErrorMessageDto);

    ErrorMessageDto saveError(ErrorMessage errorMessage);
}
