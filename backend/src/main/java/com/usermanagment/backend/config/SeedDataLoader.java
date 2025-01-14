package com.usermanagment.backend.config;

import com.usermanagment.backend.model.*;
import com.usermanagment.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class SeedDataLoader implements CommandLineRunner {

    private final IUserRepo userRepo;
    private final IIngredientRepo ingredientRepo;
    private final IDishRepo dishRepo;
    private final IDishIngredientRepo dishIngredientRepo;
    private final IOrderRepo orderRepo;
    private final IOrderDishRepo orderDishRepo;
    private final PasswordEncoder passwordEncoder;

    Ingredient lettuce = new Ingredient("Lettuce");
    Ingredient tomato = new Ingredient("Tomato");
    Ingredient cheddar = new Ingredient("Cheddar Cheese");
    Ingredient swiss = new Ingredient("Swiss Cheese");
    Ingredient bacon = new Ingredient("Bacon");
    Ingredient jalapenos = new Ingredient("Jalapeños");
    Ingredient mushrooms = new Ingredient("Grilled Mushrooms");
    Ingredient bbqSauce = new Ingredient("BBQ Sauce");
    Ingredient pickles = new Ingredient("Pickles");
    Ingredient onions = new Ingredient("Onions");
    Ingredient avocado = new Ingredient("Avocado");
    Ingredient chipotleMayo = new Ingredient("Chipotle Mayo");

    Dish classicBurger = new Dish("Classic Beef Burger", "A timeless favorite with a juicy patty and traditional flavors", 8);
    Dish deluxeCheeseburger = new Dish("Cheeseburger Deluxe", "An upgraded classic featuring rich and satisfying elements", 9);
    Dish baconBBQBurger = new Dish("Bacon BBQ Burger", "Smoky and bold with a perfect balance of flavors", 10);
    Dish spicyJalapenoBurger = new Dish("Spicy Jalapeño Burger", "A fiery twist for those who love a little heat", 9);

    User admin = new User("admin", "admin", "admin@gmail.com", "admin", 511);
    User lazar = new User("Lazar", "Dobrota", "ldobrota@raf.rs", "1234", 1);
    User lazar2 = new User("Lazar", "Dobrota", "2ldobrota@raf.rs", "1234", 0);
    User john = new User("John", "Black", "jb@gmail.com", "admin", 135);
    User ime = new User("Ime", "Prezime", "ip@gmail.com", "admin", 143);

    Order order1 = new Order(1, admin, true, LocalDateTime.of(2025, 1, 15, 12, 0, 0));
    Order order2 = new Order(1, lazar, true, LocalDateTime.of(2025, 1, 16, 14, 30, 0));
    Order order3 = new Order(1, john, false, LocalDateTime.of(2025, 1, 17, 18, 0, 0));
    Order order4 = new Order(1, admin, false, LocalDateTime.of(2025, 1, 18, 20, 45, 0));


    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedIngredients();
        seedDishes();
        seedDishesWithIngredients();
        seedOrders();
        seedOrdersWithDishes();
    }

    //region Seed Dish with Ingredients
    private void seedDishesWithIngredients() {
        if (dishIngredientRepo.count() != 0)
            return;

        dishIngredientRepo.saveAll(List.of(
                new DishIngredient(lettuce, classicBurger),
                new DishIngredient(tomato, classicBurger),
                new DishIngredient(cheddar, classicBurger),
                new DishIngredient(onions, classicBurger),
                new DishIngredient(lettuce, deluxeCheeseburger),
                new DishIngredient(tomato, deluxeCheeseburger),
                new DishIngredient(cheddar, deluxeCheeseburger),
                new DishIngredient(pickles, deluxeCheeseburger),
                new DishIngredient(bacon, baconBBQBurger),
                new DishIngredient(bbqSauce, baconBBQBurger),
                new DishIngredient(cheddar, baconBBQBurger),
                new DishIngredient(pickles, baconBBQBurger),
                new DishIngredient(jalapenos, spicyJalapenoBurger),
                new DishIngredient(cheddar, spicyJalapenoBurger),
                new DishIngredient(chipotleMayo, spicyJalapenoBurger),
                new DishIngredient(onions, spicyJalapenoBurger)
        ));
    }
    //endregion

    //region Seed Order with Dishes
    private void seedOrdersWithDishes() {
        if (orderDishRepo.count() != 0)
            return;

        orderDishRepo.saveAll(List.of(
                new OrderDish(order1, classicBurger),
                new OrderDish(order1, deluxeCheeseburger),
                new OrderDish(order2, baconBBQBurger),
                new OrderDish(order2, spicyJalapenoBurger),
                new OrderDish(order3, classicBurger),
                new OrderDish(order3, baconBBQBurger),
                new OrderDish(order4, spicyJalapenoBurger)
        ));
    }
    //endregion

    //region Seed Ingredients
    private void seedIngredients() {
        if (ingredientRepo.count() != 0)
            return;

        ingredientRepo.saveAll(List.of(
                lettuce,
                tomato,
                cheddar,
                swiss,
                bacon,
                jalapenos,
                mushrooms,
                bbqSauce,
                pickles,
                onions,
                avocado,
                chipotleMayo
        ));
    }
    //endregion

    //region Seed Dishes
    private void seedDishes() {
        if (dishRepo.count() != 0)
            return;

        dishRepo.saveAll(List.of(
                classicBurger,
                deluxeCheeseburger,
                baconBBQBurger,
                spicyJalapenoBurger
        ));
    }
    //endregion

    //region Seed Orders
    private void seedOrders() {
        if (orderRepo.count() != 0)
            return;

        orderRepo.saveAll(List.of(
                order1,
                order2,
                order3,
                order4
        ));
    }
    //endregion

    //region Seed Users
    private void seedUsers() {
        if (userRepo.count() != 0)
            return;

        userRepo.saveAll(Stream.of(
                        admin,
                        lazar,
                        lazar2,
                        john,
                        ime
                )
                .map(user -> user.setPassword(passwordEncoder.encode(user.getPassword())))
                .toList()
        );
    }
    //endregion
}
