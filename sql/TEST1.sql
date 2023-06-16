--	회원(user1)							
--	연번	컬럼참조	컬렁명	타입	크기	제약조건	기타	
--	1	아이디	id	varchar2	20	primary key		
--	2	비밀번호	pw	varchar2	300	no null		
--	3	회원명	name	varchar2	20			
--	4	연락처	tel	varchar2	13			
--	5	주소	addr	varchar2	300			
--	6	이메일	email	varchar2	50		

create table user1(id varchar2(20) primary key, pw varchar2(300) not null, name varchar2(20), tel varchar2(13), addr varchar2(300), email varchar2(50));
desc user1;
drop table user1;

--	상품(product)							
--	연번	컬럼참조	컬렁명	타입	크기	제약조건	기타	
--	1	상품코드	pcode	varchar2	10	primary key		
--	2	상품명	pname	varchar2	20	not null	
--	3	상품규격(총기장)	psize	number	10
--	4	단가	price	varchar2	10	
--	5	상품설명	pcontent	varchar2	300	
--	6	수량	pcount	number	20	

create table product(pcode varchar2(10) primary key, pname varchar2(20) not null, psize number(10), price varchar2(10), pcontent varchar2(300), pcount number(20) default 1);
desc product;

drop table product;

--주문(order1)					
--연번	컬럼참조	컬럼명	타입	크기	제약조건
--1	주문번호	ocode	varchar2	20	primary key
--2	아이디	id	varchar2	20	not null
--3	상품코드	pcode	varchar2	10	not null
--4	수량	amount	number	20	 1
--5	구입가	oprice	varchar2	20	
--6 배송일 odate date 오늘날짜
--7	배송중	ostate	varchar2	50  배송중	
--8	연락처	tel	varchar2	13	
--9	택배사	dname	varchar2	20
--10	도착지 주소	addr	varchar2	300	
--11	화물코드	dcode	varchar2	50

create table order1( ocode varchar2(20) primary key, id varchar2(20) not null, pcode varchar2(10) not null, amount number(20) default 1, oprice varchar2(50), odate date default sysdate, ostate varchar2(50) default '배송중', tel varchar2(13), dname varchar2(20), addr varchar2(300), dcode varchar2(50));
desc order1;
drop table order1;

alter table order1 add constraint pk1 foreign key(id) references user1(id);
alter table order1 add constraint pk2 foreign key(pcode) references product(pcode);
					
--장바구니(basket)					
--연번	컬럼참조	컬럼명	타입	크기	제약조건
--1	순번	bn	varchar2	20	primary key
--2	아이디	id	varchar2	50	not null
--3	상품코드	pcode	varchar2	30	not null
--4	수량	bcount	number	10	

create table basket (bn varchar2(20) primary key, id varchar2(50) not null, pcode varchar2(30) not null, bcount number(10) default 1);
desc basket;
drop table basket;


alter table basket add constraint pk3 foreign key(id) references user1(id);
alter table basket add constraint pk4 foreign key(pcode) references product(pcode);
					
--결제(pay)					
--연번	컬럼참조	컬럼명	타입	크기	제약조건
--1	결제번호	yn	varchar2	20	primary key
--2	아이디	id	varchar2	10	not null
--3	주문번호	ocode	varchar2	20	not null
--4	결제수단	ypay	varchar2	20	
--5	결제 수단 번호	pnumber	varchar2	50	
--6	결제금액	yprice	varchar2	20
--7	결제일	ydate	date	

create table pay(yn varchar2(20) primary key, id varchar2(10) not null, ocode varchar2(20) not null, ypay varchar2(20), pnumber varchar2(50), yprice varchar2(20), ydate date);
desc pay;
drop table pay;

alter table pay add constraint pk5 foreign key(id) references user1(id);
alter table pay add constraint pk6 foreign key(ocode) references order1(ocode);

					
--이용후기(latter)					
--연번	컬럼참조	컬럼명	타입	크기	제약조건
--1	글번호	ln	varchar2	20	primary key
--2	아이디	id	varchar2	20	not null
--3	주문번호	ocode	varchar2	20	not null
--4	작성일	ldate	date	
--5	이용후기	llatter	varchar2	500	
--6	별점	lstar	number	4	

create table latter (ln varchar2(20) primary key, id varchar2(20) not null, ocode varchar2(20) not null, ldate date, llatter varchar2(500), lstar number(4));
desc latter;

drop table latter;
					
alter table latter add constraint pk7 foreign key(id) references user1(id);
alter table latter add constraint pk8 foreign key(ocode) references order1(ocode);


commit;
					
					

					


								

								
