create table food_ordering_system.user
(
    id                   bigint       not null auto_increment,
    name                 varchar(255) not null,
    lastname             varchar(255) not null,
    email                varchar(255) not null unique,
    password             varchar(255) not null,
    permissions_bit_mask int          not null,
    primary key (id)
);

create table food_ordering_system.ingredient
(
    id   bigint       not null auto_increment primary key,
    name varchar(255) not null unique
);

create table food_ordering_system.dish
(
    id          bigint       not null auto_increment,
    name        varchar(255) not null unique,
    description varchar(255) not null,
    price       int          not null,
    primary key (id)
);

create table food_ordering_system.dish_ingredient
(
    id            bigint not null auto_increment primary key,
    ingredient_id bigint not null,
    dish_id       bigint not null,
    constraint ingredient__fk
        foreign key (ingredient_id) references ingredient (id) on delete cascade,
    constraint dish__fk2
        foreign key (dish_id) references dish (id) on delete cascade
);

create table food_ordering_system.customer_order
(
    id           bigint  not null auto_increment primary key,
    status       int     not null,
    createdBy    bigint  not null,
    scheduleDate datetime not null,
    active       bool not null,
    constraint user___fk
        foreign key (createdBy) references user (id) on delete cascade
);

create table food_ordering_system.order_dish
(
    id       bigint not null auto_increment primary key,
    order_id bigint not null,
    dish_id  bigint not null,
    constraint order__fk
        foreign key (order_id) references customer_order (id) on delete cascade,
    constraint dish__fk
        foreign key (dish_id) references dish (id) on delete cascade
);

create table food_ordering_system.error_message
(
    id          bigint       not null auto_increment primary key,
    user_id     bigint       not null,
    date        datetime     not null,
    status      int          not null,
    message     varchar(255) not null,
    constraint user__fk2
        foreign key (user_id) references user (id) on delete cascade
);

#Empty Table
delete from food_ordering_system.user;
delete from food_ordering_system.dish;
delete from food_ordering_system.customer_order;
delete from food_ordering_system.order_dish;
delete from food_ordering_system.error_message;
delete from food_ordering_system.ingredient;
delete from food_ordering_system.dish_ingredient;

#Delete Table
drop table food_ordering_system.user;
drop table food_ordering_system.dish;
drop table food_ordering_system.customer_order;
drop table food_ordering_system.order_dish;
drop table food_ordering_system.error_message;
drop table food_ordering_system.ingredient;
drop table food_ordering_system.dish_ingredient;