-- 변수 선언(Variable Declare) : 블록 내에서 변수를 사용하려면 선언부에서 먼저 정의를 해야 한다.
-- DECLARE 변수이름 데이터타입[:=값]

SET serveroutput ON  

DECLARE
    VNAME VARCHAR2(30) := '전재영';
    AGE NUMBER(2) := 25;
    GENDER VARCHAR2(20) DEFAULT '여';
BEGIN
    DBMS_OUTPUT.put_line('이름:'||VNAME||' 나이:'||AGE||' 성별:'||GENDER);
END;
/

-- 타입의 임시적 선언방법 : %ROWTYPE, %TYPE    
-- %ROWTYPE
DECLARE
    DT USER1%ROWTYPE;
BEGIN
    SELECT * INTO DT
    FROM USER1;
    DBMS_OUTPUT.put_line(DT.ID||DT.NAME||DT.TEL);
END;
/

-- %TYPE    
DECLARE
    VID USER1.ID%TYPE;
    VNAME USER1.NAME%TYPE;
    VTEL USER1.TEL%TYPE;
BEGIN
    SELECT ID,NAME,TEL INTO VID,VNAME,VTEL
    FROM USER1;
    DBMS_OUTPUT.put_line(VID||VNAME||VTEL);
END;
/

 /* CREATE OR REPLACE PROCEDURE update_point(vid IN test1.id%TYPE, vpoint IN test1.point%TYPE)
    IS
    BEGIN
        UPDATE test1 SET point = vpoint WHERE id=vid;
        commit;
    END update_point;
/   */


-- 회원 탈퇴 프로시저 : withdraw_mem_pro
-- 회원 아이디를 입력받아 해당 회원이 탈퇴되는 프로시저를 생성하고, 특정 회원을 탈퇴되도록 구현할 것
CREATE OR REPLACE PROCEDURE withdraw_mem_pro(vid IN user1.id%TYPE)
    IS
    BEGIN
        DELETE FROM user1 WHERE id=vid;
        commit;
    END withdraw_mem_pro;
/    

EXECUTE withdraw_mem_pro('dodo');
select * from user1;


-- 회원 가입 프로시저 : join_mem_pro
-- user1 테이블에 대하여 아래와 같이 작업할 것
-- 회원 아이디, 비밀번호, 회원명, 전화번호, 주소, 이메일 등을 입력받아 회원이 가입되는 프로시저를 생성하고, 특정 회원이 가입되도록 구현함
select * from user1;

CREATE OR REPLACE PROCEDURE join_mem_pro(vid IN user1.id%TYPE, vpw IN user1.pw%TYPE, vname IN user1.name%TYPE, vtel IN user1.tel%TYPE, vaddr IN user1.addr%TYPE, vemail IN user1.email%TYPE)
    IS 
    BEGIN
        INSERT INTO user1 VALUES(vid, vpw, vname, vtel, vaddr, vemail);
        DBMS_OUTPUT.put_line(vid||'계정이 추가되었습니다.');
        commit;
    END join_mem_pro;
/    

EXECUTE join_mem_pro('young', '1234', '관리자', '010-1234-1234', '경기도 고양시 덕양구', 'young@naver.com');
EXECUTE join_mem_pro('koung', '1111', '김도도', '010-1111-1111', '경기도 고양시 일산동구', 'koung@hanmail.net');
EXECUTE join_mem_pro('aound', '2222', '이레레', '010-2222-2222', '경기도 파주시 문산읍', 'aoung@gamil.com');
EXECUTE join_mem_pro('boung', '3333', '박미미', '010-3333-3333', '경기도 김포시 풍진읍', 'boung@naver.com');
EXECUTE join_mem_pro('coung', '4444', '강솔솔', '010-4444-4444', '경기도 파주시 월롱면', 'coung@gmail.com');
EXECUTE join_mem_pro('doung', '5555', '임라라', '010-5555-5555', '경기도 고양시 일산서구', 'doung@naver.com');

-- 상품 이미지 필드 추가
alter table product add pic1 varchar2(200);
alter table product add pic2 varchar2(200);
alter table product add pic3 varchar2(200);

select * from product;

