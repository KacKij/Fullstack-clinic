CREATE TABLE patients (
    id                      BIGSERIAL PRIMARY KEY,

    firstname               VARCHAR(128) NOT NULL,
    lastname                VARCHAR(128) NOT NULL,

    pesel                   VARCHAR(11) UNIQUE NOT NULL,
    email                   VARCHAR(256) UNIQUE NOT NULL,

    phone_number            VARCHAR(64),
    phone_number_ext        VARCHAR(32),

    date_of_birth           DATE,
    gender                  VARCHAR(32),

    address_id              BIGINT REFERENCES address(id),

    last_visit_date         DATE,
    last_visit_time         TIME,
    last_visit_doctor_id    BIGINT REFERENCES users(id),

    created_at              TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP WITH TIME ZONE,

    created_by              BIGINT NOT NULL
);