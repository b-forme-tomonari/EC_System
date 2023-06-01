CREATE TABLE `billing_address`													
(													
	 `user_id`					varchar(32)				NOT NULL			COMMENT 'ユーザーID'
	 ,`billing_name`			varchar(24)				NOT NULL			COMMENT '請求先名前'
	 ,`billing_zip_code`		varchar(8)				NOT NULL			COMMENT '請求先郵便番号'
	 ,`billing_address`			varchar(100)			NOT NULL			COMMENT '請求先住所'
	 ,`billing_number`			varchar(13)				NOT NULL			COMMENT '請求先電話番号'
	 ,`del_flg`					boolean										COMMENT '削除フラグ'
	 ,`create_div`				varchar(1)									COMMENT '登録区分'
	 ,`create_date`				datetime									COMMENT '登録年月日'
	 ,`create_id`				varchar(12)									COMMENT '登録者ID'
	 ,`update_date`				datetime									COMMENT '更新年月日'
	 ,`update_id`				varchar(12)									COMMENT '更新者ID'
	 ,`note`					varchar(255)								COMMENT '備考'
	 ,PRIMARY KEY(`user_id`)
)													
COMMENT = '請求先情報マスタ';													

INSERT INTO billing_address(`user_id`,`billing_name`,`billing_zip_code`,`billing_address`                  ,`billing_number`   ,`del_flg` ,`create_div`,`create_date`,`create_id`,`update_date`,`update_id`,`note`)
VALUES				       ('admin'   ,'ポート'       ,'260-0024'	    ,'千葉県千葉市中央区中央港１丁目'      ,'043-241-0125'     ,'0'	      ,'C'		  ,'2022-05-20' ,'admin'	,'2022-05-20' ,'admin'	  , null),
					       ('general' ,'オアシス'     ,'461-0005'        ,'愛知県名古屋市東区東桜１丁目１１−１'  ,'052-962-1011'    ,'0'	      ,'C'		   ,'2022-01-01' ,'admin'	 ,'2022-01-01' ,'admin'	   , null)
;
