# 用户表
# 存储用户名、密码、邮箱、身份证号、手机号、性别、年龄、账号、密码、头像url、注册时间、最后登录时间等信息
# 图片使用url存储，头像url可以为空,url由minio服务器存储返回获取
use communitycares;

CREATE TABLE user_info_tb
(
    id            INT PRIMARY KEY AUTO_INCREMENT comment '用户id，主键',
    user_name     VARCHAR(50) comment '用户名,真实姓名',
    identity_card VARCHAR(50) comment '身份证号' UNIQUE,
    phone         VARCHAR(20) comment '手机号',
    sex           TINYINT(1) comment '性别 0 男 1 女',
    age           INT comment '年龄',
    account       VARCHAR(50) comment '账号',
    password      VARCHAR(255) NOT NULL comment '密码',
    avatar_url    VARCHAR(255) comment '头像url',
    email         VARCHAR(50) comment '邮箱' UNIQUE,
    created_time  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP comment '注册时间',
    updated_time  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        comment '修改时间',
    is_delete     TINYINT(1)            DEFAULT 0 comment '是否删除 0 未删除 1 删除'
);



# 角色表
# 存储角色id、角色名称、创建时间、修改时间等信息
CREATE TABLE role_tb
(
    id           INT PRIMARY KEY AUTO_INCREMENT comment '角色id，主键',
    role_name    VARCHAR(50) comment '角色名称' UNIQUE,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        comment '修改时间',
    is_delete    TINYINT(1)         DEFAULT 0 comment '是否删除 0 未删除 1 删除'
);

# 权限表
# 存储权限id、权限名称、创建时间、修改时间等信息
CREATE TABLE permission_tb
(
    id              INT PRIMARY KEY AUTO_INCREMENT comment '权限id，主键',
    permission_name VARCHAR(50) comment '权限名称' UNIQUE,
    created_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    updated_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        comment '修改时间',
    is_delete       TINYINT(1)         DEFAULT 0 comment '是否删除 0 未删除 1 删除'
);

# 用户-角色关联表
# 存储用户id、角色id、创建时间、修改时间等信息
CREATE TABLE user_role_tb
(
    id           INT PRIMARY KEY AUTO_INCREMENT comment '用户角色id，主键',
    user_id      INT comment '用户id',
    role_id      INT comment '角色id',
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        comment '修改时间',
    is_delete    TINYINT(1)         DEFAULT 0 comment '是否删除 0 未删除 1 删除',
#                            外键约束
    FOREIGN KEY (user_id) REFERENCES user_info_tb (id),
    FOREIGN KEY (role_id) REFERENCES role_tb (id)
);
# 角色-权限关联表
# 存储角色id、权限id、创建时间、修改时间等信息
CREATE TABLE role_permission_tb
(
    id            INT PRIMARY KEY AUTO_INCREMENT comment '角色权限id，主键',
    role_id       INT comment '角色id',
    permission_id INT comment '权限id',
    created_time  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    updated_time  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        comment '修改时间',
    is_delete     TINYINT(1)         DEFAULT 0 comment '是否删除 0 未删除 1 删除',
#                            外键约束
    FOREIGN KEY (role_id) REFERENCES role_tb (id),
    FOREIGN KEY (permission_id) REFERENCES permission_tb (id)
);

# 帖子表
# 存储帖子的id、用户id、标题、内容、图片url、创建时间、更新时间、是否删除、是否可见等信息
CREATE TABLE post_tb
(
    id           INT PRIMARY KEY AUTO_INCREMENT comment '帖子id，主键',
    user_id      INT          NOT NULL comment '用户id，外键',
    title        VARCHAR(255) NOT NULL comment '帖子标题',
    content      TEXT         NOT NULL comment '帖子内容',
    picture_url  VARCHAR(255) comment '帖子图片url',
    created_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    updated_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    is_deleted   TINYINT(1)            DEFAULT 0 comment '是否删除，0：未删除，1：已删除',
    is_visible   TINYINT(1)            DEFAULT 1 comment '是否可见，0：不可见，1：可见',
    FOREIGN KEY (user_id) REFERENCES user_info_tb (id)
);

# 评论表
# 存储评论的id、帖子id、用户id、内容、图片url、创建时间、是否删除等信息
# 评论表与用户表、帖子表为多对多关系
CREATE TABLE post_comment_tb
(
    id           INT PRIMARY KEY AUTO_INCREMENT comment '评论id，主键',
    post_id      INT       NOT NULL comment '帖子id，外键',
    user_id      INT       NOT NULL comment '用户id，外键',
    content      TEXT      NOT NULL comment '评论内容',
    picture_url  VARCHAR(255) comment '评论图片url',
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    is_deleted   TINYINT(1)         DEFAULT 0 comment '是否删除，0：未删除，1：已删除',
    FOREIGN KEY (post_id) REFERENCES post_tb (id),
    FOREIGN KEY (user_id) REFERENCES user_info_tb (id)
);

# 点赞表
# 存储点赞的id、帖子id、用户id、创建时间等信息
# 点赞表与用户表、帖子表为多对多关系
CREATE TABLE post_like_tb
(
    id           INT PRIMARY KEY AUTO_INCREMENT comment '点赞id，主键',
    post_id      INT       NOT NULL comment '帖子id，外键',
    user_id      INT       NOT NULL comment '用户id，外键',
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '点赞时间',
    UNIQUE (post_id, user_id) comment '同一用户对同一帖子只能点赞一次',
    FOREIGN KEY (post_id) REFERENCES post_tb (id),
    FOREIGN KEY (user_id) REFERENCES user_info_tb (id)
);

# 附件表
# 存储附件的id、帖子id、文件url、文件类型、创建时间等信息
# 附件表与帖子表为一对多关系 上传excel等文件
CREATE TABLE post_attachment_tb
(
    id           INT PRIMARY KEY AUTO_INCREMENT comment '附件id，主键',
    post_id      INT          NOT NULL comment '帖子id，外键',
    file_url     VARCHAR(255) NOT NULL comment '附件文件URL',
    file_type    VARCHAR(50) comment '附件文件类型',
    created_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    FOREIGN KEY (post_id) REFERENCES post_tb (id)
);

# 收藏表
CREATE TABLE post_favorite_tb
(
    id           INT PRIMARY KEY AUTO_INCREMENT comment '收藏id，主键',
    user_id      INT       NOT NULL comment '用户id，外键',
    post_id      INT       NOT NULL comment '帖子id，外键',
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '收藏时间',
    is_deleted   TINYINT(1)         DEFAULT 0 comment '是否取消收藏，0：未取消，1：已取消',
    UNIQUE (user_id, post_id) comment '同一用户不能对同一帖子重复收藏',
    FOREIGN KEY (user_id) REFERENCES user_info_tb (id),
    FOREIGN KEY (post_id) REFERENCES post_tb (id)
);