CREATE TABLE `goods_info`													
(													
	 `goods_id`					varchar(32)				NOT NULL			COMMENT '商品ID'
	 ,`goods_name`				varchar(100)			NOT NULL			COMMENT '商品名'
	 ,`price`					integer					NOT NULL			COMMENT '価格'
	 ,`quantity`				integer					NOT NULL			COMMENT '個数'
	 ,`goods_image`				varchar(100)			NOT NULL			COMMENT '商品画像'
	 ,`goods_explanation`		varchar(200)			NOT NULL			COMMENT '商品説明'
	 ,`del_flg`					boolean										COMMENT '削除フラグ'
	 ,`create_div`				varchar(1)									COMMENT '登録区分'
	 ,`create_date`				datetime									COMMENT '登録年月日'
	 ,`create_id`				varchar(12)									COMMENT '登録者ID'
	 ,`update_date`				datetime									COMMENT '更新年月日'
	 ,`update_id`				varchar(12)									COMMENT '更新者ID'
	 ,`note`					varchar(255)								COMMENT '備考'
	 ,PRIMARY KEY(`goods_id`)
)													
COMMENT = '商品情報マスタ';											

INSERT INTO goods_info(`goods_id`,`goods_name`       ,`price` ,`quantity` ,`goods_image`        ,`goods_explanation`                  ,`del_flg` ,`create_div`,`create_date`,`create_id`,`update_date`,`update_id`,`note`)
VALUES				 ('cart1'    ,'サーバー'         ,'12000'  ,'20'       ,'image/Server9.jpg'	 ,'中古サーバーです。'	                 ,'0'	   ,'C'		    ,'2022-05-20' ,'admin'	  ,'2022-05-20' ,'admin'	,null),
					 ('cart2'    ,'サーバー2'        ,'15000'  ,'50'       ,'image/Server10.jpg' ,'企業で利用されていた中古サーバーです。' ,'0'		 ,'C'		  ,'2022-01-01' ,'admin'	,'2022-01-01' ,'admin'	  ,null),
					 ('cart3'    ,'サーバー3'        ,'20000'  ,'50'       ,'image/Server11.jpg' ,'高性能サーバーです。'	             ,'0'		,'C'		 ,'2022-01-01' ,'admin'	  ,'2022-01-01' ,'admin'	 ,null),
					 ('cart4'    ,'サーバー4'        ,'10000'  ,'50'       ,'image/Server14.jpg' ,'新品サーバーです。'	                 ,'0'	    ,'C'		 ,'2022-01-01' ,'admin'	  ,'2022-01-01' ,'admin'	 ,null),
					 ('cart5'    ,'サーバー5'        ,'40000'  ,'50'       ,'image/Server15.jpg' ,'大容量サーバーです。'	             ,'0'		,'C'		 ,'2022-01-01' ,'admin'	  ,'2022-01-01' ,'admin'	 ,null),
					 ('cart6'    ,'サーバー6'        ,'8000'   ,'50'       ,'image/Server8.jpg'  ,'格安サーバーです。'	                 ,'0'	    ,'C'		 ,'2022-01-01' ,'admin'	  ,'2022-01-01' ,'admin'	 ,null),
					 ('cart7'    ,'マウス'           ,'2000'   ,'50'       ,'image/Mouse3.jpg'   ,'無線マウスです。'	                 ,'0'	   ,'C'		     ,'2022-01-01' ,'admin'   ,'2022-01-01' ,'admin'	 ,null),
					 ('cart8'    ,'マウス2'          ,'2000'   ,'50'       ,'image/Mouse4.jpg'   ,'有線マウスです。'	                 ,'0'	   ,'C'		     ,'2022-01-01' ,'admin'	  ,'2022-01-01' ,'admin'	 ,null),
					 ('cart9'    ,'スマートスピーカー','6000'   ,'50'       ,'image/Speaker2.jpg' ,'流行中のスマートスピーカーです。'	   ,'0'		 ,'C'		   ,'2022-01-01' ,'admin'	 ,'2022-01-01' ,'admin'	    ,null),
					 ('cart10'   ,'ノートパソコン'    ,'10000'  ,'50'       ,'image/Note5.jpg'    ,'最新型ノートパソコンです。'	          ,'0'		 ,'C'		  ,'2022-01-01' ,'admin'	,'2022-01-01' ,'admin'	   ,null),
					 ('cart11'   ,'スマートフォン'    ,'7000'   ,'50'       ,'image/Phone6.jpg'   ,'最新型スマートフォンです。'	          ,'0'		 ,'C'		  ,'2022-01-01' ,'admin'	,'2022-01-01' ,'admin'	   ,null),
					 ('cart12'   ,'タブレット'        ,'8000'   ,'50'       ,'image/Tablet7.jpg'  ,'最新型タブレットです。'	             ,'0'		,'C'		  ,'2022-01-01' ,'admin'	,'2022-01-01' ,'admin'	  ,null)
;
