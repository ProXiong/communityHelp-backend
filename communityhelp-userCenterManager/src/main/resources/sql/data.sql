
use communitycares;
-- 插入管理员角色
INSERT INTO Role (roleName, description)
SELECT 'admin', '管理员角色'
WHERE NOT EXISTS (SELECT 1 FROM Role WHERE roleName = 'admin');

-- 插入普通用户角色
INSERT INTO Role (roleName, description)
SELECT 'Defaulter', '普通用户角色'
WHERE NOT EXISTS (SELECT 1 FROM Role WHERE roleName = 'Defaulter');