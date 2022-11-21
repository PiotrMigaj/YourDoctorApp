drop table if exists doctor_specialization;
create table doctor_specialization(
    id bigint auto_increment primary key,
    doctor_id bigint,
    specialization_id bigint
);



