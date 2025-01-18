package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.errormessage.ErrorMessageDto;
import com.usermanagment.backend.dto.errormessage.NewErrorMessageDto;
import com.usermanagment.backend.model.ErrorMessage;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ErrorMessageMapper {

    private final UserMapper userMapper;

    public ErrorMessageDto toErrorMessageDto(ErrorMessage errorMessage) {
        return new ErrorMessageDto(
                errorMessage.getId(),
                userMapper.userToUserDto(errorMessage.getUser()),
                errorMessage.getDate(),
                OrderStatus.getOrderStatus(errorMessage.getStatus()).getName(),
                errorMessage.getMessage()
        );
    }

    public ErrorMessage toErrorMessage(ErrorMessageDto errorMessageDto, User user) {
        return new ErrorMessage(
                errorMessageDto.getId(),
                user,
                errorMessageDto.getDate(),
                OrderStatus.getInt(errorMessageDto.getStatus()),
                errorMessageDto.getMessage()
        );
    }

    public ErrorMessage toErrorMessage(NewErrorMessageDto newErrorMessageDto, User user) {
        return new ErrorMessage(
                user,
                newErrorMessageDto.getDate(),
                OrderStatus.getInt(newErrorMessageDto.getStatus()),
                newErrorMessageDto.getMessage()
        );
    }
}
