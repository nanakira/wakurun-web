# --- !Ups

create table "STICKY" ("NAME" VARCHAR NOT NULL PRIMARY KEY,"CONTENT" VARCHAR NOT NULL);

# --- !Downs

drop table "STICKY";

