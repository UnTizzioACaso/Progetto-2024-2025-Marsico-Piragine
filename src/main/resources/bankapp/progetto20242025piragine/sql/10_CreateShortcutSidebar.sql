CREATE TABLE IF NOT EXISTS Shortcut_sidebar (
    id_custom INTEGER PRIMARY KEY AUTOINCREMENT,
    id_utente INTEGER,
    tipo_scorciatoia TEXT,
    ordine INTEGER,
    FOREIGN KEY (id_utente) REFERENCES User(id_utente) ON DELETE CASCADE,
    UNIQUE (id_utente, tipo_scorciatoia)
);