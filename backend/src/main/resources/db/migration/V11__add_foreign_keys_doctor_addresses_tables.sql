ALTER TABLE doctor_address ADD FOREIGN KEY (doctor_id) REFERENCES doctor(id);
ALTER TABLE doctor_address ADD FOREIGN KEY (address_id) REFERENCES address(id);


