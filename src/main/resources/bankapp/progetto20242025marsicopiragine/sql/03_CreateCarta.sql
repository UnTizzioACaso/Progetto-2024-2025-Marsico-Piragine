CREATE TABLE IF NOT EXISTS Carta (
    id_carta INTEGER PRIMARY KEY AUTOINCREMENT,
    id_conto INTEGER,
    numero TEXT CHECK (LENGTH(numero) = 16),
    cvv_hash TEXT,
    scadenza DATE,
    nickname TEXT,
    colore TEXT,
    preferita BOOLEAN,
    soglia_spesa_mensile DECIMAL CHECK (soglia_spesa_mensile >= 0),
    attiva BOOLEAN,
    FOREIGN KEY (id_conto) REFERENCES Conto(id_conto) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_carta_id_conto ON Carta(id_conto);