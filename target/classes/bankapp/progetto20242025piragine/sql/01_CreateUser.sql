PRAGMA foreign_keys = ON;
CREATE TABLE IF NOT EXISTS User (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    birth_place TEXT NOT NULL,
    birth_day TEXT NOT NULL,
    gender TEXT,
    state TEXT NOT NULL,
    province TEXT NOT NULL,
    city TEXT NOT NULL,
    address TEXT NOT NULL,
    street_number TEXT NOT NULL,
    cap TEXT NOT NULL,
    phone_number INTEGER NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    username TEXT UNIQUE NOT NULL,
    --tax_code TEXT UNIQUE NOT NULL CHECK (LENGTH(tax_code) = 16),
    password_hash TEXT ,
    pin_hash TEXT ,
    remember_credentials BOOLEAN DEFAULT FALSE,
    last_access_date DATETIME,
    theme TEXT DEFAULT 'light' CHECK (theme IN ('light', 'dark'))
    );
