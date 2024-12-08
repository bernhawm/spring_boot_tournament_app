
-- Insert predefined players
INSERT INTO users  (first_name,last_name,password, email) VALUES
('Alice', 'Bernhardt', 'password', 'alice@example.com'),
('Bob', 'Bernhardt', 'password', 'bob@example.com'),
('Jack', 'Bernhardt', 'password', 'Jack@example.com'),
('Johnson', 'Bernhardt', 'password', 'Johnson@example.com');
--Insert predefined tournaments
-- Example of inserting data without specifying 'id' (it is auto-generated)
INSERT INTO tournaments (rounds, date, format, standings) 
VALUES (5, '2024-12-10T10:00:00', 'Swiss', '{"standings":[]}');


-- Link players to tournaments
INSERT INTO TOURNAMENT_PLAYERS (tournament_id, player_id, is_in_tournament)
VALUES 
(1, 1, true), 
(1, 2, true), 
(1, 3, true), 
(1, 4, true);

-- Insert a round
INSERT INTO rounds (tournament_id, start_time) 
VALUES (1, '2024-12-10T12:00:00');

-- -- Insert two matches linked to the round above
-- INSERT INTO matches (round_id, tournament_id, player1Id, player2Id, result, winnerId)
-- VALUES 
-- (1, 1, 1, 2, 'Pending', NULL),
-- (1, 1, 3, 4, 'Pending', NULL);
-- Insert matches linked to the round and tournament
INSERT INTO matches (round_id, tournament_id, PLAYER1ID , PLAYER2ID, result, winner_id)
VALUES 
(1, 1, 1, 2, 'Pending', NULL),
(1, 1, 3, 4, 'Pending', NULL);