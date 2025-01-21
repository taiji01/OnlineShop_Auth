CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100),
    `phone` VARCHAR(20),
    `role` VARCHAR(20) NOT NULL DEFAULT 'USER',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 插入管理员账户
INSERT INTO `user` (`username`, `password`, `email`, `phone`, `role`)
SELECT 'admin', '123456', 'admin@example.com', '13800000000', 'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM `user` WHERE `username` = 'admin'
); 