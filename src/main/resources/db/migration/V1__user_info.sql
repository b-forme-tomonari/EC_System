CREATE TABLE `user_info`													
(													
	 `user_id`					varchar(32)				NOT NULL			COMMENT 'ユーザーID'
	 ,`password`				varchar(128)			NOT NULL			COMMENT 'パスワード'
	 ,`email`					varchar(100)			NOT NULL			COMMENT 'メールアドレス'
	 ,`name`					varchar(24)				NOT NULL			COMMENT '名前'
	 ,`date_of_birth`			date				    NOT NULL			COMMENT '生年月日'
	 ,`zip_code`				varchar(8)				NOT NULL			COMMENT '郵便番号'
	 ,`address`					varchar(100)			NOT NULL			COMMENT '住所'
	 ,`del_flg`					boolean							            COMMENT '削除フラグ'
	 ,`create_div`				varchar(1)									COMMENT '登録区分'
	 ,`create_date`				datetime									COMMENT '登録年月日'
	 ,`create_id`				varchar(12)									COMMENT '登録者ID'
	 ,`update_date`				datetime									COMMENT '更新年月日'
	 ,`update_id`				varchar(12)									COMMENT '更新者ID'
	 ,`note`					varchar(255)								COMMENT '備考'
	 ,PRIMARY KEY(`user_id`)
)													
COMMENT = 'ユーザー情報マスタ';													

INSERT INTO user_info(`user_id`,`password`,`email`            ,`name`    ,`date_of_birth`  ,`zip_code` ,`address`                             ,`del_flg` ,`create_div`,`create_date`,`create_id`,`update_date`,`update_id`,`note`)
VALUES				 ('general','$2a$10$6fPXYK.C9rCWUBifuqBIB.GRNU.nQtBpdzkkKis8ETaKVKxNo/ltO'  ,'test@gmail.com'	,'テスト'   ,'2022-05-20'  ,'600-8216'	,'京都府京都市下京区東塩小路町７２１−１'  ,'0' ,'C' ,'2022-05-20' ,'admin' ,'2022-05-20' ,'admin' ,null),
					 ('admin'  ,'$2a$10$SJTWvNl16fCU7DaXtWC0DeN/A8IOakpCkWWNZ/FKRV2CHvWElQwMS'  ,'sample@gmail.com' ,'サンプル' ,'2022-05-20'  ,'650-0042'	,'兵庫県神戸市中央区波止場町５−５'	      ,'0' ,'C' ,'2022-01-01' ,'admin' ,'2022-01-01' ,'admin' ,null)
;
