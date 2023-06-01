CREATE TABLE `purchase_history`													
(													
	 `purchase_id`					varchar(32)				NOT NULL			COMMENT '購入ID'
	 ,`goods_id`					varchar(32)				NOT NULL			COMMENT '商品ID'
	 ,`user_id`					    varchar(32)				NOT NULL			COMMENT 'ユーザーID'
	 ,`quantity`					integer					NOT NULL			COMMENT '個数'
	 ,`purchase_date`				date					NOT NULL			COMMENT '購入日'
	 ,`price`						integer					NOT NULL			COMMENT '価格'
	 ,`del_flg`						boolean										COMMENT '削除フラグ'
	 ,`create_div`					varchar(1)									COMMENT '登録区分'
	 ,`create_date`					datetime									COMMENT '登録年月日'
	 ,`create_id`					varchar(12)									COMMENT '登録者ID'
	 ,`update_date`					datetime									COMMENT '更新年月日'
	 ,`update_id`					varchar(12)									COMMENT '更新者ID'
	 ,`note`					    varchar(255)								COMMENT '備考'
	 ,PRIMARY KEY(`purchase_id`, `goods_id`, `user_id`)
)													
COMMENT = '購入履歴マスタ';						

INSERT INTO purchase_history(`purchase_id`,`goods_id`,`user_id` ,`quantity` ,`purchase_date` ,`price`  ,`del_flg` ,`create_div`,`create_date`,`create_id`,`update_date`,`update_id`,`note`)
VALUES				        ('abs1'       ,'cart1'   ,'admin'    ,'1'       ,'2022-01-19'    ,'12000'   ,'0'	   ,'C'		    ,'2022-12-20' ,'admin'	  ,'2022-05-20' ,'admin'	  ,null),
					        ('abs2'       ,'cart2'   ,'admin'    ,'1'       ,'2022-02-13'    ,'15000'   ,'0'	   ,'C'		    ,'2022-01-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs3'       ,'cart3'   ,'admin'    ,'1'       ,'2022-03-13'    ,'16000'   ,'0'	   ,'C'		    ,'2022-02-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs4'       ,'cart4'   ,'admin'    ,'1'       ,'2022-04-13'    ,'10000'   ,'0'	   ,'C'		    ,'2022-03-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs5'       ,'cart5'   ,'admin'    ,'1'       ,'2022-05-13'    ,'14000'   ,'0'	   ,'C'		    ,'2022-04-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs6'       ,'cart6'   ,'admin'    ,'1'       ,'2022-06-13'    ,'10000'   ,'0'	   ,'C'		    ,'2022-05-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs7'       ,'cart7'   ,'admin'    ,'1'       ,'2022-07-13'    ,'12000'   ,'0'	   ,'C'		    ,'2022-06-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs8'       ,'cart8'   ,'admin'    ,'1'       ,'2022-08-13'    ,'8000'    ,'0'	   ,'C'		    ,'2022-07-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs9'       ,'cart9'   ,'admin'    ,'1'       ,'2022-09-13'    ,'6000'    ,'0'	   ,'C'		    ,'2022-07-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs10'      ,'cart10'  ,'admin'    ,'1'       ,'2022-10-13'    ,'7000'    ,'0'	   ,'C'		    ,'2022-07-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs11'      ,'cart11'  ,'admin'    ,'1'       ,'2022-11-13'    ,'12000'   ,'0'	   ,'C'		    ,'2022-07-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null),
							('abs12'      ,'cart12'  ,'admin'    ,'1'       ,'2022-12-13'    ,'6000'    ,'0'       ,'C'		    ,'2022-08-01' ,'admin'	  ,'2022-01-01' ,'admin'	  ,null)
;
