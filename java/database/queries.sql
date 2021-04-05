BEGIN TRANSACTION;

DROP TABLE IF EXISTS comics;
DROP TABLE IF EXISTS collections;
DROP TABLE IF EXISTS characters;
DROP TABLE IF EXISTS creator;
DROP TABLE IF EXISTS series;
DROP TABLE IF EXISTS publisher;
DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS collection_user;
DROP TABLE IF EXISTS collection_comic;
DROP TABLE IF EXISTS characters_comic;


DROP SEQUENCE IF EXISTS seq_comic_id;
DROP SEQUENCE IF EXISTS seq_collection_id;
DROP SEQUENCE IF EXISTS seq_character_id;
DROP SEQUENCE IF EXISTS seq_creator_id;
DROP SEQUENCE IF EXISTS seq_series_id;
DROP SEQUENCE IF EXISTS seq_publisher_id;


CREATE SEQUENCE seq_comic_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_collection_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_character_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_creator_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_series_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_publisher_id
  INCREMENT BY 1
  START WITH 4001
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE comics (

	comic_id int DEFAULT nextval('seq_comic_id'::regclass) NOT NULL,
	title varchar(50) NOT NULL,
	issue_num int NOT NULL,
	series varchar(50) NOT NULL,
	description varchar(50) NOT NULL,
	img varchar(200) NOT NULL,
	
	CONSTRAINT PK_comics PRIMARY KEY (comic_id)
);

CREATE TABLE collections (

	collection_id int DEFAULT nextval('seq_collection_id'::regclass) NOT NULL,
	user_id int NOT NULL,
	name varchar(50) NOT NULL,
	hidden boolean DEFAULT false, --would we need this in the table or in the vue side?
	
	CONSTRAINT PK_collections PRIMARY KEY (collection_id)
);

CREATE TABLE characters (

	character_id int DEFAULT nextval('seq_character_id'::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	
	CONSTRAINT PK_characters PRIMARY KEY (character_id)
	--CONSTRAINT UQ_username UNIQUE (username)
);

CREATE TABLE creator (

	creator_id int DEFAULT nextval('seq_creator_id'::regclass) NOT NULL,
	first_name varchar(50)NOT NULL,
	last_name varchar(50) NOT NULL,
	full_name varchar(50) NOT NULL,
	
        CONSTRAINT PK_creator PRIMARY KEY (creator_id)
	--CONSTRAINT FK_accounts_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE series (

	series_id int DEFAULT nextval('seq_series_id'::regclass) NOT NULL,
	title varchar(50) NOT NULL,
	description varchar(100) NOT NULL,
	start_year int NOT NULL,
	end_year int NOT NULL,
	rating varchar(50) NOT NULL,
	
	CONSTRAINT PK_series PRIMARY KEY (series_id)
	
);

CREATE TABLE user_info (

	user_info_id int NOT NULL,
	is_premium boolean DEFAULT false,
	first_name varchar(50)NOT NULL,
	last_name varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	
	CONSTRAINT PK_user_info PRIMARY KEY (user_info_id)
);
	
CREATE TABLE publisher (

	publisher_id int DEFAULT nextval('seq_publisher_id'::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	
	CONSTRAINT PK_publisher PRIMARY KEY (publisher_id)
);
ROLLBACK;

--COMMIT TRANSACTION;

