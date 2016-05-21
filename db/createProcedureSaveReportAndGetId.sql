DELIMITER $$
CREATE PROCEDURE saveReportAndGetId(
IN name VARCHAR(50), document MEDIUMBLOB)
BEGIN
    INSERT INTO report (name, document) VALUES (name, document);
    SELECT LAST_INSERT_ID();
END $$
DELIMITER ;