
CREATE TABLE IF NOT EXISTS users (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(100) NOT NULL UNIQUE,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  phone_number VARCHAR(50) NOT NULL UNIQUE,
  create_timestamp TIMESTAMP NOT NULL,
  update_timestamp TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS fitness_tracking (
  trackingid INT PRIMARY KEY AUTO_INCREMENT,
  userid INT NOT NULL,
  tracking_name VARCHAR(255) NOT NULL,
  tracking_type VARCHAR(100) NOT NULL,
  status VARCHAR(50) NOT NULL,
  total_distance VARCHAR(100) NOT NULL,
  average_pace VARCHAR(50) NOT NULL,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP NULL
);

