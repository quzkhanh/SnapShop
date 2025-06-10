-- Bảng Admins
  CREATE TABLE admins (
      id INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(100) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL,
      full_name VARCHAR(255),
      email VARCHAR(100)
  );

  -- Bảng Employees
  CREATE TABLE employees (
      id INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(100) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL,
      full_name VARCHAR(255),
      phone VARCHAR(20),
      email VARCHAR(100),
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );

  -- Bảng Categories
  CREATE TABLE categories (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(100) NOT NULL UNIQUE
  );

  -- Bảng Products
  CREATE TABLE products (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      description TEXT,
      category_id INT,
      price DECIMAL(10,2) NOT NULL,
      cost_price DECIMAL(10,2) NOT NULL,
      stock INT NOT NULL DEFAULT 0,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (category_id) REFERENCES categories(id)
  );

  -- Bảng Orders
  CREATE TABLE orders (
      id INT AUTO_INCREMENT PRIMARY KEY,
      employee_id INT NOT NULL,
      total_price DECIMAL(10,2) NOT NULL,
      payment_method VARCHAR(50),
      payment_status VARCHAR(20),
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (employee_id) REFERENCES employees(id)
  );

  -- Bảng Order Items
  CREATE TABLE order_items (
      id INT AUTO_INCREMENT PRIMARY KEY,
      order_id INT NOT NULL,
      product_id INT NOT NULL,
      quantity INT NOT NULL,
      price DECIMAL(10,2) NOT NULL,
      cost_price DECIMAL(10,2) NOT NULL,
      total DECIMAL(10,2) NOT NULL,
      FOREIGN KEY (order_id) REFERENCES orders(id),
      FOREIGN KEY (product_id) REFERENCES products(id)
  );

  -- Dữ liệu mẫu
  INSERT INTO admins (username, password, full_name, email)
  VALUES ('admin', 'admin123', 'Chủ cửa hàng', 'admin@snapshop.local');

  INSERT INTO categories (name) VALUES ('Đồ uống'), ('Đồ ăn nhanh');

  INSERT INTO products (name, description, category_id, price, cost_price, stock)
  VALUES ('Trà sữa truyền thống', 'Vị truyền thống', 1, 25000, 15000, 100);