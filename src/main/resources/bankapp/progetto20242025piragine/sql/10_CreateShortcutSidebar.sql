CREATE TABLE IF NOT EXISTS Shortcut_sidebar (
    id_custom INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    type_shortcut TEXT,
    Order1 INTEGER,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    UNIQUE (user_id, type_shortcut)
);