--- INSERT för spel (4 lagspel totalt)
INSERT INTO games (game_id, game_name) VALUES (1, 'CyberBall');
INSERT INTO games (game_id, game_name) VALUES (2, 'BattleStrike');
INSERT INTO games (game_id, game_name) VALUES (3, 'ArenaShowdown');
INSERT INTO games (game_id, game_name) VALUES (4, 'SpaceWars');

-- INSERT för singelspel (4 spel totalt)
INSERT INTO games (game_id, game_name) VALUES (5, 'SpeedRunner');
INSERT INTO games (game_id, game_name) VALUES (6, 'ShadowDuel');
INSERT INTO games (game_id, game_name) VALUES (7, 'SniperEliteX');
INSERT INTO games (game_id, game_name) VALUES (8, 'MysticFight');

-- INSERT för lag (4 lag per spel, 4 spelare per lag)
-- CyberBall (game_id = 1)
INSERT INTO teams (game_id, team_id, team_name) VALUES (1, 1, 'Red Titans');
INSERT INTO teams (game_id, team_id, team_name) VALUES (1, 2, 'Blue Rangers');
INSERT INTO teams (game_id, team_id, team_name) VALUES (1, 3, 'Green Falcons');
INSERT INTO teams (game_id, team_id, team_name) VALUES (1, 4, 'Yellow Vipers');

-- BattleStrike (game_id = 2)
INSERT INTO teams (game_id, team_id, team_name) VALUES (2, 5, 'Iron Wolves');
INSERT INTO teams (game_id, team_id, team_name) VALUES (2, 6, 'Shadow Panthers');
INSERT INTO teams (game_id, team_id, team_name) VALUES (2, 7, 'Silver Bears');
INSERT INTO teams (game_id, team_id, team_name) VALUES (2, 8, 'Night Owls');

-- ArenaShowdown (game_id = 3)
INSERT INTO teams (game_id, team_id, team_name) VALUES (3, 9, 'Golden Hawks');
INSERT INTO teams (game_id, team_id, team_name) VALUES (3, 10, 'Black Cobras');
INSERT INTO teams (game_id, team_id, team_name) VALUES (3, 11, 'Crimson Dragons');
INSERT INTO teams (game_id, team_id, team_name) VALUES (3, 12, 'Blue Tigers');

-- SpaceWars (game_id = 4)
INSERT INTO teams (game_id, team_id, team_name) VALUES (4, 13, 'Star Blazers');
INSERT INTO teams (game_id, team_id, team_name) VALUES (4, 14, 'Galaxy Knights');
INSERT INTO teams (game_id, team_id, team_name) VALUES (4, 15, 'Nebula Warriors');
INSERT INTO teams (game_id, team_id, team_name) VALUES (4, 16, 'Comet Raiders');
-- CyberBall: Red Titans (team_id = 1)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 1, 1, '101 Main St', 'New York', 'USA', 'john.doe@example.com', 'John', 'Doe', 'RedHero', '10001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 2, 1, '102 Main St', 'New York', 'USA', 'jane.smith@example.com', 'Jane', 'Smith', 'RedQueen', '10001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 3, 1, '103 Main St', 'New York', 'USA', 'mike.jones@example.com', 'Mike', 'Jones', 'RedTank', '10001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 4, 1, '104 Main St', 'New York', 'USA', 'sara.connor@example.com', 'Sara', 'Connor', 'RedSniper', '10001');

-- CyberBall: Blue Rangers (team_id = 2)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 5, 2, '201 Oak St', 'Los Angeles', 'USA', 'chris.lee@example.com', 'Chris', 'Lee', 'BlueAce', '90001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 6, 2, '202 Oak St', 'Los Angeles', 'USA', 'kelly.king@example.com', 'Kelly', 'King', 'BlueStar', '90001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 7, 2, '203 Oak St', 'Los Angeles', 'USA', 'tom.rogers@example.com', 'Tom', 'Rogers', 'BlueBlaze', '90001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 8, 2, '204 Oak St', 'Los Angeles', 'USA', 'emma.green@example.com', 'Emma', 'Green', 'BlueShield', '90001');

