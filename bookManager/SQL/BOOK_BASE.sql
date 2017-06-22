CREATE TABLE BOOK(
BOOK_ID   number CONSTRAINT PK_BKID primary key, -- //seq_bid 시퀀스로 번호 발생 처리
TITLE     varchar2(50) CONSTRAINT NN_BTITLE not null,	-- //책제목
AUTHOR    varchar2(20) CONSTRAINT NN_BAUTHOR not null,	-- //저자명
PUBLISHER  varchar2(20) CONSTRAINT NN_BPUBLISHER not null,  --   //출판사
PUBLISH_DATE    date CONSTRAINT NN_PDATE not null, -- //출판날짜
PRICE      NUMBER CONSTRAINT NN_BPRICE not null	-- //가격
);

CREATE SEQUENCE SEQ_BID
 START WITH 1
 INCREMENT BY 1
 NOCYCLE
 NOCACHE;

INSERT INTO BOOK VALUES(SEQ_BID.NEXTVAL,'천년의 왕국 신라', '김기흥', '비평문화', TO_DATE('14-04-2000', 'DD-MM-YYYY'),12000);
INSERT INTO BOOK VALUES(SEQ_BID.NEXTVAL,'봄비 한 주머니', '문태준', '창작과 비평', TO_DATE('04-04-2000', 'DD-MM-YYYY'),5000);
INSERT INTO BOOK VALUES(SEQ_BID.NEXTVAL,'수런거리는 뒤란', '유안진', '비평문화', TO_DATE('04-04-2000', 'DD-MM-YYYY'),6000);
INSERT INTO BOOK VALUES(SEQ_BID.NEXTVAL,'음악의 바다, 바흐', '정종목', '생각 나무', TO_DATE('30-03-2000', 'DD-MM-YYYY'),6000);
INSERT INTO BOOK VALUES(SEQ_BID.NEXTVAL,'내 여자의 열매', '한강(韓江)', '창작과 비평', TO_DATE('15-03-2000', 'DD-MM-YYYY'),7500);

select * from BOOK;
COMMIT;
/*
select * from BOOK;
drop table BOOK;
drop SEQUENCE seq_bid;
delete from BOOK where BOOK_ID = 1;
*/
