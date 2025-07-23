CREATE TABLE roles
(
    id   BINARY(16)   NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BINARY(16)   NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NULL,
    role_id    BINARY(16)   NULL,
    created_at datetime     NULL,
    updated_at datetime     NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);