drop sequence event_seq;
drop table event;

drop sequence inquiry_seq;
drop table inquiry;

drop sequence review_seq;
drop table review;
drop sequence payment_seq;
drop table payment;
drop sequence pr_seq;
drop table product_reg;

drop sequence product_seq;
drop table product;
drop sequence color_seq;
drop table color;
drop table product_name;

drop table buyer;
drop table rank;
drop table member;







create table member
(
    id varchar2(10) primary key,
    pwd varchar2(16) not null,
    type char(1) check(type in('A','B'))
);
-- A : administrator
-- B : buyer

create table rank
(
    rank varchar2(7) primary key,
    minamount number(8),
    maxamount number(10)
);
insert into rank values('Welcome',0,100000);
insert into rank values('Silver',100000,200000);
insert into rank values('Gold',200000,500000);
insert into rank values('VIP',500000,1000000);
insert into rank values('VVIP',1000000,100000000);

create table buyer
(
    cid references member(id),
    phone char(11),
    addr varchar2(50),
    email varchar2(30),
    rank references rank(rank),
    status number(1),
    coin number(10),
    primary key(cid)
);
-- status 1 : 회원
-- status 2 : 탈퇴신청회원
-- status 3 : 탈퇴완료회원




create table product_name
(
    pcode varchar2(30) primary key,
    cname varchar2(20) not null,
    pname varchar2(50) not null,
    price number(10)
);

create table color
(
    colnum number(10) primary key,
    pcode references product_name(pcode),
    color varchar2(15),
    orgfilename varchar2(50),
    savefilename varchar2(50),
    filesize number(30)
);
create sequence color_seq;


create table product
(
    pnum number(10) primary key,
    colnum references color(colnum),
    psize char(1) check(psize in('S','M','L')),
    icnt number(10)
);
create sequence product_seq;


create table product_reg
(
    regnum number(10) primary key,
    pnum references product(pnum),
    cnt number(10),
    regdate date
);
create sequence pr_seq;



create table payment
(
    paynum number(10) primary key,
    bid references buyer(cid),
    pnum references product(pnum),
    cnt number(10) not null,
    paydate date not null,
    status number(1) default 1
);
create sequence payment_seq;
-- 1 : 배송준비
-- 2 : 배송중
-- 3 : 배송완료
-- 4 : 구매확정
-- 5 : 배송전취소
-- 6 : 환불요청
-- 7 : 환불완료


create table review
(
    rnum number(10) primary key,
    paynum references payment(paynum),
    content varchar2(500),
    rpoint number(1),
    regdate date
);
create sequence review_seq;





create table inquiry
(
    inum number(10) primary key,
    cid references buyer(cid),
    pnum references product(pnum),
    title varchar2(50),
    content varchar2(500),
    ref number(10),
    lev number(10),
    step number(10),
    regdate date
);
create sequence inquiry_seq;





create table event
(
    enum number(10) primary key,
    title varchar2(50),
    content varchar2(500),
    orgfilename varchar2(50),
    savefilename varchar2(50),
    filesize number(30)
);
create sequence event_seq;



