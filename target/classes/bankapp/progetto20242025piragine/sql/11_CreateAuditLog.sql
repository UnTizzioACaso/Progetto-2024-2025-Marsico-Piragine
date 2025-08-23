CREATE TABLE IF NOT EXISTS Audit_Log (
    id_log INTEGER PRIMARY KEY AUTOINCREMENT,
    id_utente INTEGER,
    azione TEXT,
    dettagli TEXT,
    esito TEXT CHECK(esito IN ('SUCCESSO', 'FALLITO')),
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_utente) REFERENCES Utente(id_utente) ON DELETE CASCADE
);