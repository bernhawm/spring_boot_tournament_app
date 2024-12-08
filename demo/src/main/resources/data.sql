
-- Insert predefined players
INSERT INTO users  (first_name,last_name,password, email) VALUES
('Alice', 'Bernhardt', 'password', 'alice@example.com'),
('Bob', 'Bernhardt', 'password', 'bob@example.com');

--Insert predefined tournaments
-- Example of inserting data without specifying 'id' (it is auto-generated)
INSERT INTO tournaments (rounds, date, format, standings) 
VALUES (5, '2024-12-10T10:00:00', 'Swiss', '{"standings":[]}');


-- Link players to tournaments
INSERT INTO TOURNAMENT_PLAYERS (tournament_id, player_id)
VALUES (1, 1), (1, 2);
