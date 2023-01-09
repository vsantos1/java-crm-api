CREATE TABLE tb_persons(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    complement VARCHAR(255),
    district VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    cep VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE   
);
