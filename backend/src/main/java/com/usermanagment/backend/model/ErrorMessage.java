package com.usermanagment.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "error_message")
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String message;

    public ErrorMessage(Order order, LocalDateTime date, String message) {
        this.order = order;
        this.date = date;
        this.message = message;
    }
}
