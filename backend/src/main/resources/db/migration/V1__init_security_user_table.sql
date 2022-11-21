drop table if exists security_user;
create table security_user(
    id bigint auto_increment primary key,
    email varchar(255) not null unique,
    password varchar(255) not null,
    account_non_expired bit,
    account_non_locked bit,
    credentials_non_expired bit,
    enabled bit
);



