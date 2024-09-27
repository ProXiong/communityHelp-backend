INSERT INTO User (userName, userAccount, userPassword, avatarUrl, gender, phone, email, userStatus, isDelete)
VALUES
('Alice', 'alice123', 'password123', 'http://example.com/avatar/alice.jpg', 'FEMALE', '12345678901', 'alice@example.com', 1, 0),
('Bob', 'bob456', 'password456', 'http://example.com/avatar/bob.jpg', 'MALE', '12345678902', 'bob@example.com', 1, 0),
('Charlie', 'charlie789', 'password789', NULL, 'OTHER', '12345678903', 'charlie@example.com', 1, 0);
INSERT INTO Role (roleName, description)
VALUES
('admin', '管理员，具备所有权限'),
('user', '普通用户，具有有限权限');
INSERT INTO User_Role (userId, roleId)
VALUES
(1, 1), -- Alice 是管理员
(2, 2), -- Bob 是普通用户
(3, 2); -- Charlie 是普通用户
INSERT INTO Permission (permissionName, description)
VALUES
('READ_POST', '查看帖子权限'),
('WRITE_POST', '发布帖子权限'),
('DELETE_POST', '删除帖子权限'),
('MANAGE_USER', '管理用户权限');
INSERT INTO Role_Permission (roleId, permissionId)
VALUES
(1, 1), -- 管理员具备查看帖子权限
(1, 2), -- 管理员具备发布帖子权限
(1, 3), -- 管理员具备删除帖子权限
(1, 4), -- 管理员具备管理用户权限
(2, 1); -- 普通用户具备查看帖子权限
INSERT INTO Tag (tagName, description)
VALUES
('Technology', '与科技相关的帖子'),
('Health', '与健康和保健相关的帖子'),
('Lifestyle', '与生活方式和技巧相关的帖子');
INSERT INTO user_Tag (userId, tagId)
VALUES
(1, 1), -- Alice 喜欢科技标签
(1, 2), -- Alice 喜欢健康标签
(2, 1), -- Bob 喜欢科技标签
(3, 3); -- Charlie 喜欢生活方式标签
INSERT INTO post (title, content, tags, userId, thumbNum, favourNum, createTime, updateTime, isDelete)
VALUES
('探索科技创新', '这篇帖子讨论了最新的科技创新。', '["Technology"]', 1, 10, 5, NOW(), NOW(), 0),
('健康生活小贴士', '这篇帖子提供了一些健康生活的建议。', '["Health"]', 2, 5, 2, NOW(), NOW(), 0),
('2023年生活技巧', '这篇帖子分享了一些实用的生活技巧。', '["Lifestyle"]', 3, 2, 1, NOW(), NOW(), 0);
INSERT INTO post_thumb (postId, userId, createTime, updateTime)
VALUES
(1, 2, NOW(), NOW()), -- Bob 点赞了第一篇帖子
(1, 3, NOW(), NOW()), -- Charlie 点赞了第一篇帖子
(2, 1, NOW(), NOW()); -- Alice 点赞了第二篇帖子
INSERT INTO post_favour (postId, userId, createTime, updateTime)
VALUES
(2, 1, NOW(), NOW()), -- Alice 收藏了第二篇帖子
(3, 2, NOW(), NOW()); -- Bob 收藏了第三篇帖子
CREATE TABLE IF NOT EXISTS Tag
(
    tagId       BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '标签 ID',
    tagName     VARCHAR(256) NOT NULL UNIQUE COMMENT '标签名称',
    description TEXT COMMENT '标签描述' -- '标签描述'已是中文
) COMMENT '标签';
