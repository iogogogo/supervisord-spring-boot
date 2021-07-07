DROP TABLE IF EXISTS `supervisord_job`;

CREATE TABLE `supervisord_job`
(
    `job_id`      INTEGER PRIMARY KEY,
    `server`      varchar(255)  NOT NULL,
    `name`        varchar(255)  NOT NULL,
    `remark`      varchar(4096) NOT NULL,
    `create_time` datetime      NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime      NULL DEFAULT CURRENT_TIMESTAMP
);