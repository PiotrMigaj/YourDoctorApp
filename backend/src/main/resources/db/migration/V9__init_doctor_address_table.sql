drop table if exists doctor_address;
create table doctor_address(
    id bigint auto_increment primary key,
    doctor_id bigint,
    address_id bigint
);



