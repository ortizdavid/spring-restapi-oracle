
-- View: view_task_data
DROP VIEW view_task_data;
CREATE VIEW view_task_data AS
SELECT t.task_id, t.task_name,
    t.task_status, t.complexity,
    t.description,
    u.user_id, u.user_name,
    u.password
FROM tasks t
JOIN users u ON(u.user_id = t.user_id);