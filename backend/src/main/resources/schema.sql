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

create table dish
(
    id   bigint       not null auto_increment,
    name varchar(255) not null unique,
    primary key (id)
);

create table food_ordering_system.`order`
(
    id        bigint  not null auto_increment primary key,
    status    int     not null,
    createdBy bigint  not null,
    active    bool not null,
    constraint user___fk
        foreign key (createdBy) references user (id) on delete cascade
);

create table food_ordering_system.order_dish
(
    id       bigint not null auto_increment primary key,
    order_id bigint not null,
    dish_id  bigint not null,
    constraint order__fk
        foreign key (order_id) references `order` (id) on delete cascade,
    constraint dish__fk
        foreign key (dish_id) references dish (id) on delete cascade
);

#Empty Table
delete from food_ordering_system.user;
delete from food_ordering_system.dish;
delete from food_ordering_system.order;
delete from food_ordering_system.order_dish;

#Delete Table
drop table food_ordering_system.user;
drop table food_ordering_system.dish;
drop table food_ordering_system.order;
drop table food_ordering_system.order_dish;