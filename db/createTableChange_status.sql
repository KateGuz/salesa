CREATE TABLE change_status (
    id INT  AUTO_INCREMENT PRIMARY KEY,
    advertId INT NOT NULL,
    advertTitle VARCHAR(100) NOT NULL,
    advertText VARCHAR(1000) NOT NULL,
    advertPrice DECIMAL NOT NULL,
    advertCurrency VARCHAR(5) NOT NULL,
    advertStatus VARCHAR(1) NOT NULL,
    advertModificationDate DATETIME NOT NULL,
    userName VARCHAR(50) NOT NULL,
    userEmail VARCHAR(50) NOT NULL
);