-- CyberBall: Green Falcons (team_id = 3)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 9, 3, '301 Pine St', 'Chicago', 'USA', 'alex.ray@example.com', 'Alex', 'Ray', 'FalconStrike', '60601');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 10, 3, '302 Pine St', 'Chicago', 'USA', 'lisa.stone@example.com', 'Lisa', 'Stone', 'QuickFlyer', '60601');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 11, 3, '303 Pine St', 'Chicago', 'USA', 'jake.morris@example.com', 'Jake', 'Morris', 'GreenHornet', '60601');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 12, 3, '304 Pine St', 'Chicago', 'USA', 'anna.martin@example.com', 'Anna', 'Martin', 'FlyerAce', '60601');

-- CyberBall: Yellow Vipers (team_id = 4)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 13, 4, '401 Oak St', 'Los Angeles', 'USA', 'john.doe2@example.com', 'John', 'Doe', 'ViperStrike', '90002');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 14, 4, '402 Oak St', 'Los Angeles', 'USA', 'elizabeth.jones@example.com', 'Elizabeth', 'Jones', 'ViperQueen', '90002');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 15, 4, '403 Oak St', 'Los Angeles', 'USA', 'matthew.lee@example.com', 'Matthew', 'Lee', 'ViperStrike', '90002');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (1, 16, 4, '404 Oak St', 'Los Angeles', 'USA', 'diana.smith@example.com', 'Diana', 'Smith', 'YellowFury', '90002');
--- BattleStrike: Red Warriors (team_id = 5)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 17, 5, '101 Red St', 'Miami', 'USA', 'steve.martin@example.com', 'Steve', 'Martin', 'RedBlaze', '33101');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 18, 5, '102 Red St', 'Miami', 'USA', 'david.lee@example.com', 'David', 'Lee', 'FireStorm', '33101');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 19, 5, '103 Red St', 'Miami', 'USA', 'paul.white@example.com', 'Paul', 'White', 'RedFury', '33101');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 20, 5, '104 Red St', 'Miami', 'USA', 'michael.jones@example.com', 'Michael', 'Jones', 'InfernoAce', '33101');

-- BattleStrike: Blue Defenders (team_id = 6)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 21, 6, '201 Blue St', 'Seattle', 'USA', 'luke.morris@example.com', 'Luke', 'Morris', 'BluePhoenix', '98101');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 22, 6, '202 Blue St', 'Seattle', 'USA', 'claire.green@example.com', 'Claire', 'Green', 'AzureStar', '98101');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 23, 6, '203 Blue St', 'Seattle', 'USA', 'jason.brown@example.com', 'Jason', 'Brown', 'BlueKnight', '98101');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 24, 6, '204 Blue St', 'Seattle', 'USA', 'amy.davis@example.com', 'Amy', 'Davis', 'SkyWarrior', '98101');

-- BattleStrike: Green Titans (team_id = 7)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 25, 7, '301 Green St', 'Dallas', 'USA', 'ben.terry@example.com', 'Ben', 'Terry', 'GreenThunder', '75201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 26, 7, '302 Green St', 'Dallas', 'USA', 'olivia.peters@example.com', 'Olivia', 'Peters', 'TitanFury', '75201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 27, 7, '303 Green St', 'Dallas', 'USA', 'ryan.smith@example.com', 'Ryan', 'Smith', 'EmeraldWarrior', '75201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 28, 7, '304 Green St', 'Dallas', 'USA', 'laura.johnson@example.com', 'Laura', 'Johnson', 'GreenBlaze', '75201');

-- BattleStrike: Yellow Reapers (team_id = 8)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 29, 8, '401 Yellow St', 'Chicago', 'USA', 'mark.carter@example.com', 'Mark', 'Carter', 'YellowViper', '60601');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 30, 8, '402 Yellow St', 'Chicago', 'USA', 'michelle.garcia@example.com', 'Michelle', 'Garcia', 'ReaperQueen', '60601');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 31, 8, '403 Yellow St', 'Chicago', 'USA', 'christopher.clark@example.com', 'Christopher', 'Clark', 'ReaperFury', '60601');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (2, 32, 8, '404 Yellow St', 'Chicago', 'USA', 'emma.wilson@example.com', 'Emma', 'Wilson', 'GoldenShred', '60601');
-- ArenaShowdown: Thunder Warriors (team_id = 9)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 33, 9, '101 Thunder St', 'Houston', 'USA', 'james.davis@example.com', 'James', 'Davis', 'ThunderBolt', '77001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 34, 9, '102 Thunder St', 'Houston', 'USA', 'katherine.brown@example.com', 'Katherine', 'Brown', 'BoltAce', '77001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 35, 9, '103 Thunder St', 'Houston', 'USA', 'robert.martin@example.com', 'Robert', 'Martin', 'ThunderStrike', '77001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 36, 9, '104 Thunder St', 'Houston', 'USA', 'susan.moore@example.com', 'Susan', 'Moore', 'LightningStrike', '77001');

