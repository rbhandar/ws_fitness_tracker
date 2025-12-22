
INSERT INTO users (
  user_id,
  user_name,
  first_name,
  last_name,
  email,
  phone_number,
  create_timestamp,
  update_timestamp
) VALUES
  (1, 'test', 'Mike', 'Johnson', 'mike.johnson@example.com', '343-343-5354', TIMESTAMP '2025-01-01 08:00:00', TIMESTAMP '2025-01-01 08:00:00'),
  (2, 'testuser', 'Test', 'User', 'test.user@example.com', '123-456-7890', TIMESTAMP '2025-01-02 08:00:00', TIMESTAMP '2025-01-02 08:00:00');

INSERT INTO fitness_tracking (
  trackingid,
  userid,
  tracking_name,
  tracking_type,
  status,
  total_distance,
  average_pace,
  start_time,
  end_time
) VALUES
  (1,1, 'Daily Steps', 'Biking', 'IN_PROGRESS', '0 km', '00:00', '2025-01-03 07:00:00', '2025-01-03 07:00:00'),
  (2,2, 'Daily Run',    'Running','COMPLETED',  '5 km', '05:30', '2025-01-02 06:30:00', '2025-01-02 07:00:00');

