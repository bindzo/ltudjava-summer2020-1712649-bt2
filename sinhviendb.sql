CREATE DATABASE sinhviendb;
use sinhviendb;
create table SINHVIEN (
   MSSV bigint not null,
   TEN varchar(50),
   GIOITINH varchar(10),
   CMND bigint,
   LOP varchar(20),
	USERNAME varchar(50),
   PASS varchar(50),
   primary key (MSSV),
   unique (MSSV)
);
create table MONHOC (
   MAMON varchar(20) not null,
   TEN varchar(50),
   PHONG varchar(10),
   primary key (MAMON),
   unique (MAMON)
);
create table LOP (
   MALOP varchar(20) not null,
   CMND int,
   primary key (MALOP),
   unique (MALOP)
);
create table GIANGKHOA (
   MALOP varchar(20) not null ,
   MAMON varchar(20) not null,
   PRIMARY KEY(MALOP,MAMON)
);
ALTER TABLE SINHVIEN ADD FOREIGN KEY(LOP) REFERENCES LOP(MALOP);
ALTER TABLE GIANGKHOA ADD foreign key(MALOP) references LOP(MALOP);
 ALTER TABLE GIANGKHOA ADD foreign key(MAMON) references MONHOC(MAMON);
select * from sinhvien;
DROP database sinhviendb;
DELETE FROM sinhvien;
SET SQL_SAFE_UPDATES = 0;