-- 상품 등록 프로시저 : prd_ins_pro
-- user1 테이블에 대하여 아래와 같이 작업할 것
-- 상품코드, 상품명, 규격, 단가, 상품설명, 수량, 상품대표이미지1, 상품상세이미지2, 상품상세이미지3 등을 입력받아 상품을 등록하는
-- 프로시저를 생성하고, 그 프로시저를 구형하여 5개 이상의 상품을 등록할 것

desc product;
-- alter table 테이블명 modify(컬럼명 타입(크기));
alter table product modify(pname varchar2(100));

CREATE OR REPLACE PROCEDURE prd_ins_pro(vpcode IN product.pcode%TYPE, vpname IN product.pname%TYPE, vpsize IN product.psize%TYPE, vprice IN product.price%TYPE, vpcontent IN product.pcontent%TYPE, vpcount IN product.pcount%TYPE, vpic1 IN product.pic1%TYPE, vpic2 IN product.pic2%TYPE, vpic3 IN product.pic3%TYPE)
    IS
    BEGIN
        INSERT INTO product VALUES(vpcode, vpname, vpsize,vprice, vpcontent, vpcount, vpic1, vpic2, vpic3);
        DBMS_OUTPUT.put_line(vpcode||'상품이 추가되었습니다.');
        commit;
    END prd_ins_pro;
/    

EXECUTE prd_ins_pro('4192', '플레인 여리핏 썸머가디건', '55', '28400', '손에 꼽게 여리여리한 핏과 시원한 소재로 슬리브리스와 찰떡처럼 어울릴 아우터', '10', '', '', '');
EXECUTE prd_ins_pro('6186', '프리미엄 데일리 무지티셔츠', '60', '15900', '하나하나 소장가치 있는 일곱가지 색감에 이중지 원단으로 잦은 세탁에도 탄탄하게!', '12', '', '', '');
EXECUTE prd_ins_pro('5072', '쫀쫀베이직 슬리브리스(컴퓨터 블라인)', '53', '13900', '노출 전혀 없이 속옷라인을 싹 덮어주면서 비침 없이 쫀쫀한 기본템 슬리브리스', '8', '', '', '');
EXECUTE prd_ins_pro('1352', '썸머데이 루즈니트탑', '62', '14900', '여리여리하고 루즈한 핏감에 목늘어짐은 덜고 바람이 솔솔 통하는 소재로 제작했어요', '13', '', '', '');
EXECUTE prd_ins_pro('8138', '탄탄핏 세미크롭 반팔티셔츠', '52', '15900', '아방하고 센스있는 기장의 세미크롭에 쫀쫀하고 파우더리한 소재', '5', '', '', '');
EXECUTE prd_ins_pro('8162', '밤부소프트 여리핏 반팔니트', '53', '19800', '담백한 터치감과 여리여리한 핏은 그대로 가볍고 시원한 하프슬리스리스 타입', '6', '', '', '');

select * from product;



-- 함수명 : update_mem_fnc;
-- 특정 아이디를 가진 회원의 정보를 입력된 값으로 변경하고, 그 입력된 정보를 출력하도록 하는 함수를 생성하고, 구현할 것.
desc user1;

CREATE OR REPLACE FUNCTION update_mem_fnc(vid IN VARCHAR2, pw IN VARCHAR2, v2 IN VARCHAR2)
    RETURN NUMBER
    IS
    vcnt NUMBER := 0;
    BEGIN
        IF sw='pw' THEN
            UPDATE user1 SET pw = v2 WHERE id = vid;
       ELSIF sw='name' THEN
            UPDATE user1 SET name = v2 WHERE id = vid;
       ELSIF sw='tel' THEN
            UPDATE user1 SET tel = v2 WHERE id = vid;
        ELSIF sw='addr' THEN
            UPDATE user1 SET addr = v2 WHERE id = vid;
        ELSIF sw='email' THEN
            UPDATE user1 SET email = v2 WHERE id = vid;
        ELSE
            DBMS_OUTPUT.put_line('변경된 값이 없음');
        END IF; 
        COMMIT;
        DBMS_OUTPUT.put_line('변경된 정보:'||sw||' '||vid||' '||v2);
        RETURN vcnt;
/
VAR cnt NUMBER;
EXECUTE :cnt := update_mem_fnc();


select * from user1;

CREATE OR REPLACE TYPE USER_TYPE AS OBJECT (     -- 테이블 타입 객체 정의
    ID VARCHAR2(20), PW VARCHAR2(300), NAME VARCHAR2(20),
    TEL VARCHAR2(13), ADDR VARCHAR2(300), EMAIL VARCHAR2(50));
    
