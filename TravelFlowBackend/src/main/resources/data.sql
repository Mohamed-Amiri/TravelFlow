INSERT INTO trip (destination, `date`, price)
SELECT * FROM (SELECT 'Marrakech' AS destination, '2025-11-10' AS `date`, 1999 AS price) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM trip);

INSERT INTO trip (destination, `date`, price)
SELECT * FROM (SELECT 'Chefchaouen', '2025-12-05', 1499) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM trip WHERE destination = 'Chefchaouen');

INSERT INTO trip (destination, `date`, price)
SELECT * FROM (SELECT 'Merzouga', '2026-01-20', 2299) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM trip WHERE destination = 'Merzouga');

-- Seed initial clients
INSERT INTO client (name, email)
SELECT * FROM (SELECT 'John Doe' AS name, 'john.doe@example.com' AS email) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM client);

INSERT INTO client (name, email)
SELECT * FROM (SELECT 'Jane Smith', 'jane.smith@example.com') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM client WHERE email = 'jane.smith@example.com');



