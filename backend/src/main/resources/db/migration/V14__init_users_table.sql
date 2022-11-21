drop table if exists users;
create table users(
    id bigint primary key not null unique,
    first_name varchar(255),
    last_name varchar(255),
    security_user_id bigint
);



