drop table if exists appointment;
create table appointment(
    id bigint auto_increment primary key,
    created_at datetime,
    updated_at datetime,
    registration_time datetime,
    date_of_appointment date,
    time_of_appointment time,
    taken bit,
    address_id bigint,
    doctor_id bigint,
    user_id bigint
);



