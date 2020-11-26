CREATE TABLE "public"."sys_user" (
  "id" varchar(32) PRIMARY KEY,
  "username" varchar(32),
  "nickname" varchar(64),
  "password" varchar(64),
  "create_at" timestamp,
  "update_at" timestamp,
  "del" bool
);

CREATE INDEX "sys_user_username_index" ON "public"."sys_user" USING hash ("username");
CREATE INDEX "sys_user_username_del_index" ON "public"."sys_user" USING btree ("username", "del");

COMMENT ON COLUMN "public"."sys_user"."id" IS '用户ID';
COMMENT ON COLUMN "public"."sys_user"."username" IS '用户名';
COMMENT ON COLUMN "public"."sys_user"."nickname" IS '昵称';
COMMENT ON COLUMN "public"."sys_user"."password" IS '密码';
COMMENT ON COLUMN "public"."sys_user"."create_at" IS '创建时间';
COMMENT ON COLUMN "public"."sys_user"."update_at" IS '更新时间';
COMMENT ON COLUMN "public"."sys_user"."del" IS '是否删除';


CREATE TABLE "public"."sys_dict_type" (
  "id" varchar(32) PRIMARY KEY,
  "name" varchar(64),
  "code" varchar(128),
  "order_no" int8,
  "create_at" timestamp,
  "update_at" timestamp,
  "del" bool
);

CREATE INDEX "sys_dict_type_code_index" ON "public"."sys_dict_type" USING hash ("code");
CREATE INDEX "sys_dict_type_code_del_index" ON "public"."sys_dict_type" USING btree ("code", "del");

COMMENT ON COLUMN "public"."sys_dict_type"."id" IS 'ID';
COMMENT ON COLUMN "public"."sys_dict_type"."name" IS '中文名称';
COMMENT ON COLUMN "public"."sys_dict_type"."code" IS 'CODE编码';
COMMENT ON COLUMN "public"."sys_dict_type"."order_no" IS '排序号';
COMMENT ON COLUMN "public"."sys_dict_type"."create_at" IS '创建时间';
COMMENT ON COLUMN "public"."sys_dict_type"."update_at" IS '更新时间';
COMMENT ON COLUMN "public"."sys_dict_type"."del" IS '是否删除';


CREATE TABLE "public"."sys_dict_item" (
  "id" varchar(32) PRIMARY KEY,
  "name" varchar(64),
  "code" varchar(128),
  "parent_id" varchar(32),
  "order_no" int8,
  "level" int2,
  "path" varchar(256),
  "create_at" timestamp,
  "update_at" timestamp,
  "del" bool
);

CREATE INDEX "sys_dict_item_code_index" ON "public"."sys_dict_item" USING hash ("code");
CREATE INDEX "sys_dict_item_parent_id_index" ON "public"."sys_dict_item" USING btree ("parent_id");
CREATE INDEX "sys_dict_item_code_del_index" ON "public"."sys_dict_item" USING btree ("code", "del");

COMMENT ON COLUMN "public"."sys_dict_item"."id" IS 'ID';
COMMENT ON COLUMN "public"."sys_dict_item"."name" IS '中文名称';
COMMENT ON COLUMN "public"."sys_dict_item"."code" IS 'CODE编码';
COMMENT ON COLUMN "public"."sys_dict_item"."parent_id" IS '父ID';
COMMENT ON COLUMN "public"."sys_dict_item"."order_no" IS '排序号';
COMMENT ON COLUMN "public"."sys_dict_item"."level" IS '等级';
COMMENT ON COLUMN "public"."sys_dict_item"."path" IS '路径，全路径';
COMMENT ON COLUMN "public"."sys_dict_item"."create_at" IS '创建时间';
COMMENT ON COLUMN "public"."sys_dict_item"."update_at" IS '更新时间';
COMMENT ON COLUMN "public"."sys_dict_item"."del" IS '是否删除';