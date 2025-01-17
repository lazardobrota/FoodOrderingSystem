package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.errormessage.ErrorMessageDto;
import com.usermanagment.backend.dto.errormessage.NewErrorMesageDto;
import com.usermanagment.backend.dto.order.OrderDto;
import com.usermanagment.backend.model.ErrorMessage;
import com.usermanagment.backend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ErrorMessageMapper {

    private final OrderMapper orderMapper;

    public ErrorMessageDto toErrorMessageDto(ErrorMessage errorMessage) {
        return new ErrorMessageDto(
                errorMessage.getId(),
                orderMapper.toOrderDto(errorMessage.getOrder()),
                errorMessage.getDate(),
                errorMessage.getMessage()
        );
    }

    public ErrorMessage toErrorMessage(ErrorMessageDto errorMessageDto, User user) {
        return new ErrorMessage(
                errorMessageDto.getId(),
                orderMapper.toOrder(errorMessageDto.getOrder(), user),
                errorMessageDto.getDate(),
                errorMessageDto.getMessage()
        );
    }

    public ErrorMessage toErrorMessage(NewErrorMesageDto newErrorMesageDto, User user) {
        return new ErrorMessage(
                orderMapper.toOrder(newErrorMesageDto.getOrder(), user),
                newErrorMesageDto.getDate(),
                newErrorMesageDto.getMessage()
        );
    }
}
