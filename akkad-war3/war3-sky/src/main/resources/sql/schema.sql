drop table if exists tb_locations;
CREATE TABLE tb_locations(
  id INTEGER not null primary key,
  flag varchar(6),
  local_code varchar(30),
  local_name varchar(100),
  lv INTEGER,
  sup_local_code varchar(30),
  url varchar(60)
);
