-- Voeg rollen toe
INSERT INTO roles(role_name) values ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_BREWER');

-- Voeg gebruikers toe
-- Voeg gebruikers toe
INSERT INTO users (username, first_name, last_name, company, email, password)
VALUES
    ('tony', 'tony', 'stark', 'stark tech', 't.stark@starktech.com', '$2a$12$e1pjxBkaMvYDbBLW2v1dD.KT29klsJ4ZI11eTdlGxQ7thbsPQ/OKi'), -- password: ironman
    ('admin', 'admin', 'istratie', 'BenB', 'admink@BenB.com', '$2a$12$0vopxKleyixhHvxc.SwyH.czMCOw8thpgEXzLFRkZmt.5fOQiiPOm'); -- password: password



-- Koppel rollen aan gebruikers
INSERT INTO users_role (users_username, roles_role_name)
SELECT 'tony', 'ROLE_BREWER'
    WHERE NOT EXISTS (SELECT 1 FROM users_roles WHERE users_username = 'tony' AND roles_role_name = 'ROLE_BREWER');

INSERT INTO users_role (users_username, roles_role_name)
SELECT 'admin', 'ROLE_ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM users_roles WHERE users_username = 'admin' AND roles_role_name = 'ROLE_ADMIN');


-- Voeg producten toe
INSERT INTO products (id, product_name, name_brewer, production_location, tast, type, alcohol, ibu, color, volume)
VALUES
    (1001, 'Bier 1', 'Brouwerij 1', 'Brouwerij 1', 'Zoet', 'Pils', 5.0, 10, 5, 33),
    (1002, 'Bier 2', 'Brouwerij 2', 'Brouwerij 2', 'Zoet', 'Pils', 5.0, 10, 5, 33),
    (1003, 'Bier 3', 'Brouwerij 3', 'Brouwerij 3', 'Zoet', 'Pils', 5.0, 10, 5, 33),
    (1004, 'Bier 4', 'Brouwerij 4', 'Brouwerij 4', 'Zoet', 'Pils', 5.0, 10, 5, 33);
