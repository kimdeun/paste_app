CREATE TABLE IF NOT EXISTS paste_entity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data varchar(100) NOT NULL,
    hash varchar(100) NOT NULL,
    life_time DATETIME NOT NULL,
    is_public BOOL NOT NULL
);

