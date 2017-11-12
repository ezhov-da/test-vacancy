drop table TEST;

create table TEST 
(
    FIELD bigint
);

create unique index if not exists field on TEST(FIELD);

select * from TEST;