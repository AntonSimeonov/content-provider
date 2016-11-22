--CREATE TABLE train_alarm (
--id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
--alarm_id INTEGER NOT NULL,
--train_station TEXT NOT NULL,
--train_number TEXT NOT NULL,
--start_notification TEXT NOT NULL
--);
CREATE TABLE user (
id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
user_name TEXT NOT NULL,
user_password TEXT NOT NULL,
user_mail TEXT NOT NULL,
user_address TEXT NOT NULL,
job_id INTEGER
);
CREATE TABLE job (
id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
job_title TEXT NOT NULL,
job_description TEXT NOT NULL,
job_state_number INTEGER NOT NULL
);