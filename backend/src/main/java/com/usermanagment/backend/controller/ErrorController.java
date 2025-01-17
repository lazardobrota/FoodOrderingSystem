package com.usermanagment.backend.controller;

import com.usermanagment.backend.dto.errormessage.ErrorMessageDto;
import com.usermanagment.backend.dto.errormessage.NewErrorMesageDto;
import com.usermanagment.backend.service.IErrorMessageService;
import com.usermanagment.backend.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/error")
public class ErrorController {

    private final IErrorMessageService errorMessageService;

    @GetMapping("/order/{id}")
    public ResponseEntity<Set<ErrorMessageDto>> order(@PathVariable("id") Long id) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(errorMessageService.findAllErrorsOfOrderId(id)));
    }

    @PostMapping
    public ResponseEntity<ErrorMessageDto> addError(@RequestBody NewErrorMesageDto newErrorMesageDto) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(errorMessageService.addError(newErrorMesageDto)));
    }
}
