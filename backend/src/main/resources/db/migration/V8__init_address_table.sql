drop table if exists address;
create table address(
    id bigint auto_increment primary key,
    city varchar(255),
    zip_code varchar(255),
    street varchar(255),
    number varchar(255)
);