CREATE OR REPLACE TYPE MEM_TBL AS TABLE OF USER_TYPE;    -- 테이블 타입 객체 생성

CREATE OR REPLACE FUNCTION get_mem_info(vid IN VARCHAR2)  -- 타입 객체로 회원정보를 반환하는 함수 생성
    RETURN MEM_TBL
    IS tb MEM_TBL;
    BEGIN
        SELECT USER_TYPE(id, pw, name, tel, addr, email)
        BULK COLLECT INTO tb
        FROM user1 WHERE id = vid;
        RETURN tb;
    END get_mem_info;
/
8
-- 회원 정보를 요청하여 출력하기
select * from table(get_mem_info('young'));
select * from table(get_mem_info('aoung'));

-- user1(회원) 테이블에 기본값을 0으로 하는 컬럼 point를 추가하여라
alter table user1 add point number default 0;

--user1(회원) 테이블에 기본값을 0으로 하는 컬럼 vsited을 추가하여라
alter table user1 add visited number default 0;
select * from user1;

-- 함수 이름: user_pointup_fnc
-- user1(회원)테이블의 데이터 중에서 요청한 아이디에 대하여 로그인 횟수(visited)을 1씩 증가 시키고,
-- 포인트(point)가 5점씩 증가 되도록 연쇄동작 함수를 작성하고, 실행하시오.

CREATE OR REPLACE FUNCTION user_pointup_fnc(vid IN user1.id%TYPE)
    RETURN NUMBER
    IS
        up_data number := 5;
    BEGIN
        update user1 set visited=visited+1, point=point+up_data where id = vid;
    COMMIT;
    RETURN up_data;
END user_pointup_fnc;
/

variable pt number;
execute :pt := user_pointup_fnc('doung');
select * from user1;
    
-- 함수명 : basket_ins_fnc
-- basket(장바구니)에 데이터가 추가되는 함수를 생성하고, 설명하시오.
-- 아래와 같이 첫 번째 장바구니건은 sql문의 insert문으로 데이터를 직접 입력한 후
-- 3건 이상의 함수를 실행하여 추가할 것
-- insert into basket values('000001', 'lee', '6186', '1');
delete from basket where id='koung';

select * from basket;
desc basket;
CREATE OR REPLACE FUNCTION basket_ins_fnc(vid IN basket.id%TYPE, vpcode IN basket.pcode%TYPE, vbcount IN basket.bcount%TYPE)
    RETURN number
    IS
        datal number := 0;
    BEGIN
        select TO_NUMBER(bn) into datal from basket order by bn desc;
        datal := datal + 1;
        insert into basket values(TO_CHAR(datal, '00000'), vid, vpcode, vbcount);
    COMMIT;
    DBMS_OUTPUT.PUT_LINE(TO_CHAR(datal, '00000') || '가 추가되었습니다.');
    RETURN datal;
    END basket_ins_fnc;
/    

insert into basket values('10001', 'doung', '6186', 1);
variable cnt number;
execute :cnt := basket_ins_fnc('koung','1352',2 );
variable cnt number;
execute :cnt := basket_ins_fnc('aoung','5072',1 );
variable cnt number;
execute :cnt := basket_ins_fnc('coung','8162',3 );
    

-- select_addr_fnc
-- user1 테이블에서 addr(주소)가 입력된 값이 포함된 회원의 이름과 전화번호, 주소를 출력하는 함수를 생성하고, 실행하시오.
desc user1;

CREATE OR REPLACE FUNCTION select_addr_fnc(kdata in user1.addr%TYPE)
RETURN varchar2
IS
    vname user1.name%TYPE;
BEGIN
    FOR i in (select * from user1 where addr like '%'||kdata||'%') LOOP
            DBMS_OUTPUT.put_line(i.name||' '||i.tel||' '||i.addr);
    END LOOP;
    RETURN vname;
END;
/

variable vname varchar2(20);
execute :vname := select_addr_fnc('고양');
select * from user1;

