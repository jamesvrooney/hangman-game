CREATE SEQUENCE game_seq START WITH 0;
CREATE SEQUENCE player_seq START WITH 0;

CREATE TABLE player (
	id NUMBER(19) NOT NULL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE game (
	id NUMBER(19) NOT NULL PRIMARY KEY,
	game_state VARCHAR(12) NOT NULL,
	word VARCHAR(20) NOT NULL,
	guess VARCHAR(1),
	num_remaining_incorrect_guesses NUMBER(2),
	existing_guessed_letters VARCHAR(20),
	word_contains_guessed_letter BOOLEAN,
	player_id NUMBER(19),
	FOREIGN KEY (player_id) REFERENCES player (id)
--	CONSTRAINT fk_game_player_id FOREIGN KEY (player_id) REFERENCES player (id) ON DELETE CASCADE ON UPDATE CASCADE
);
