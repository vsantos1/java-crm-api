CREATE TABLE tb_categories(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
INSERT INTO tb_categories(name) VALUES ('Pharmacy');
INSERT INTO tb_categories(name) VALUES ('Grocery');
INSERT INTO tb_categories(name) VALUES ('Electronics');
INSERT INTO tb_categories(name) VALUES ('Clothing');
INSERT INTO tb_categories(name) VALUES ('Home');
INSERT INTO tb_categories(name) VALUES ('Toys');
