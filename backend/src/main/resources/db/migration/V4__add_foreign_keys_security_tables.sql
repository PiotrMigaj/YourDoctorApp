ALTER TABLE security_user_security_user_role ADD FOREIGN KEY (security_user_id) REFERENCES security_user(id);
ALTER TABLE security_user_security_user_role ADD FOREIGN KEY (security_user_role_id) REFERENCES security_user_role(id);


