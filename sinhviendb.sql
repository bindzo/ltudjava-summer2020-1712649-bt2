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
	STT int NOT NULL AUTO_INCREMENT,
    MAMON varchar(20) not null,
	MSSV bigint not null,
    MALOP varchar(20) not null,
	primary key(STT)
);
ALTER TABLE SINHVIEN ADD FOREIGN KEY(MALOP) REFERENCES LOP(MALOP);
ALTER TABLE MONHOC ADD foreign key(MALOP) references LOP(MALOP);
ALTER TABLE MONHOC_LOP ADD foreign key(MALOP) references LOP(MALOP);
ALTER TABLE MONHOC_LOP ADD foreign key(MAMON) references MONHOC(MAMON);
ALTER TABLE MONHOC_LOP ADD foreign key(MSSV) references SINHVIEN(MSSV);

select * from monhoc_lop;
select * from lop;
select * from monhoc;
select * from sinhvien;
DROP database sinhviendb;
DELETE FROM monhoc_lop;
delete FROM lop;
SET SQL_SAFE_UPDATES = 0;
ALTER TABLE MONHOC_LOP AUTO_INCREMENT = 1;
