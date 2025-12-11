CREATE TABLE IF NOT EXISTS Home_Widget_Custom (
    id_widget INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    type_widget TEXT,
    size TEXT,
    position INTEGER,
    remove BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    UNIQUE (user_id,position)
);