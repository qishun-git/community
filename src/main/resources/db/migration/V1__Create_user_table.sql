CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `gmt_create` bigint DEFAULT NULL,
  `gmt_modified` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);