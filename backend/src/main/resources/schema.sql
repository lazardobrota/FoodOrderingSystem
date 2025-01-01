create table user_management.user
(
    id                   bigint       not null auto_increment,
    name                 varchar(255) not null,
    lastname             varchar(255) not null,
    email                varchar(255) not null unique,
    password             varchar(255) not null,
    permissions_bit_mask int          not null,
    primary key (id)
);

delete from user_management.user;