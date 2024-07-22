-- Author table
CREATE TABLE authors (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    birthday DATE
);

-- Book table
CREATE TABLE books (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    title varchar(250) NOT NULL,
    isbn varchar(250) NOT NULL,
    author_id bigint NOT NULL,
    published_year YEAR,
    status varchar(50) NOT NULL DEFAULT 'AVAILABLE',
    FOREIGN KEY(author_id) REFERENCES authors(id)
);

-- User table
CREATE TABLE users (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL UNIQUE,
    password varchar(250) NOT NULL
);

-- Loan table
CREATE TABLE loans (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    user_id bigint NOT NULL,
    book_id bigint NOT NULL,
    borrow_date DATE,
    return_date DATE NULL,
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(book_id) REFERENCES books(id)
);
