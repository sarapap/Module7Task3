drop table if exists CurrencyObjects;
drop table if exists Transaction;
create table CurrencyObjects (id integer not null auto_increment, abbreviation varchar(255), currency_name varchar(255), rate float(53), primary key (id)) engine=InnoDB;
create table Transaction (id integer not null auto_increment, amount float(53), fromCurrency varchar(255), toCurrency varchar(255), primary key (id)) engine=InnoDB;
drop table if exists CurrencyObjects;
drop table if exists Transaction;
create table CurrencyObjects (id integer not null auto_increment, abbreviation varchar(255), currency_name varchar(255), rate float(53), primary key (id)) engine=InnoDB;
create table Transaction (id integer not null auto_increment, amount float(53), fromCurrency varchar(255), toCurrency varchar(255), primary key (id)) engine=InnoDB;
drop table if exists CurrencyObjects;
drop table if exists Transaction;
create table CurrencyObjects (id integer not null auto_increment, abbreviation varchar(255), currency_name varchar(255), rate float(53), primary key (id)) engine=InnoDB;
create table Transaction (id integer not null auto_increment, amount float(53), fromCurrency varchar(255), toCurrency varchar(255), primary key (id)) engine=InnoDB;