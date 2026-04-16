CREATE TABLE doctor_shift_schedules (
    id BIGSERIAL PRIMARY KEY,

    doctor_id BIGINT NOT NULL REFERENCES users(id),

    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,

    created_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT chk_shift_time CHECK (start_time < end_time)
);

CREATE INDEX idx_shift_doctor_date
ON doctor_shift_schedules(doctor_id, date);

CREATE TYPE visit_status AS ENUM (
    'SCHEDULED',
    'COMPLETED',
    'CANCELLED'
);

CREATE TABLE visits (
    id BIGSERIAL PRIMARY KEY,

    patient_id BIGINT NOT NULL REFERENCES patients(id),
    doctor_id BIGINT NOT NULL REFERENCES users(id),
    shift_schedule_id BIGINT REFERENCES doctor_shift_schedules(id),

    slot_start TIMESTAMP WITH TIME ZONE NOT NULL,
    slot_end TIMESTAMP WITH TIME ZONE NOT NULL,
    visit_length_in_slots INT NOT NULL,

    status visit_status NOT NULL DEFAULT 'SCHEDULED',
    notes TEXT,

    created_by INT REFERENCES users(id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT chk_visit_time CHECK (slot_end > slot_start)
);

CREATE INDEX idx_visits_doctor_time
ON visits(doctor_id, slot_start, visit_lenght_in_slots);

CREATE INDEX idx_visits_patient
ON visits(patient_id);