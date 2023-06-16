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

EXECUTE withdraw_mem_pro('young');
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

EXECUTE join_mem_pro('young', '1234', '재영', '010-1111-1111', '고양시 일산서구', 'young@naver.com');
EXECUTE join_mem_pro('minji', '1111', '민지', '010-2222-2222', '고양시 일산동구', 'minji@hanmail.net');
EXECUTE join_mem_pro('saebin', '2222', '세빈', '010-3333-3333', '파주시 금빛로', 'saebin@gmail.com');
EXECUTE join_mem_pro('koung', '3333', '경희', '010-4444-4444', '파주시 시청로', 'koung@naver.com');
EXECUTE join_mem_pro('dodo', '4444', '도도', '010-5555-5555', '파주시 문화로', 'dodo@gamil.com');

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