-- ArenaShowdown: Storm Riders (team_id = 10)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 37, 10, '201 Storm St', 'Atlanta', 'USA', 'matthew.harris@example.com', 'Matthew', 'Harris', 'StormRider', '30301');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 38, 10, '202 Storm St', 'Atlanta', 'USA', 'amanda.jackson@example.com', 'Amanda', 'Jackson', 'WindStorm', '30301');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 39, 10, '203 Storm St', 'Atlanta', 'USA', 'joshua.moore@example.com', 'Joshua', 'Moore', 'StormKnight', '30301');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 40, 10, '204 Storm St', 'Atlanta', 'USA', 'lisa.wilson@example.com', 'Lisa', 'Wilson', 'TempestWarrior', '30301');

-- ArenaShowdown: Shadow Warriors (team_id = 11)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 41, 11, '301 Shadow St', 'Denver', 'USA', 'olivia.green@example.com', 'Olivia', 'Green', 'ShadowKnight', '80201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 42, 11, '302 Shadow St', 'Denver', 'USA', 'ethan.davis@example.com', 'Ethan', 'Davis', 'DarkRider', '80201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 43, 11, '303 Shadow St', 'Denver', 'USA', 'maria.hall@example.com', 'Maria', 'Hall', 'NightAssassin', '80201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 44, 11, '304 Shadow St', 'Denver', 'USA', 'jared.martin@example.com', 'Jared', 'Martin', 'NightHunter', '80201');

-- ArenaShowdown: Flame Enforcers (team_id = 12)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 45, 12, '401 Flame St', 'Phoenix', 'USA', 'carla.jones@example.com', 'Carla', 'Jones', 'FireEnforcer', '85001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 46, 12, '402 Flame St', 'Phoenix', 'USA', 'james.morris@example.com', 'James', 'Morris', 'FlameKnight', '85001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 47, 12, '403 Flame St', 'Phoenix', 'USA', 'laura.clark@example.com', 'Laura', 'Clark', 'FireStryker', '85001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (3, 48, 12, '404 Flame St', 'Phoenix', 'USA', 'joseph.white@example.com', 'Joseph', 'White', 'InfernoFury', '85001');

-- Space Wars: Galaxy Rangers (team_id = 13)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 49, 13, '101 Galaxy St', 'Orlando', 'USA', 'luke.walker@example.com', 'Luke', 'Walker', 'StarRanger', '32801');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 50, 13, '102 Galaxy St', 'Orlando', 'USA', 'sarah.king@example.com', 'Sarah', 'King', 'NovaAce', '32801');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 51, 13, '103 Galaxy St', 'Orlando', 'USA', 'mark.jones@example.com', 'Mark', 'Jones', 'CosmoKnight', '32801');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 52, 13, '104 Galaxy St', 'Orlando', 'USA', 'emma.smith@example.com', 'Emma', 'Smith', 'StellarBlade', '32801');

-- Space Wars: Cosmic Raiders (team_id = 14)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 53, 14, '201 Cosmic St', 'Dallas', 'USA', 'john.black@example.com', 'John', 'Black', 'CosmicRaider', '75201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 54, 14, '202 Cosmic St', 'Dallas', 'USA', 'olivia.white@example.com', 'Olivia', 'White', 'VoidChaser', '75201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 55, 14, '203 Cosmic St', 'Dallas', 'USA', 'nathan.scott@example.com', 'Nathan', 'Scott', 'AstroKnight', '75201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 56, 14, '204 Cosmic St', 'Dallas', 'USA', 'mia.brown@example.com', 'Mia', 'Brown', 'NebulaQueen', '75201');

