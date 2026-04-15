CREATE TABLE address (
    id                 BIGSERIAL PRIMARY KEY,
    street             VARCHAR(255) NOT NULL,
    city               VARCHAR(255) NOT NULL,
    zip_code           VARCHAR(50) NOT NULL,
    state              VARCHAR(255),
    country            VARCHAR(255) NOT NULL,
    street_number      VARCHAR(50) NOT NULL,
    apartment_name     VARCHAR(50),

    created_at         TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT NOT NULL
);

CREATE TABLE address_change_log (
                                    id BIGSERIAL PRIMARY KEY,
                                    old_address_id BIGINT,
                                    new_address_id BIGINT NOT NULL,
                                    changed_by BIGINT NOT NULL,
                                    changed_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);