-- 함수이름은 : user_rank
-- user1 테이블에서 visited 컬럼별로 회원등급을 구하여 고객아이디, 고객이름, 등급을
-- 출력하는 함수를 작성하고, 실행하되, IF문, 반복실행문(WHILE, LOOF, FOR)을 사용하여, 함수를 작성하고, 실행하되
-- 회원등급: 10회 이상 방문시 우수회원, 5회 이상 방문시 정회원, 5회 미만 방문시 준회원으로 하며,
-- 회원등급의 컬럼은 방문횟수인 visited을 활용할 것

CREATE OR REPLACE FUNCTION user_rank_fnc
RETURN number
IS
    i number := 0;
    rank varchar2(20) := '';
BEGIN
    DBMS_OUTPUT.ENABLE;
    DBMS_OUTPUT.PUT_LINE('아이디'||' '||'고객명'||' '||'등급');
    FOR r IN (SELECT id, name, visited FROM user1) LOOP
        i := i + 1;
        IF(r.visited >= 10) THEN rank := '우수회원';
        ELSIF(r.visited >= 5) THEN rank := '정회원';
        ELSE rank := '준회원';
        END IF;
        DBMS_OUTPUT.PUT_LINE(r.id ||' '|| r.name || ' ' || rank);
        rank := '';
    END LOOP;
    RETURN i;
END;
/

variable rwcnt number;
execute :rwcnt := user_rank_fnc;
    
    
    
-- 문제13
-- 함수이름은 : user_basket_fnc
-- 회원 아이디(id)를 입력받아 해당 장바구니의 정보를 고객명, 연락처, 상품명, 수량, 결제할금액을 출력하는 함수를 생성하고,
-- 실행하시오
select * from product;
select * from basket;
select * from user1;

CREATE OR REPLACE FUNCTION user_basket_fnc(vid in user1.id%TYPE)
RETURN number
IS
    i number := 0;
BEGIN
    DBMS_OUTPUT.ENABLE;
    DBMS_OUTPUT.PUT_LINE('고객명'||chr(9)||'연락처'||chr(9)||chr(9)||chr(9)||'상품명'||chr(9)||chr(9)||chr(9)||'수량');
    FOR r IN (SELECT user1.name as name, user1.tel as tel, product.pname as pname, basket.bcount as bcount,
    (product.price*basket.bcount) as money FROM basket, user1, product where
    basket.id=user1.id and basket.pcode=product.pcode and user1.id=vid) LOOP
        i := i + 1;
        DBMS_OUTPUT.PUT_LINE(r.name||chr(9)||r.tel||chr(9)||r.pname||chr(9)||r.bcount||chr(9)||r.money);
    END LOOP;
    RETURN i;
END;
/
variable rcnt number;
execute :rcnt := user_basket_fnc('koung');

-- 트리거

create table pro(tno number, pname varchar2(40), dan number);   --상품 테이블
INSERT INTO pro VALUES(100, '카스맥주', 1500);
INSERT INTO pro VALUES(200, '테라맥주', 1000);
INSERT INTO pro VALUES(300, '필라이트맥주', 2000);
INSERT INTO pro VALUES(400, '하이트맥주', 1800);
INSERT INTO pro VALUES(500, '클라우드맥주', 1600);

select * from pro;    

create table inventory(ino number, quant number, price number); --재고 테이블
create table wearing(ino number, quant number, price number); --입고 테이블
create table sales(ino number, quant number, price number); --판매 테이블


-- 문제1.
-- 트리거 이름: wearing_trigger
-- 입고(wearing) 테이블에 상품을 입고시킬 때 만약, 새로운 상품(insert)일 경우와
-- 이미 있는 재고 상품(update)일 경우로 분류하여
-- 재고(inventory) 테이블에   반영될 수 있도록 트리거를 작성하되,
-- 임의의 데이터를 입고 테이블에 튜플을 입력하여 자동 재고처리된 그 결과를 확인하시오. :NEW 새로 추가(갱신)되는 데이터
CREATE OR REPLACE TRIGGER wearing_trigger
AFTER INSERT ON wearing
FOR EACH ROW
DECLARE
    v_cnt number;
BEGIN
    --재고 테이블에서 먼저 해당 상품코드에 속하는 레코드 수 계산
    select count(*) into v_cnt from inventory where ino=:NEW.ino;
    --재고 테이블에 없으면, 새로운 레코드로 등록(insert)
    IF(v_cnt=0) THEN insert into inventory values (:NEW.ino, :NEW.quant, :NEW.price);
    --재고 테이블에 있으면, 해당 상품의 수량과 가격을 갱신(update)
    ELSE update inventory set quant=quant+:NEW.quant, price=price+:NEW.price where ino=:NEW.ino;
    END IF;
