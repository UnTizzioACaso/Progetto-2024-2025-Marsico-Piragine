CREATE TABLE IF NOT EXISTS Home_Widget_Custom (
    id_widget INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    type_widget TEXT,
    y INT check ( y>0 and y<3 ),
    x INT check ( x>=0 and x<2 ),
    remove BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
);