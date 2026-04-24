CREATE TABLE users (
    id                  BIGSERIAL PRIMARY KEY,

    username            VARCHAR(128) NOT NULL UNIQUE,
    firstname           VARCHAR(128) NOT NULL,
    lastname            VARCHAR(128) NOT NULL,
    email               VARCHAR(256) NOT NULL UNIQUE,
    password_hash       VARCHAR(512) NOT NULL,

    enabled             BOOLEAN DEFAULT TRUE,
    occupation          VARCHAR(256),

    phone_number        VARCHAR(64),
    phone_number_ext    VARCHAR(32),

    address_id          BIGINT REFERENCES address(id),

    created_at          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by          BIGINT NOT NULL,

    updated_at          TIMESTAMP WITH TIME ZONE,
    updated_by          BIGINT NOT NULL
);

CREATE TABLE users_roles (
    user_id             BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role_id             BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);