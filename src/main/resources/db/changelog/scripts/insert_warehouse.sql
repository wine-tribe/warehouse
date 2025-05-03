INSERT INTO warehouse (external_id, is_deleted, status, warehouse_name, is_transit_warehouse, warehouse_group_id, created_date, created_by, last_modified_date, last_modified_by)
VALUES
    ('W-001', false, 'ACTIVE', 'Склад Москва', false, 1, '2025-01-01 00:00:00',
     '{"fullName": "Admin User", "username": "admin"}'::jsonb, '2025-01-01 00:00:00',
     '{"fullName": "Admin User", "username": "admin"}'::jsonb),

    ('W-002', false, 'ACTIVE', 'Склад Санкт-Петербург', false, 2, '2025-01-01 00:00:00',
     '{"fullName": "Admin User", "username": "admin"}'::jsonb, '2025-01-01 00:00:00',
     '{"fullName": "Admin User", "username": "admin"}'::jsonb),

    ('W-003', false, 'INACTIVE', 'Склад Новосибирск', false, 3, '2025-01-01 00:00:00',
     '{"fullName": "Admin User", "username": "admin"}'::jsonb, '2025-01-01 00:00:00',
     '{"fullName": "Admin User", "username": "admin"}'::jsonb),

    ('W-004', false, 'ACTIVE', 'Транзитный склад Казань', true, 1, '2025-01-01 00:00:00',
     '{"fullName": "Admin User", "username": "admin"}'::jsonb, '2025-01-01 00:00:00',
     '{"fullName": "Admin User", "username": "admin"}'::jsonb);
