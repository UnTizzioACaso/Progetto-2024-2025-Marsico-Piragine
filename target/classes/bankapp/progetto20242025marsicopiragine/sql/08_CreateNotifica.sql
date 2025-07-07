CREATE TABLE IF NOT EXISTS Notifica (
    id_notifica INTEGER PRIMARY KEY AUTOINCREMENT,
    id_utente INTEGER,
    id_transazione INTEGER,
    id_richiesta_amicizia INTEGER,
    id_amicizia INTEGER,
    messaggio TEXT,
    letta BOOLEAN,
    data_creazione DATETIME,
    FOREIGN KEY (id_utente) REFERENCES Utente(id_utente) ON DELETE CASCADE,
    FOREIGN KEY (id_transazione) REFERENCES Transazione(id_transazione) ON DELETE CASCADE,
    FOREIGN KEY (id_richiesta_amicizia) REFERENCES RichiestaAmicizia(id_richiesta) ON DELETE CASCADE,
    FOREIGN KEY (id_amicizia) REFERENCES Amicizia(id_amicizia) ON DELETE CASCADE
);