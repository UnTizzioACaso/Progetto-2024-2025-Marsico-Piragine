CREATE TABLE IF NOT EXISTS Home_Widget_Custom (
    id_widget INTEGER PRIMARY KEY AUTOINCREMENT,
    id_utente INTEGER,
    tipo_widget TEXT,
    grandezza TEXT,
    pos INTEGER,
    rimovibile BOOLEAN,
    FOREIGN KEY (id_utente) REFERENCES Utente(id_utente) ON DELETE CASCADE,
    UNIQUE (id_utente, pos)
);