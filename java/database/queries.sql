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
	desc varchar(50) NOT NULL,
	img varchar(200) NOT NULL
	//CONSTRAINT PK_transfer_types PRIMARY KEY (transfer_type_id)
);

CREATE TABLE transfer_statuses (
	transfer_status_id int DEFAULT nextval('seq_transfer_status_id'::regclass) NOT NULL,
	transfer_status_desc varchar(10) NOT NULL,
	CONSTRAINT PK_transfer_statuses PRIMARY KEY (transfer_status_id)
);

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

CREATE TABLE accounts (
	account_id int DEFAULT nextval('seq_account_id'::regclass) NOT NULL,
	user_id int NOT NULL,
	balance decimal(13, 2) NOT NULL,
	CONSTRAINT PK_accounts PRIMARY KEY (account_id),
	CONSTRAINT FK_accounts_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE transfers (
	transfer_id int DEFAULT nextval('seq_transfer_id'::regclass) NOT NULL,
	transfer_type_id int NOT NULL,
	transfer_status_id int NOT NULL,
	account_from int NOT NULL,
	account_to int NOT NULL,
	amount decimal(13, 2) NOT NULL,
	CONSTRAINT PK_transfers PRIMARY KEY (transfer_id),
	CONSTRAINT FK_transfers_accounts_from FOREIGN KEY (account_from) REFERENCES accounts (account_id),
	CONSTRAINT FK_transfers_accounts_to FOREIGN KEY (account_to) REFERENCES accounts (account_id),
	CONSTRAINT FK_transfers_transfer_statuses FOREIGN KEY (transfer_status_id) REFERENCES transfer_statuses (transfer_status_id),
	CONSTRAINT FK_transfers_transfer_types FOREIGN KEY (transfer_type_id) REFERENCES transfer_types (transfer_type_id),
	CONSTRAINT CK_transfers_not_same_account CHECK  ((account_from<>account_to)),
	CONSTRAINT CK_transfers_amount_gt_0 CHECK ((amount>0))
);

ROLLBACK;

//COMMIT TRANSACTION;

