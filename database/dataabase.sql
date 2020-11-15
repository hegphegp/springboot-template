CREATE TABLE "public"."sys_user" (
  "id" varchar(32) PRIMARY KEY,
  "username" varchar(32),
  "nickname" varchar(64),
  "password" varchar(64),
  "del" bool,
  "create_by" varchar(32),
  "update_by" varchar(32),
  "create_at" timestamp,
  "update_at" timestamp
);

CREATE INDEX "sys_user_username_index" ON "public"."sys_user" USING hash ("username");
CREATE INDEX "sys_user_username_del_index" ON "public"."sys_user" USING btree ("username", "del");

COMMENT ON COLUMN "public"."sys_user"."id" IS '用户ID';
COMMENT ON COLUMN "public"."sys_user"."username" IS '用户名';
COMMENT ON COLUMN "public"."sys_user"."nickname" IS '昵称';
COMMENT ON COLUMN "public"."sys_user"."password" IS '密码';
COMMENT ON COLUMN "public"."sys_user"."del" IS '是否删除';
COMMENT ON COLUMN "public"."sys_user"."create_by" IS '创建者';
COMMENT ON COLUMN "public"."sys_user"."update_by" IS '更新者';
COMMENT ON COLUMN "public"."sys_user"."create_at" IS '创建时间';
COMMENT ON COLUMN "public"."sys_user"."update_at" IS '更新时间';