-- Space Wars: Star Defenders (team_id = 15)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 57, 15, '301 Star St', 'Los Angeles', 'USA', 'ethan.harris@example.com', 'Ethan', 'Harris', 'StarDefender', '90001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 58, 15, '302 Star St', 'Los Angeles', 'USA', 'ava.jones@example.com', 'Ava', 'Jones', 'GalaxyShield', '90001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 59, 15, '303 Star St', 'Los Angeles', 'USA', 'william.moore@example.com', 'William', 'Moore', 'AstroWarden', '90001');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 60, 15, '304 Star St', 'Los Angeles', 'USA', 'sophia.green@example.com', 'Sophia', 'Green', 'CometBlade', '90001');

-- Space Wars: Solar Sentinels (team_id = 16)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 61, 16, '401 Solar St', 'San Francisco', 'USA', 'jack.wilson@example.com', 'Jack', 'Wilson', 'SolarSentinel', '94101');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 62, 16, '402 Solar St', 'San Francisco', 'USA', 'amelia.martin@example.com', 'Amelia', 'Martin', 'LightStorm', '94101');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 63, 16, '403 Solar St', 'San Francisco', 'USA', 'logan.hall@example.com', 'Logan', 'Hall', 'FlareKnight', '94101');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (4, 64, 16, '404 Solar St', 'San Francisco', 'USA', 'isabella.lee@example.com', 'Isabella', 'Lee', 'RadiantFury', '94101');

-- SpeedRunner (game_id = 5)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (5, 65, NULL, '301 Maple St', 'Chicago', 'USA', 'sam.doe@example.com', 'Sam', 'Doe', 'QuickRunner', '60602');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (5, 66, NULL, '302 Maple St', 'Chicago', 'USA', 'susan.white@example.com', 'Susan', 'White', 'SpeedQueen', '60602');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (5, 67, NULL, '303 Maple St', 'Chicago', 'USA', 'john.green@example.com', 'John', 'Green', 'TurboRacer', '60602');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (5, 68, NULL, '304 Maple St', 'Chicago', 'USA', 'lisa.black@example.com', 'Lisa', 'Black', 'LightningRunner', '60602');

-- ShadowDuel (game_id = 6)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (6, 69, NULL, '401 Oak St', 'Los Angeles', 'USA', 'jack.doe@example.com', 'Jack', 'Doe', 'ShadowKnight', '90003');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (6, 70, NULL, '402 Oak St', 'Los Angeles', 'USA', 'sara.jones@example.com', 'Sara', 'Jones', 'DarkAssassin', '90003');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (6, 71, NULL, '403 Oak St', 'Los Angeles', 'USA', 'mary.smith@example.com', 'Mary', 'Smith', 'ShadowMaster', '90003');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (6, 72, NULL, '404 Oak St', 'Los Angeles', 'USA', 'paul.brown@example.com', 'Paul', 'Brown', 'NightHunter', '90003');

-- SniperEliteX (game_id = 7)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (7, 73, NULL, '501 Birch St', 'Phoenix', 'USA', 'mike.peters@example.com', 'Mike', 'Peters', 'EliteSniper', '85002');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (7, 74, NULL, '502 Birch St', 'Phoenix', 'USA', 'emily.james@example.com', 'Emily', 'James', 'Sharpshooter', '85002');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (7, 75, NULL, '503 Birch St', 'Phoenix', 'USA', 'luke.wilson@example.com', 'Luke', 'Wilson', 'SilentShot', '85002');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (7, 76, NULL, '504 Birch St', 'Phoenix', 'USA', 'olivia.martin@example.com', 'Olivia', 'Martin', 'LongRanger', '85002');

-- MysticFight (game_id = 8)
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (8, 77, NULL, '601 Cedar St', 'Dallas', 'USA', 'aaron.smith@example.com', 'Aaron', 'Smith', 'MysticKnight', '75201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (8, 78, NULL, '602 Cedar St', 'Dallas', 'USA', 'heather.brown@example.com', 'Heather', 'Brown', 'MagicWarrior', '75201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (8, 79, NULL, '603 Cedar St', 'Dallas', 'USA', 'joshua.williams@example.com', 'Joshua', 'Williams', 'ElementalMage', '75201');
INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code) VALUES (8, 80, NULL, '604 Cedar St', 'Dallas', 'USA', 'laura.miller@example.com', 'Laura', 'Miller', 'MysticQueen', '75201');




