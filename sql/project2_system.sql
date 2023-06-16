--SQL의 단점
-- 1) 변수가 없다.
-- 2) 한 번에 하나의 명령문만 사용 가능하기 때문에 트래픽이 상대적으로 증가한다.
-- 3) 제어문이 사용 불가 (IF, LOOF)
-- 4) 예외처리가 없다.
-- 5) 기타 다른 언어처럼 알고리즘을 구현하기 힘들다. 기타 등등

-- PL/SQL : Procadural Language extension to SQL = SQL을 확장한 절차적 언어로서 알고리즘 구현이 가능한 언어

-- 기본 구조: 선언부(Declare), 실행부(Begin), 예외처리부(Exception), 종료부(End)
-- 각주 : -- 한 줄 각주, /* 여러 줄 각주 */
-- 작성된 PL/SQL을 실행하려면, /로 종결해야 실행된다
-- 기본 블록 종류 : 프로시저(Procedure), 함수(Function), 패키기(Package), 트리거(Triger)
-- 프로시저(Procedure) : 리턴 값을 하나 이상 갖는 하나의 프로그램 블록
-- 함수(Function) : 리턴 값과 매개변수를 갖는 하나의 프로그램 블록
-- 패키지(Package) : 하나 이상의 프로시저, 함수, 변수, 예외처리 구문을 묶은 블록
-- 트리거(Triger) : 지정된 이벤트 발생시 자동으로 실행되는 프로그램 블록

-- 기본 입출력문 : dbms_output.put_line(출력할내용);

/*
SET serveroutput ON
BEGIN
    dbms_output.put_line('Hello');
END;
/
*/

SET serveroutput ON     /*내용이 출력이 되려면 한번 실행해야함*/

BEGIN
    dbms_output.put_line('Hello');
END;
/

-- 변수 선언(Variable Declare) : 블록 내에서 변수를 사용하려면 선언부에서 먼저 정의를 해야 한다.
-- DECLARE 변수이름 데이터타입[:=값]

/*
DECLARE VNAME VARCHAR2(20);
    VNAME := '전재영'; 

DECLARE VNAME VARCHAR2(20) := '전재영';

DECLARE VNAME VARCHAR2(20) DEFAULT '전재영';

DECLARE
    VNAME VARCHAR2(30);
    AGE NUMBER(2);
    GENDER VARCHAR2(20) DEFAULT '여';
*/

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

-- 반복문: FOR LOOP, LOOP, WHILE LOOP
-- FOR LOOF
/*
FOR index in [REVERSE] 시작값 .. END값 LOOP
    STATEMENT 1
    STATEMENT 2
    ...
    
END LOOP;    
*/

BEGIN
    FOR i in 1..5 LOOP
        DBMS_OUTPUT.put_line(i);
        IF mod(i, 2) = 0 THEN
            DBMS_OUTPUT.put_line(i||'는 짝수값');
        ELSE    
            DBMS_OUTPUT.put_line(i||'는 홀수값');
        END IF;    
    END LOOP;
END;
/

DECLARE
    v_num NUMBER := 0;   --카운트 변수
    v_tot_num NUMBER := 0;      --합산 결과 변수
BEGIN
    LOOP
        v_num := v_num + 1;  -- 1
        DBMS_OUTPUT.PUT_LINE('현재 숫자 :' || v_num);
        v_tot_num := v_tot_num + v_num;
        EXIT WHEN v_num >=10;   -- 반복구간을 벗어나기 위한 조건
     END LOOP;
     DBMS_OUTPUT.PUT_LINE('합산 결과 : ' || v_tot_num);
END;
/


DECLARE
    v_num NUMBER :=0; --시작숫자
    v_tot_num NUMBER := 0;  --총 loop수 반환 변수
BEGIN
    WHILE v_num < 101 LOOP      --조건이 만족하는 동안 실행
        v_num := v_num + 1;
        IF mod(v_num,2)=0  THEN
            v_tot_num := v_tot_num + v_num;
        END IF;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('짝수 합산 결과: ' || v_tot_num);
END;
/


DECLARE
    score NUMBER :=88;
BEGIN    
    IF score >= 90 THEN
        DBMS_OUTPUT.PUT_LINE('점수 : ' || score || ', 등급 : A');
    ELSIF score >= 80 THEN
        DBMS_OUTPUT.PUT_LINE('점수 : ' || score || ', 등급 : B'); 
    ELSIF score >= 70 THEN
        DBMS_OUTPUT.PUT_LINE('점수 : ' || score || ', 등급 : C'); 
    ELSIF score >= 60 THEN
        DBMS_OUTPUT.PUT_LINE('점수 : ' || score || ', 등급 : D');   
    ELSE
        DBMS_OUTPUT.PUT_LINE('점수 : ' || score || ', 등급 : F');
    END IF;
