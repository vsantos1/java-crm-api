CREATE TABLE tb_payments(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description VARCHAR(255),
    due_date DATE NOT NULL,
    payment_date DATE,
    value DECIMAL(10,2) NOT NULL,
    type VARCHAR(255) NOT NULL,
    person_id SERIAL NOT NULL,
    category_id SERIAL NOT NULL,
    FOREIGN KEY (person_id) REFERENCES tb_persons(id),
    FOREIGN KEY (category_id) REFERENCES tb_persons(id)


);

INSERT INTO tb_payments (description, due_date, payment_date, value, type, person_id, category_id) VALUES ('Conta de Luz', '2021-01-10', '2021-01-10', 100.00, 'RECEITA', 1, 1);
INSERT INTO tb_payments (description, due_date, payment_date, value, type, person_id, category_id) VALUES ('Conta de Água', '2021-01-10', '2021-01-10', 100.00, 'RECEITA', 1, 1);
INSERT INTO tb_payments (description, due_date, payment_date, value, type, person_id, category_id) VALUES ('Conta de Telefone', '2021-01-10', '2021-01-10', 100.00, 'RECEITA', 1, 1);