DROP TABLE IF EXISTS `supervisord_job`;

CREATE TABLE `supervisord_job`
(
    `job_id`      bigint(20)    NOT NULL,
    `server`      varchar(255)  NOT NULL,
    `name`        varchar(255)  NOT NULL,
    `description` varchar(4096) NOT NULL,
    `create_time` datetime      NULL     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime      NULL     DEFAULT CURRENT_TIMESTAMP,
    `status`      int(2)        NOT NULL DEFAULT 0,
    PRIMARY KEY (`job_id`)
);