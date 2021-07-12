CREATE TABLE tb_locations (
  id bigint(20) NOT NULL,
  flag varchar(6) DEFAULT NULL,
  local_code varchar(30) DEFAULT NULL,
  local_name varchar(100) DEFAULT NULL,
  lv int(11) DEFAULT NULL,
  sup_local_code varchar(30) DEFAULT NULL,
  url varchar(60) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;