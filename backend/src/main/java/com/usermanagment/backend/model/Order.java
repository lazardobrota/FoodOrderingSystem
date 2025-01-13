package com.usermanagment.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "CustomerOrder")
@Table(name = "customer_order")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "schedule_date", nullable = false)
    private LocalDateTime scheduleDate;

    @ManyToMany
    @JoinTable(
            name = "order_dish",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Dish> dishes;

    public Order(int status, User createdBy, boolean active, LocalDateTime scheduleDate) {
        this.status = status;
        this.createdBy = createdBy;
        this.active = active;
        this.scheduleDate = scheduleDate;
    }

    public Order(Long id, int status, User createdBy, boolean active) {
        this.id = id;
        this.status = status;
        this.createdBy = createdBy;
        this.active = active;
    }


    public static String id() {
        return "id";
    }

    public static String status() {
        return "status";
    }
    public static String createdBy() {
        return "createdBy";
    }
    public static String active() {
        return "active";
    }
    public static String scheduleDate() {
        return "scheduleDate";
    }

}
