CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    profile_photo_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE address (
     id SERIAL PRIMARY KEY,
     address_type VARCHAR(20) NOT NULL CHECK (address_type IN ('HOME', 'WORK', 'OTHER')),
     person_id INT REFERENCES person(id) ON DELETE CASCADE,
     street VARCHAR(255) NOT NULL,
     city VARCHAR(100) NOT NULL,
     state VARCHAR(100),
     zip_code VARCHAR(20),
     country VARCHAR(100) NOT NULL,
     constraint unique_address UNIQUE (person_id, address_type)
);

CREATE TABLE contact_info (
     id SERIAL PRIMARY KEY,
     contact_info_type VARCHAR(20) NOT NULL check ( contact_info_type IN ('PERSONAL', 'WORK', 'HOME')),
     person_id INT REFERENCES person(id) ON DELETE CASCADE,
     email VARCHAR(255) UNIQUE,
     phone_number VARCHAR(20),
     constraint unique_contact_info UNIQUE (person_id, contact_info_type)
);

CREATE TABLE social_media (
     id SERIAL PRIMARY KEY,
     person_id INT REFERENCES person(id) ON DELETE CASCADE,
     platform VARCHAR(50) NOT NULL,
     handle VARCHAR(100) NOT NULL
);
