CREATE TABLE IF NOT EXISTS Transazione (
    id_transazione INTEGER PRIMARY KEY AUTOINCREMENT,
    mittente INTEGER,
    destinatario INTEGER,
    importo DECIMAL CHECK (importo > 0),
    motivazione TEXT,
    data_transazione DATETIME,
    stato TEXT CHECK(stato IN ('inviata','fallita')),
    tipo TEXT CHECK(tipo IN ('Ricarica','Bonifico', 'Invio Rapido')),
    carta_utilizzata INTEGER,
    FOREIGN KEY (mittente) REFERENCES Conto(id_conto) ON DELETE SET NULL,
    FOREIGN KEY (destinatario) REFERENCES Conto(id_conto) ON DELETE SET NULL,
    FOREIGN KEY (carta_utilizzata) REFERENCES Carta(id_carta) ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS idx_transazione_mittente ON Transazione(mittente);
CREATE INDEX IF NOT EXISTS idx_transazione_destinatario ON Transazione(destinatario);