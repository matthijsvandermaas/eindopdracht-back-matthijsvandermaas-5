INSERT INTO roles(role_name) VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_BREWER');


INSERT INTO users (username, password) VALUES ('tony stark', '$2a$12$Ja8oLzkb7PhVfyzo9aip/O/rWoyQWPuSxEhZYt9a2llEXVP6Z1p52'); // ironman
INSERT INTO users_roles (users_username, roles_role_name) VALUES ('ironman', 'ROLE_BREWER');

INSERT INTO users (username, password) VALUES ('admin', '$2a$12$CK1EfBYfL33NjdjOT/OHvOuAKFnCPR/NxEnAzLoP65D74.IuDjtX6'); // password
INSERT INTO users_roles (users_username, roles_role_name) VALUES ('admin', 'ROLE_AdMIN');

INSERT INTO users (username, password) VALUES ('peper pots', '$2a$12$CK1EfBYfL33NjdjOT/OHvOuAKFnCPR/NxEnAzLoP65D74.IuDjtX6'); // password
INSERT INTO users_roles (users_username, roles_role_name) VALUES ('peper', 'ROLE_USER');

INSERT INTO products (id, productname, namebrewer, productionlocation, tast, type, alcohol, ibu, color, volume )
VALUES (50, '1000', 'vedett extra blond', 'vedett', 'soepel', lager, 5.2, '1', 'goud', '330');
INSERT INTO products (id, productname, namebrewer, productionlocation, tast, type, alcohol, ibu, color, volume )
VALUES (50, '1001', 'vedett extra blond', 'vedett', 'soepel', lager, 5.2, '1', 'goud', '330');
