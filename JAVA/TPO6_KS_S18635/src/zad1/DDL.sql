--<ScriptOptions statementTerminator=";"/>
set SCHEMA APP


select p.isbn,a.name as AUTOR,p.tytul,p.rok,w.name as WYDAWCA,p.cena from POZYCJE p inner join autor a on p.autid=a.autid inner join wydawca w on p.wydid=w.wydid 





