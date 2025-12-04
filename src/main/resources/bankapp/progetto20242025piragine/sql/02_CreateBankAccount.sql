 CREATE TABLE IF NOT EXISTS Bank_Account (
    id_conto INTEGER PRIMARY KEY AUTOINCREMENT,
    id_utente INTEGER NOT NULL UNIQUE,
    saldo DECIMAL CHECK (saldo >= 0),
    valuta TEXT CHECK (valuta IN ('EUR', 'USD', 'GBP')),
    iban TEXT UNIQUE,
    bic_swift TEXT,
    sede_banca TEXT,
    soglia_invio_rapido DECIMAL DEFAULT 50,
    forza_pin_rapido BOOLEAN DEFAULT FALSE,
    stato_conto TEXT CHECK (stato_conto IN ('attivo', 'chiuso')),
    FOREIGN KEY (id_utente) REFERENCES User(id_utente) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_conto_id_utente ON Bank_Account(id_utente);