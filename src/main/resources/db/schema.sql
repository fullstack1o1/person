CREATE TABLE Person (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    profile_photo_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE Address (
     id SERIAL PRIMARY KEY,
     type VARCHAR(10),
     person_id INT REFERENCES Person(id) ON DELETE CASCADE,
     street VARCHAR(255) NOT NULL,
     city VARCHAR(100) NOT NULL,
     state VARCHAR(100),
     zip_code VARCHAR(20),
     country VARCHAR(100) NOT NULL
);

CREATE TABLE "ContactInfo" (
     id SERIAL PRIMARY KEY,
     person_id INT REFERENCES Person(id) ON DELETE CASCADE,
     email VARCHAR(255) UNIQUE,
     phone_number VARCHAR(20)
);

CREATE TABLE "SocialMedia" (
     id SERIAL PRIMARY KEY,
     person_id INT REFERENCES Person(id) ON DELETE CASCADE,
     platform VARCHAR(50) NOT NULL,
     handle VARCHAR(100) NOT NULL
);
