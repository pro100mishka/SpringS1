DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id                    bigserial,
  username              varchar(50) NOT NULL,
  password              varchar(80) NOT NULL,
  first_name            VARCHAR(50) NOT NULL,
  last_name             VARCHAR(50) NOT NULL,
  email                 VARCHAR(50) NOT NULL,
  phone                 VARCHAR(15) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id                    serial,
  name                  VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles (
  user_id               INT NOT NULL,
  role_id               INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id)
  REFERENCES users (id),
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, first_name, last_name, email,phone)
VALUES
('admin','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Admin','Admin','admin@gmail.com','+79881111111'),
('user','$2a$10$6wIxbW2zfUZuEqEj6f/8o.dfa7DSo9zVOhspAPhVulAEbKoR80jMe','User','User','user@gmail.com','+79529395258');
INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3);

DROP TABLE IF EXISTS product;
CREATE TABLE product (id bigserial, title varchar(255), cost int);
INSERT INTO product (title, cost) VALUES
('Cheese', 320),
('Milk', 90),
('Apples', 120);