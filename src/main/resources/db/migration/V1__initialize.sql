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
('user','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','User','User','user@gmail.com','+79529395258');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id bigserial,
    title varchar(255),
    cost decimal ,
    PRIMARY KEY (id));

INSERT INTO product (title, cost) VALUES
('Cheese', 320),
('Milk', 90),
('Apples', 120);

DROP TABLE IF EXISTS carts;
CREATE table carts(
                      id bigserial,
                      user_id int REFERENCES users (id),
                      PRIMARY key (id));
INSERT INTO carts (user_id) VALUES
(1);

DROP TABLE IF EXISTS cart_item;
CREATE TABLE cart_item (
                           id bigserial,
                           quantity INT NOT NULL,
                           cart_id int REFERENCES carts(id) ON DELETE CASCADE,
                           product_id int REFERENCES product (id) ON DELETE CASCADE,
                           PRIMARY KEY (id));

DROP TABLE IF EXISTS cookie_carts;
CREATE table cookie_carts(
                      id bigserial,
                      session_id varchar(255),
                      PRIMARY key (id));

DROP TABLE IF EXISTS cookie_cart_item;
CREATE TABLE cookie_cart_item (
                           id bigserial,
                           quantity INT NOT NULL,
                           cart_id int REFERENCES cookie_carts(id) ON DELETE CASCADE,
                           product_id int REFERENCES product (id) ON DELETE CASCADE,
                           PRIMARY KEY (id));

INSERT INTO cart_item (cart_id, product_id, quantity) VALUES
(1,1,2);

DROP TABLE IF EXISTS address;
CREATE TABLE address(
    id bigserial,
    address_street varchar(255) NOT NULL,
    address_home varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    zip_code varchar(255) NOT NULL,
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS temp_user;
CREATE TABLE temp_user(
    id bigserial,
    email varchar(255),
    address_id int REFERENCES address(id),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS order_temp;
CREATE TABLE order_temp(
    id bigserial,
    user_id int REFERENCES temp_user(id),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS order_temp_items;
CREATE TABLE order_temp_items(
    id bigserial,
    order_id int REFERENCES order_temp(id),
    product_id int REFERENCES product(id),
    price decimal,
    total_price decimal,
    quantity int,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS order_user;
CREATE TABLE order_user(
    id bigserial,
    user_id int REFERENCES users(id),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS order_user_item;
CREATE TABLE order_user_item(
    id bigserial,
    order_id int REFERENCES order_user(id),
    product_id int REFERENCES product(id),
    price decimal,
    total_price decimal,
    quantity int,
    PRIMARY KEY (id)
);