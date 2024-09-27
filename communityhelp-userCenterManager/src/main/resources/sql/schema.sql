-- region 用户相关
-- 数据库初始化
-- 用户表todo 用户表设计不对
CREATE TABLE  IF NOT EXISTS  User
(
    userId      BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    userName    VARCHAR(256) NOT NULL DEFAULT 'Unnamed User' COMMENT '用户昵称',
    userAccount  VARCHAR(256)                       NOT NULL COMMENT '账号',
    userPassword VARCHAR(512)                       NOT NULL COMMENT '密码',
    avatarUrl    VARCHAR(1024)                      NULL COMMENT '用户头像',
    gender       ENUM ('MALE', 'FEMALE', 'OTHER')   NULL COMMENT '性别',
    phone        VARCHAR(128)                       NULL COMMENT '电话',
    email        VARCHAR(512)                       NULL COMMENT '邮箱',
    userStatus   INT      DEFAULT 0                 NOT NULL COMMENT '1是0否正常',
    createTime   DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    updateTime   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    isDelete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '1是0否删除'
) COMMENT '用户';
-- 角色表
CREATE TABLE  IF NOT EXISTS Role
(
    roleId     BIGINT AUTO_INCREMENT PRIMARY KEY,
    roleName   VARCHAR(255) NOT NULL UNIQUE, -- admin管理员, user普通用户
    description TEXT
);
-- 用户角色表
CREATE TABLE  IF NOT EXISTS User_Role
(
    userId BIGINT,
    roleId BIGINT,
    PRIMARY KEY (userId, roleId),
    FOREIGN KEY (userId) REFERENCES User (userId) ON DELETE CASCADE,
    FOREIGN KEY (roleId) REFERENCES Role (roleId) ON DELETE CASCADE
);
-- 权限表
CREATE TABLE  IF NOT EXISTS Permission
(
    permissionId   BIGINT AUTO_INCREMENT PRIMARY KEY,
    permissionName VARCHAR(255) NOT NULL UNIQUE,
    description     TEXT
);
-- 角色权限表
CREATE TABLE  IF NOT EXISTS Role_Permission
(
    roleId       BIGINT,
    permissionId BIGINT,
    PRIMARY KEY (roleId, permissionId),
    FOREIGN KEY (roleId) REFERENCES Role (roleId) ON DELETE CASCADE,
    FOREIGN KEY (permissionId) REFERENCES Permission (permissionId) ON DELETE CASCADE
);
-- endregion

-- region 帖子相关
-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
    ) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
    ) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
    ) comment '帖子收藏';
-- 注意mysql的注释
-- endregion

-- region 标签相关
-- 标签表
CREATE TABLE IF NOT EXISTS Tag (
                                   tagId BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '标签 ID',
                                   tagName VARCHAR(256) NOT NULL UNIQUE COMMENT '标签名称',
                                   description TEXT COMMENT '标签描述'
) COMMENT '标签';
-- 用户标签关联表
CREATE TABLE IF NOT EXISTS user_Tag (
                                        userId BIGINT NOT NULL COMMENT '用户 ID',
                                        tagId BIGINT NOT NULL COMMENT '标签 ID',
                                        PRIMARY KEY (userId, tagId),  -- 组合主键
                                        FOREIGN KEY (userId) REFERENCES User (userId) ON DELETE CASCADE,
                                        FOREIGN KEY (tagId) REFERENCES Tag (tagId) ON DELETE CASCADE
) COMMENT '用户标签关联表';

-- endregion

