CREATE TABLE IF NOT EXISTS Scorciatoia_sidebar (
    id_custom INTEGER PRIMARY KEY AUTOINCREMENT,
    id_utente INTEGER,
    tipo_scorciatoia TEXT,
    ordine INTEGER,
    FOREIGN KEY (id_utente) REFERENCES Utente(id_utente) ON DELETE CASCADE,
    UNIQUE (id_utente, tipo_scorciatoia)
);