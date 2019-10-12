INSERT INTO account
       (name, username, email, mobile_phone, password, enabled, created_on, created_by, approved)
VALUES
       ('Admin', 'pmteam@intuit.com', 'pmteam@intuit.com', '9343352734', '$2a$10$9NhAigH0vdTPk1M45AVSYO1UpA0ZQSl1ce6drvP4KdPzlBHnnHGm2', TRUE, NOW(), 1, TRUE),
       ('User', 'user@intuit.com', 'user@intuit.com', '9592034521',  '$2a$10$9NhAigH0vdTPk1M45AVSYO1UpA0ZQSl1ce6drvP4KdPzlBHnnHGm2', TRUE, NOW(), 2, TRUE);
