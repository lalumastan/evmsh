drop table if exists vaccine_type;
create table vaccine_type (
name varchar(30) not null primary key,
description varchar(256) not null,
created_date datetime not null default '2021-11-19 00:00:00',
created_by varchar(100) not null,
last_updated_date datetime not null default '2021-11-19 00:00:00',
last_updated_by varchar(100) not null);

insert into vaccine_type values
('COVID19',	'COVID19 - It Typically Requires 2 Shots', '2021-11-19 00:00:00', 'Mohammed Monirul Islam', '2021-11-19 00:00:00', 'Mohammed Monirul Islam'),
('EBOLA', 	'EBOLA - It Typically Requires 2 Shots', '2021-11-19 00:00:00', 'Mohammed Monirul Islam', '2021-11-19 00:00:00', 'Mohammed Monirul Islam'),
('FLU',	'FLU - It Typically Requires 1 Shot', '2021-11-19 00:00:00', 'Mohammed Monirul Islam', '2021-11-19 00:00:00', 'Mohammed Monirul Islam');

drop table if exists users;
create table users (
  user_id int(11) not null auto_increment primary key,
  username varchar(45) not null,
  password varchar(64) not null,
  role varchar(45) not null,
  enabled tinyint(4) default null,
  created_date datetime not null default '2021-11-19 00:00:00',
  created_by varchar(100) not null,
  last_updated_date datetime not null default '2021-11-19 00:00:00',
  last_updated_by varchar(100) not null
);

-- admin aPassword
-- user Password
insert into users (username, password, role, enabled, created_date, created_by, last_updated_date, last_updated_by) values
('user', '$2a$12$FZscIOfSqOD5YxgpFh.NaeRRjI60YeWwPdIG60Ylh8iiE1GYihxgu', 'ROLE_USER', 1, '2021-11-19 00:00:00', 'Mohammed Monirul Islam', '2021-11-19 00:00:00', 'Mohammed Monirul Islam'),
('admin', '$2a$12$IrkgrllQf4sz7exSvOP71OIV0E9xlZV9Ymc3/GC8XlgzZ7v80VLcy', 'ROLE_ADMIN', 1, '2021-11-19 00:00:00', 'Mohammed Monirul Islam', '2021-11-19 00:00:00', 'Mohammed Monirul Islam');