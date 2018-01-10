CREATE TABLE users (
    user_id BIGINT PRIMARY KEY auto_increment,
    username VARCHAR(128) UNIQUE,
    password VARCHAR(256),
    PRIMARY KEY (user_id)
);

CREATE TABLE hello (
    id BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(128),
    PRIMARY KEY (id)
);