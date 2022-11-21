drop table if exists doctor;
create table doctor(
    id bigint primary key,
    phone_number varchar(255),
    created_at datetime,
    user_id bigint
);



