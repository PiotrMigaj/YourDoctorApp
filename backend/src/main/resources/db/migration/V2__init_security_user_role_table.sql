drop table if exists security_user_role;
create table security_user_role(
    id bigint auto_increment primary key,
    name varchar(255) not null unique
);



