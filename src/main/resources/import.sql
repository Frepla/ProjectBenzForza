-- Insert data into GAME table
INSERT INTO game (game_id, game_name) VALUES (1, 'Soccer');
INSERT INTO game (game_id, game_name) VALUES (2, 'Basketball');
INSERT INTO game (game_id, game_name) VALUES (3, 'Baseball');
INSERT INTO game (game_id, game_name) VALUES (4, 'Hockey');
INSERT INTO game (game_id, game_name) VALUES (5, 'Tennis');

-- Insert data into TEAM table först
INSERT INTO team (team_id, team_name) VALUES (1, 'Red Dragons');
INSERT INTO team (team_id, team_name) VALUES (2, 'Blue Sharks');
INSERT INTO team (team_id, team_name) VALUES (3, 'Golden Eagles');

-- -- Insert data into PLAYER table efter team
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1,1,1, 1, 'John', 'Doe', 'Johnny');
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (2,2, 2, 'Jane', 'Smith', 'Janey');
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (3,3, 3, 'Bob', 'Brown', 'Bobby');
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1,4, 1, 'Alice', 'Green', 'Ali');
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (2,5, 2, 'Tom', 'White', 'Tommy');
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (3,6, 3, 'Emma', 'Johnson', 'Em');
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1,7, 1, 'Michael', 'Williams', 'Mike');
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (2,8, 2, 'Sophia', 'Lee', 'Sophie');
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (3,9, 3, 'Chris', 'Miller', 'Chris');
INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1,10, 1, 'Olivia', 'Davis', 'Liv');

-- -- -- -- Insert data into PLAYER table efter team
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1, 1, 'John', 'Doe', 'Johnny');
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (2, 2, 'Jane', 'Smith', 'Janey');
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (3, 3, 'Bob', 'Brown', 'Bobby');
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (4, 1, 'Alice', 'Green', 'Ali');
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (5, 2, 'Tom', 'White', 'Tommy');
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (6, 3, 'Emma', 'Johnson', 'Em');
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (7, 1, 'Michael', 'Williams', 'Mike');
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (8, 2, 'Sophia', 'Lee', 'Sophie');
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (9, 3, 'Chris', 'Miller', 'Chris');
-- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (10, 1, 'Olivia', 'Davis', 'Liv');



