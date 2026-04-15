CREATE TABLE patients (
    id                      BIGSERIAL PRIMARY KEY,

    firstname               VARCHAR(100) NOT NULL,
    lastname                VARCHAR(100) NOT NULL,

    pesel                   VARCHAR(11) UNIQUE NOT NULL,
    email                   VARCHAR(150),

    phone_number            VARCHAR(20),
    phone_number_ext        VARCHAR(10),

    date_of_birth           DATE,
    gender                  VARCHAR(20),

    address_id              BIGINT REFERENCES address(id),

    last_visit_date         DATE,
    last_visit_time         TIME,
    last_visit_doctor_id    BIGINT REFERENCES users(id),

    created_at              TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP WITH TIME ZONE,

    created_by              BIGINT REFERENCES users(id)
);