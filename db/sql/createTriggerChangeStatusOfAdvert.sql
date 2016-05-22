DELIMITER $$
DROP TRIGGER IF EXISTS salesa.changeStatusOfAdvert $$
CREATE TRIGGER changeStatusOfAdvert
AFTER UPDATE ON advert
 FOR EACH ROW
BEGIN
    DECLARE a_id INT;
    DECLARE a_title VARCHAR(100);
    DECLARE a_text VARCHAR(1000);
    DECLARE a_price DECIMAL;
    DECLARE a_currency VARCHAR(5);
    DECLARE a_modificationDate DATETIME;
    DECLARE a_status VARCHAR(5);
    DECLARE u_name VARCHAR(50);
    DECLARE u_email VARCHAR(50);
    DECLARE i_id INT;
IF NEW.status <> OLD.status
THEN
SELECT NEW.id INTO i_id ;
#fill variables
SELECT a.id, a.title, a.text, a.price, a.currency, a.modificationDate,
        a.status, u.name, u.email
        INTO a_id, a_title, a_text, a_price,
        a_currency, a_modificationDate,
        a_status, u_name, u_email
        FROM advert AS a
        INNER JOIN user AS u ON u.id = a.userId WHERE a.id = i_id;
        #into table
INSERT INTO change_status(advertId, advertTitle, advertText, advertPrice, advertCurrency,
        advertStatus, advertModificationDate, userName, userEmail)
        VALUES(a_id, a_title, a_text, a_price, a_currency, a_status, a_modificationDate,
        u_name, u_email);
END IF;
END$$
DELIMITER ;