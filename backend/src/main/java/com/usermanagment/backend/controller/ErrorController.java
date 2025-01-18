package com.usermanagment.backend.controller;

import com.usermanagment.backend.dto.errormessage.ErrorMessageDto;
import com.usermanagment.backend.dto.errormessage.NewErrorMessageDto;
import com.usermanagment.backend.params.SearchParams;
import com.usermanagment.backend.permission.UserPermission;
import com.usermanagment.backend.service.IErrorMessageService;
import com.usermanagment.backend.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/error")
public class ErrorController {

    private final IErrorMessageService errorMessageService;

    @GetMapping
    public ResponseEntity<Page<ErrorMessageDto>> order(Pageable pageable) {
        String userEmail = isAdmin() ? null : SecurityContextHolder.getContext().getAuthentication().getName();
        SearchParams searchParams = new SearchParams(userEmail, null, null, null);
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(errorMessageService.findAllErrorsOfOrderId(pageable, searchParams)));
    }

    @PostMapping
    public ResponseEntity<ErrorMessageDto> addError(@RequestBody NewErrorMessageDto newErrorMessageDto) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(errorMessageService.addError(newErrorMessageDto)));
    }

    private boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(UserPermission.CanDeleteUsers.getName()));
    }
}
