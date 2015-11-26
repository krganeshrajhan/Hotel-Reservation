drop table ROOMTYPE if exists;
drop table ROOM if exists;
drop table TRANSACTION if exists;

create table ROOMTYPE (ID integer identity primary key, NAME varchar(20), CAPACITY integer, unique(CAPACITY));
create table ROOM (ID integer identity primary key, FLOORNO integer, ROOMTYPEID integer, RESERVED  boolean default false not null);
create table TRANSACTION (ID integer identity primary key, ROOMID integer, TRANSACTIONDATE date);


alter table ROOM add constraint FK_ROOM_ROOMTYPE foreign key (ROOMTYPEID)
references ROOMTYPE(ID);
alter table TRANSACTION add constraint FK_TRANSACTION_ROOM foreign key (ROOMID)
references ROOM(ID);