CREATE DATABASE IF NOT EXISTS PyeonYook;
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE PyeonYook;

CREATE TABLE IF NOT EXISTS user(
    userno int not null,
    name VARCHAR(32) ,
    id VARCHAR(128) UNIQUE, -- "email"
    password VARCHAR(32) ,
    PRIMARY KEY(userno)
);

CREATE TABLE IF NOT EXISTS keyword(
    userno INT UNSIGNED INCREMENT PRIMARY KEY,
    jsonstr VARCHAR(8192),
    FOREIGN KEY(userno) REFERENCES user(userno)
);