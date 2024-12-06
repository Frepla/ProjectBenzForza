-- Skapa databasen
CREATE DATABASE piper_games;
USE piper_games;

-- Tabell för spelare
CREATE TABLE players (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) UNIQUE NOT NULL,
    street_address VARCHAR(100),
    postal_code VARCHAR(10),
    city VARCHAR(50),
    country VARCHAR(50),
    email VARCHAR(100)
);

-- Tabell för spel
CREATE TABLE games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL
);

-- Tabell för lag
CREATE TABLE teams (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    game_id BIGINT NOT NULL,
    FOREIGN KEY (game_id) REFERENCES games(id)
);

-- Tabell för matcher
CREATE TABLE matches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    match_date DATE NOT NULL,
    team1_id BIGINT,
    team2_id BIGINT,
    player1_id BIGINT,
    player2_id BIGINT,
    winner_id BIGINT,
    is_team_match BOOLEAN NOT NULL,
    FOREIGN KEY (team1_id) REFERENCES teams(id),
    FOREIGN KEY (team2_id) REFERENCES teams(id),
    FOREIGN KEY (player1_id) REFERENCES players(id),
    FOREIGN KEY (player2_id) REFERENCES players(id),
    FOREIGN KEY (winner_id) REFERENCES players(id)
);

-- Tabell för personal
CREATE TABLE staff (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) UNIQUE NOT NULL,
    street_address VARCHAR(100),
    postal_code VARCHAR(10),
    city VARCHAR(50),
    country VARCHAR(50),
    email VARCHAR(100)
);
