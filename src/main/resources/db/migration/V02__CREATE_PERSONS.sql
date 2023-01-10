CREATE TABLE tb_persons(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    complement VARCHAR(255),
    district VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    cep VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE   
);

INSERT INTO tb_persons (name, street, complement, district, city, number, state, cep) VALUES ('João da Silva', 'Rua das Flores', 'Apto 303', 'Jardim', 'Uberlândia', '1000', 'MG', '38400-000');
INSERT INTO tb_persons (name, street, complement, district, city, number, state, cep) VALUES ('Maria de Souza', 'Avenida Matos', '105', 'Centro', 'São Paulo', '1000', 'SP', '38777-000');
INSERT INTO tb_persons (name, street, complement, district, city, number, state, cep) VALUES ('Pedro Alves', 'Rua das Flores', 'Apto 303', 'Jardim', 'Uberlândia', '1000', 'MG', '38400-000');
INSERT INTO tb_persons (name, street, complement, district, city, number, state, cep) VALUES ('Paulo Roberto', 'Avenida Floriano', '2106', 'Centro', 'Rio de Janeiro', '2000', 'RJ', '20000-000');