END;
/


DECLARE
    grade CHAR(1) := 'B';
    appraisal VARCHAR2(20);
BEGIN
    appraisal := CASE grade
        WHEN 'A' THEN 'Excellent'
        WHEN 'B' THEN 'Very GOOD'
        WHEN 'C' THEN 'Good'
        ELSE 'No such grade'
     END;
    DBMS_OUTPUT.PUT_LINE ('등급 : ' || grade);
    DBMS_OUTPUT.PUT_LINE ('피드백 : ' || appraisal);
END;
/


select * from tbl_member_202201;

DECLARE
 BEGIN
    UPDATE tbl_member_202201 SET REGIST_DATE='20230101' WHERE address like '서울시%';
    DBMS_OUTPUT.PUT_LINE('처리 건수 : ' || TO_CHAR(SQL%ROWCOUNT)|| '건');
END;
/


DECLARE
    CURSOR mem_cur
    IS
    SELECT * FROM tbl_member_202201;
    mem_rec tbl_member_202201%ROWTYPE;
BEGIN
    OPEN mem_cur;
    LOOP
        FETCH mem_cur INTO mem_rec; --하나의 레코드 단위로 인출
        EXIT WHEN mem_cur%NOTFOUND; --더 이상 레코드가 없는 경우 종료(참이면 종료)
        DBMS_OUTPUT.put_line(mem_rec.c_no||' '||mem_rec.c_name||' '||mem_rec.phone);
    END LOOP;
    CLOSE mem_cur;
END;
/

alter table test1 add naem varchar(20) default '이무계';
alter table test1 add point number default 0;
alter table test1 add tel varchar2(13) default '010-0000-0000';
select * from test1;

-- alter table 테이블명 rename column 원래컬럼명 to 바꿀컬럼명;
alter table test1 rename column naem to name;
alter table test1 rename column addr to tel;

-- 이름변경 프로시저
CREATE OR REPLACE PROCEDURE update_name(vid IN test1.id%TYPE, vname IN test1.name%TYPE) 
    IS
    BEGIN
        UPDATE test1 SET name = vname WHERE id=vid;
        commit;
    END update_name;
/

EXECUTE update_name('kim', '도도도');
EXECUTE update_name('young', '레레레');
EXECUTE update_name('jeon', '미미미');
select * from test1;

-- 포인트 변경 프로시저 : update_point
-- 특정 아이디를 가진 회원에게 입력된 포인트로 변경되는 프로시저 만들고, 해당 프로시저를 실행하여 포인트를 변경할 것
CREATE OR REPLACE PROCEDURE update_point(vid IN test1.id%TYPE, vpoint IN test1.point%TYPE)
    IS
    BEGIN
        UPDATE test1 SET point = vpoint WHERE id=vid;
        commit;
    END update_point;
/    

EXECUTE update_point('kim','1');
EXECUTE update_point('young','2');
EXECUTE update_point('jeon','3');
EXECUTE update_point('mimi','4');
select * from test1;

-- 전화번호 변경 프로시저 : update_tel
-- 특정 아이디를 가진 회원에게 입력된 전화번호로 변경되는 프로시저 만들고, 해당 프로시저를  실행하여 전화번호를 변경할 것

CREATE OR REPLACE PROCEDURE update_tel(vid IN test1.id%TYPE, vtel IN test1.tel%TYPE)
    IS
    BEGIN
        UPDATE test1 SET tel = vtel WHERE id=vid;
        commit;
    END update_tel;
/

EXECUTE update_tel('kim', '010-1111-1111');
EXECUTE update_tel('young', '010-2222-2222');
EXECUTE update_tel('jeon', '010-3333-3333');
EXECUTE update_tel('mimi', '010-4444-4444');
select * from test1;

-- 회원 탈퇴 프로시저 : withdraw_mem_pro
-- 회원 아이디를 입력받아 해당 회원이 탈퇴되는 프로시저를 생성하고, 특정 회원을 탈퇴되도록 구현할 것
CREATE OR REPLACE PROCEDURE withdraw_mem_pro(vid IN test1.id%TYPE)
    IS
    BEGIN
        DELETE FROM test1 WHERE id=vid;
        commit;
    END withdraw_mem_pro;
/    

EXECUTE withdraw_mem_pro('kim');
select * from test1;

-- 회원 가입 프로시저 : join_mem_pro
-- user1 테이블에 대하여 아래와 같이 작업할 것
-- 회원 아이디, 비밀번호, 회원명, 전화번호, 주소, 이메일 등을 입력받아 회원이 가입되는 프로시저를 생성하고, 특정 회원이 가입되도록 구현함
    













