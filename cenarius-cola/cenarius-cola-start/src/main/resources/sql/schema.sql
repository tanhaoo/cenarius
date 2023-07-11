CREATE TABLE tb_sales_order_item
(
    id                  VARCHAR(50)    NOT NULL PRIMARY KEY,
    qty                 INT,
    original_price      DECIMAL(10, 2) NOT NULL DEFAULT 0,
    discount_price      DECIMAL(10, 2) NOT NULL DEFAULT 0,
    spu_code            VARCHAR(20)    NOT NULL,
    sku_code            VARCHAR(20)    NOT NULL,
    unit_price          DECIMAL(10, 2) NOT NULL DEFAULT 0,
    discount_unit_price DECIMAL(10, 2) NOT NULL DEFAULT 0
);
CREATE TABLE tb_sales_order
(
    id            VARCHAR(50)    NOT NULL PRIMARY KEY,
    freight       DECIMAL(10, 2) NOT NULL DEFAULT 0,
    total_amount  DECIMAL(10, 2) NOT NULL DEFAULT 0,
    description   VARCHAR(50),
    is_use_coupon TINYINT                 DEFAULT 0,
    operate_user  VARCHAR(20)    NOT NULL,
    create_time   DATETIME(3)    NOT NULL DEFAULT NOW(3),
    update_time   DATETIME(3)    NOT NULL DEFAULT NOW(3),
    version       INT            NOT NULL DEFAULT 0
);
