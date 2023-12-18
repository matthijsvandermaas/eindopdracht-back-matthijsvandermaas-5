-- Voeg rollen toe
insert into roles(role_name) values ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_BREWER');

-- Voeg gebruikers toe
INSERT INTO users (username, first_name, last_name, company, email, password)
VALUES
    ('tony','tony', 'stark', 'stark tech', 't.stark@starktech.com', '$2a$12$e1pjxBkaMvYDbBLW2v1dD.KT29klsJ4ZI11eTdlGxQ7thbsPQ/OKi'), -- password: password
    ('admin','admin', 'instratie', 'BenB', 'admink@BenB.com', '$2a$12$0vopxKleyixhHvxc.SwyH.czMCOw8thpgEXzLFRkZmt.5fOQiiPOm'); -- password: ironman

-- Koppel rollen aan gebruikers
INSERT INTO users_roles (users_username, roles_role_name) VALUES ('tony', 'ROLE_ADMIN');
INSERT INTO users_roles (users_username, roles_role_name) VALUES ('admin', 'ROLE_BREWER');

-- Voeg producten toe
INSERT INTO products (id, product_name, name_brewer, production_location, tast, type, alcohol, ibu, color, volume)
VALUES
    (1001, 'Bier 1', 'Brouwerij 1', 'Brouwerij 1', 'Zoet', 'Pils', 5.0, 10, 5, 33),
    (1002, 'Bier 2', 'Brouwerij 2', 'Brouwerij 2', 'Zoet', 'Pils', 5.0, 10, 5, 33),
    (1003, 'Bier 3', 'Brouwerij 3', 'Brouwerij 3', 'Zoet', 'Pils', 5.0, 10, 5, 33),
    (1004, 'Bier 4', 'Brouwerij 4', 'Brouwerij 4', 'Zoet', 'Pils', 5.0, 10, 5, 33);