END;
/

select * from wearing;
select * from inventory;
insert into wearing values(200, 20, 2000);
insert into wearing values(200, 5, 2100);
insert into wearing values(200, 5, 900);    

-- 문제2.
-- 트리거 이름 : sales_trigger
-- 판매가 되면 판매(sales) 테이블에 상품이 등록된다. 그렇다면 재고(inventory) 테이블에 반영되어
-- 튜플이 변경되도록 트리거를 생성하고, 실행할 것.
-- 임의의 데이터를 판매 테이블에 튜플을 입력하여 자동 재고계산된 그 결과를 확인하시오.
CREATE OR REPLACE TRIGGER sales_trigger
AFTER INSERT ON sales
FOR EACH ROW
DECLARE
    v_cnt number;
BEGIN
    -- 해당 상품에 대한 판매 후 현제 재고량을 확인
    select quant-:NEW.quant into v_cnt from inventory where ino=:NEW.ino;
    -- 남은 재고량이 0보다 작으면, 재고 테이블에서 해당 상품 정보를 삭제
    IF(v_cnt<=0) THEN delete from inventory where ino=:NEW.ino;
    -- 남은 재고량이 있으면, 재고 테이블에서 해당 상품의 수량을 감산한다.
    ELSE update inventory set quant=quant-:NEW.quant, price=price-:NEW.price;
    END IF;
END;
/
select * from inventory;
insert into sales values(200, 3, 3000);
select * from inventory;
select * from sales;
    
-- 문제 3.
-- 트리거 이름: return_trigger
-- 반품이 되면 판매(sales) 테이블의 해당 판매 내역이 삭제된다. 그렇다면 재고(inventory) 테이블에 반영되어
-- 튜플이 변경되도록 트리거를 생성하고, 실행할 것.
-- 임의의 데이터를 판매 테이블에 튜플을 제거되어 자동 재고계산된 그 결과를 확인하시오.
CREATE OR REPLACE TRIGGER return_trigger
AFTER DELETE ON sales
REFERENCING OLD AS O NEW AS N
FOR EACH ROW
DECLARE
    v_cnt number;
BEGIN
    -- 재고 테이블에서 먼저 해당 상품코드에 속하는 레코드 수 계산
    select count(*) into v_cnt from inventory where ino=:O.ino;
    -- 반품된 상품이 재고 테이블에 없으면, 새로운 레코드로 등록(insert)
    IF(v_cnt=0) THEN insert into inventory values(:O.ino, :O.quant, :O.price);
    -- 반품된 상품이 재고 테이블에 있으면, 해당 상품의 수량과 가격을 갱신(update)
    ELSE update inventory set quant=quant+:O.quant, price=price+:O.price where ino=:O.ino;
    END IF;
END;
/

select * from inventory;
delete from sales where ino = 200;
select * from sales;
    
    
-- 문제4.
-- 재고를 반출하게 되면 입고(wearing) 테이블의 해당 상품 내역을 제거하면 재고(inventory)테이블에 반영되어
-- 튜플이 변경되도록 트리거를 생성하고,실행할 것
-- 임의의 데이터를 입고 테이블의 튜플을 제거하여 자동 재고계산된 그 결과를 확인하시오.
CREATE OR REPLACE TRIGGER rev_trigger
AFTER DELETE ON wearing
REFERENCING OLD AS O NEW AS N
FOR EACH ROW
DECLARE
    v_cnt number;
BEGIN
    -- 해당 상품에 대한 반출 후 현재 재고량을 확인
    select quant-:O.quant into v_cnt from inventory where ino=:O.ino;
    -- 반품된 상품이 0보다 작으면, 재고 테이블에서 해당 상품 정보를 삭제
    IF(v_cnt<=0) THEN delete from inventory where ino=:O.ino;
    -- 반품된 상품이 재고 테이블에 있으면, 해당 상품의 수량과 가격을 갱신(update)
    ELSE update inventory set quant=quant-:O.quant, price=price-:O.price where ino=:O.ino;
    END IF;
END;
/
select * from inventory;
select * from sales;
delete from sales where ino = 200;
    
    
    
    












