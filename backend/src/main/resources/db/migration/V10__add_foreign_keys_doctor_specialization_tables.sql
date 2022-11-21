ALTER TABLE doctor_specialization ADD FOREIGN KEY (doctor_id) REFERENCES doctor(id);
ALTER TABLE doctor_specialization ADD FOREIGN KEY (specialization_id) REFERENCES specialization(id);


