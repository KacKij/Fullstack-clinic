CREATE TABLE roles (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE privileges (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE role_privileges (
    role_id         BIGINT NOT NULL REFERENCES roles(id),
    privilege_id    BIGINT NOT NULL REFERENCES privileges(id),
    PRIMARY KEY (role_id, privilege_id)
);

insert into roles (name) VALUES
                             ('ADMIN'),
                             ('RECEPTIONIST'),
                             ('SCHEDULER'),
                             ('DOCTOR');

INSERT INTO privileges (name) VALUES
                              ('READ_PATIENT'),
                              ('WRITE_PATIENT'),
                              ('DELETE_PATIENT'),
                              ('MANAGE_SCHEDULE'),
                              ('READ_SCHEDULE'),
                              ('MANAGE_USERS'),
                              ('READ_PESEL'),
                              ('READ_DETAILS'),
                              ('READ_VISITS'),
                              ('WRITE_VISITS'),
                              ('CANCEL_VISITS'),
                              ('READ_LOGS'),
                              ('READ_ALERTS');

INSERT INTO role_privileges (role_id, privilege_id)
SELECT r.id, p.id FROM roles r, privileges p WHERE r.name = 'ADMIN';

-- RECEPTIONIST: patient management + visits + schedule read
INSERT INTO role_privileges (role_id, privilege_id)
SELECT r.id, p.id FROM roles r, privileges p
WHERE r.name = 'RECEPTIONIST'
  AND p.name IN ('READ_PATIENT', 'WRITE_PATIENT', 'READ_SCHEDULE', 'READ_VISITS', 'WRITE_VISITS', 'CANCEL_VISITS');

-- SCHEDULER: schedule management
INSERT INTO role_privileges (role_id, privilege_id)
SELECT r.id, p.id FROM roles r, privileges p
WHERE r.name = 'SCHEDULER'
  AND p.name IN ('MANAGE_SCHEDULE', 'READ_SCHEDULE', 'READ_VISITS');

-- DOCTOR: read patients, read and update visits, read pesel, read details
INSERT INTO role_privileges (role_id, privilege_id)
SELECT r.id, p.id FROM roles r, privileges p
WHERE r.name = 'DOCTOR'
  AND p.name IN ('READ_PATIENT', 'READ_VISITS', 'WRITE_VISITS', 'READ_SCHEDULE', 'READ_PESEL', 'READ_DETAILS');
