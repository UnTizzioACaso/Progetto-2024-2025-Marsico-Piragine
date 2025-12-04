CREATE TABLE IF NOT EXISTS Friend_Request (
    id_richiesta INTEGER PRIMARY KEY AUTOINCREMENT,
    richiedente INTEGER NOT NULL,
    destinatario INTEGER NOT NULL,
    data_invio DATETIME NOT NULL,
    stato TEXT CHECK(stato IN ('in_attesa', 'rifiutata')) DEFAULT 'in_attesa',
    FOREIGN KEY (richiedente) REFERENCES User(id_utente) ON DELETE CASCADE,
    FOREIGN KEY (destinatario) REFERENCES User(id_utente) ON DELETE CASCADE,
    UNIQUE (richiedente, destinatario)
);