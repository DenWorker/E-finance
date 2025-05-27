-- Создание БД
CREATE DATABASE mydatabase;


-- Создание таблицы
CREATE TABLE users (
    id SERIAL PRIMARY KEY,      -- Автоинкрементируемый уникальный идентификатор
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Добавление столбца
ALTER TABLE users
    ADD COLUMN age INTEGER DEFAULT 0 NOT NULL;


--  Создание таблицы с внешними ключами (связи между таблицами)
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)  -- Внешний ключ
);



-- Вставка данных в таблицы
-- Вставка пользователя
INSERT INTO users (first_name, password) VALUES ('john_doe', 'password123');

-- Вставка заказа для пользователя
INSERT INTO orders (user_id) VALUES (1);  -- user_id = 1, это внешний ключ на таблицу users


-- Запросы для выборки данных
SELECT * FROM users;

SELECT * FROM orders WHERE user_id = 1;

SELECT users.username, orders.order_date
FROM users
JOIN orders ON users.id = orders.user_id;



-- Обновление данных
UPDATE users
SET password = 'newpassword123'
WHERE id = 1;




-- Удаление данных
-- Сначала удаляем заказы, чтобы избежать ошибки внешнего ключа
DELETE FROM orders WHERE user_id = 1;

-- Теперь удаляем пользователя
DELETE FROM users WHERE id = 1;
