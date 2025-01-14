package com.usermanagment.backend.controller;

import com.usermanagment.backend.dto.order.CreateOrderDto;
import com.usermanagment.backend.dto.order.OrderDto;
import com.usermanagment.backend.params.SearchParams;
import com.usermanagment.backend.service.IOrderService;
import com.usermanagment.backend.utils.ExceptionUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderDto>> getAllOrders(Pageable pageable,
                                                       @RequestParam(required = false, name = "user_email") String userEmail,
                                                       @RequestParam(required = false, name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                                       @RequestParam(required = false, name = "end_date"  ) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate,
                                                       @RequestParam(required = false) Integer status) {
        SearchParams searchParams = new SearchParams(userEmail, startDate, endDate, status);
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(orderService.getAllOrders(pageable, searchParams)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable("id") Long id) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(orderService.findOrderById(id)));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(orderService.createOrder(createOrderDto), HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable("id") Long id) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(orderService.deleteOrder(id)));
    }
}