-- Insert data into GAMES table
-- INSERT INTO games (game_id, game_name) VALUES (1, 'Soccer');
-- INSERT INTO games (game_id, game_name) VALUES (2, 'Basketball');
-- INSERT INTO games (game_id, game_name) VALUES (3, 'Baseball');
-- INSERT INTO games (game_id, game_name) VALUES (4, 'Hockey');
-- INSERT INTO games (game_id, game_name) VALUES (5, 'Tennis');
--
-- -- Insert data into TEAMS table
-- INSERT INTO teams (game_id, team_id, team_name) VALUES (1, 1, 'Red Dragons');
-- INSERT INTO teams (game_id, team_id, team_name) VALUES (2, 2, 'Blue Sharks');
-- INSERT INTO teams (game_id, team_id, team_name) VALUES (3, 3, 'Golden Eagles');
--
-- -- Insert data into PLAYER table
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (1, 1, 1, '123 Main St', 'New York', 'USA', 'john.doe@example.com', 'John', 'Doe', 'Johnny', '12345');
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (2, 2, 2, '456 Maple Ave', 'Los Angeles', 'USA', 'jane.smith@example.com', 'Jane', 'Smith', 'Janey', '54321');
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (3, 3, 3, '789 Oak Dr', 'Chicago', 'USA', 'bob.brown@example.com', 'Bob', 'Brown', 'Bobby', '67890');
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (1, 4, 1, '321 Pine St', 'San Francisco', 'USA', 'alice.green@example.com', 'Alice', 'Green', 'Ali', '45678');
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (2, 5, 2, '654 Cedar Ln', 'Houston', 'USA', 'tom.white@example.com', 'Tom', 'White', 'Tommy', '98765');
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (3, 6, 3, '987 Birch Blvd', 'Miami', 'USA', 'emma.johnson@example.com', 'Emma', 'Johnson', 'Em', '23456');
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (1, 7, 1, '741 Spruce St', 'Seattle', 'USA', 'michael.williams@example.com', 'Michael', 'Williams', 'Mike', '13579');
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (2, 8, 2, '852 Willow Ave', 'Boston', 'USA', 'sophia.lee@example.com', 'Sophia', 'Lee', 'Sophie', '24680');
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (3, 9, 3, '963 Elm Rd', 'Denver', 'USA', 'chris.miller@example.com', 'Chris', 'Miller', 'Chris', '11223');
-- INSERT INTO player (game_id, player_id, team_id, player_address, player_city, player_country, player_email, player_first_name, player_last_name, player_nick_name, player_zip_code)VALUES (1, 10, 1, '159 Redwood St', 'Austin', 'USA', 'olivia.davis@example.com', 'Olivia', 'Davis', 'Liv', '33445');
--
-- -- Första tabellen
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (1, 1, TRUE, NULL, NULL, 1, 2, NULL, 1, '2024-05-01 15:00:00', '3-1', 'TEAM_MATCH');
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (2, 2, TRUE, 1, 2, NULL, NULL, 1, NULL, '2024-05-02 16:00:00', '2-0', 'PLAYER_MATCH');
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (3, 1, TRUE, NULL, NULL, 3, 4, NULL, 3, '2024-05-03 18:00:00', '1-0', 'TEAM_MATCH');
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (4, 3, TRUE, 3, 4, NULL, NULL, 4, NULL, '2024-05-04 14:00:00', '2-1', 'PLAYER_MATCH');
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (5, 1, TRUE, NULL, NULL, 5, 6, NULL, 6, '2024-05-05 17:00:00', '4-2', 'TEAM_MATCH');
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (6, 3, TRUE, 5, 6, NULL, NULL, 6, NULL, '2024-05-06 13:00:00', '3-0', 'PLAYER_MATCH');
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (7, 2, TRUE, NULL, NULL, 7, 8, NULL, 7, '2024-05-07 19:00:00', '5-4', 'TEAM_MATCH');
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (8, 2, TRUE, 7, 8, NULL, NULL, 7, NULL, '2024-05-08 11:00:00', '1-0', 'PLAYER_MATCH');
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (9, 1, TRUE, NULL, NULL, 9, 10, NULL, 10, '2024-05-09 15:30:00', '2-1', 'TEAM_MATCH');
-- INSERT INTO matches (id, game_id, is_finished, player1_id, player2_id, team1_id, team2_id, winner_player_id, winner_team_id, match_date, result, match_type)VALUES (10, 2, TRUE, 9, 10,
-- -- Insert data into GAME table
-- INSERT INTO game (game_id, game_name) VALUES (1, 'Soccer');
-- INSERT INTO game (game_id, game_name) VALUES (2, 'Basketball');
-- INSERT INTO game (game_id, game_name) VALUES (3, 'Baseball');
-- INSERT INTO game (game_id, game_name) VALUES (4, 'Hockey');
-- INSERT INTO game (game_id, game_name) VALUES (5, 'Tennis');
--
-- -- Insert data into TEAM table först
-- INSERT INTO team (team_id, team_name) VALUES (1, 'Red Dragons');
-- INSERT INTO team (team_id, team_name) VALUES (2, 'Blue Sharks');
-- INSERT INTO team (team_id, team_name) VALUES (3, 'Golden Eagles');
--
-- -- -- Insert data into PLAYER table efter team
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1,1,1, 1, 'John', 'Doe', 'Johnny');
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (2,2, 2, 'Jane', 'Smith', 'Janey');
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (3,3, 3, 'Bob', 'Brown', 'Bobby');
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1,4, 1, 'Alice', 'Green', 'Ali');
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (2,5, 2, 'Tom', 'White', 'Tommy');
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (3,6, 3, 'Emma', 'Johnson', 'Em');
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1,7, 1, 'Michael', 'Williams', 'Mike');
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (2,8, 2, 'Sophia', 'Lee', 'Sophie');
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (3,9, 3, 'Chris', 'Miller', 'Chris');
-- INSERT INTO player (game_id, player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1,10, 1, 'Olivia', 'Davis', 'Liv');
--
-- -- -- -- -- Insert data into PLAYER table efter team
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (1, 1, 'John', 'Doe', 'Johnny');
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (2, 2, 'Jane', 'Smith', 'Janey');
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (3, 3, 'Bob', 'Brown', 'Bobby');
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (4, 1, 'Alice', 'Green', 'Ali');
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (5, 2, 'Tom', 'White', 'Tommy');
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (6, 3, 'Emma', 'Johnson', 'Em');
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (7, 1, 'Michael', 'Williams', 'Mike');
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (8, 2, 'Sophia', 'Lee', 'Sophie');
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (9, 3, 'Chris', 'Miller', 'Chris');
-- -- INSERT INTO player (player_id, team_id, player_first_name, player_last_name, player_nick_name) VALUES (10, 1, 'Olivia', 'Davis', 'Liv');

