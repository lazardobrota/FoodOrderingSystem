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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private String message;

    public ErrorMessage(User user, LocalDateTime date, int status, String message) {
        this.user = user;
        this.date = date;
        this.status = status;
        this.message = message;
    }
}
