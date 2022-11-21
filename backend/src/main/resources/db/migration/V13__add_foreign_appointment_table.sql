ALTER TABLE appointment ADD FOREIGN KEY (address_id) REFERENCES address(id);
ALTER TABLE appointment ADD FOREIGN KEY (doctor_id) REFERENCES doctor(id);
ALTER TABLE appointment ADD FOREIGN KEY (user_id) REFERENCES security_user(id);