INSERT INTO staff (first_name, last_name, nickname, email, address, postal_code, city, country) VALUES ('Richard', 'Hendricks', 'Richie', 'richard.hendricks@piedpiper.com', '123 Startup Lane', '94025', 'Menlo Park', 'USA');
INSERT INTO staff (first_name, last_name, nickname, email, address, postal_code, city, country) VALUES ('Bertram', 'Gilfoyle', 'Gilfoyle', 'gilfoyle@piedpiper.com', '456 Hacker Way', '94040', 'Mountain View', 'USA');
INSERT INTO staff (first_name, last_name, nickname, email, address, postal_code, city, country) VALUES ('Dinesh', 'Chugtai', 'Dinesh', 'dinesh.chugtai@piedpiper.com', '789 Coders Street', '94041', 'Sunnyvale', 'USA');
INSERT INTO staff (first_name, last_name, nickname, email, address, postal_code, city, country) VALUES ('Erlich', 'Bachman', 'Erlich', 'erlich.bachman@piedpiper.com', '321 Aviato Avenue', '94025', 'Palo Alto', 'USA');
INSERT INTO staff (first_name, last_name, nickname, email, address, postal_code, city, country) VALUES ('Donald', 'Dunn', 'Jared', 'jared.dunn@piedpiper.com', '987 Optimist Way', '94063', 'Redwood City', 'USA');
INSERT INTO staff (first_name, last_name, nickname, email, address, postal_code, city, country) VALUES ('Monica', 'Hall', 'Monica', 'monica.hall@piedpiper.com', '654 Venture Capital Blvd', '94043', 'Mountain View', 'USA');
INSERT INTO staff (first_name, last_name, nickname, email, address, postal_code, city, country) VALUES ('Nelson', 'Bighetti', 'Big Head', 'bighead@piedpiper.com', '222 Cloud Computing Drive', '94087', 'Cupertino', 'USA');


