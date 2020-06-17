CREATE DATABASE sinhviendb;
use sinhviendb;
create table SINHVIEN (
   MSSV bigint not null,
   TEN varchar(50),
   GIOITINH varchar(10),
   CMND bigint,
   MALOP varchar(20),
	USERNAME varchar(50),
   PASS varchar(50),
   primary key (MSSV),
   unique (MSSV)
);
create table MONHOC (
   MAMON varchar(20) not null,
   TEN varchar(50),
   PHONG varchar(10),
	MALOP varchar(20),
   primary key (MAMON),
   unique (MAMON)
);
create table LOP (
   MALOP varchar(20) not null,
   primary key (MALOP),
   unique (MALOP)
);
create table MONHOC_LOP(
	MALOP varchar(20) not null,
    MAMON varchar(20) not null,
	MSSV bigint not null,

);
ALTER TABLE SINHVIEN ADD FOREIGN KEY(MALOP) REFERENCES LOP(MALOP);
ALTER TABLE MONHOC ADD foreign key(MALOP) references LOP(MALOP);
select * from lop;
DROP database sinhviendb;
DELETE FROM sinhvien;
delete FROM lop;
SET SQL_SAFE_UPDATES = 0;