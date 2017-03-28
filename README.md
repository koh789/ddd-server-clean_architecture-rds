ddd_server
===============
spring4.0


### ddd(クリーンアーキテクチャ)検証  
* 認証などの実装が省かれています。
* ストレージとしてRDSを利用します。


### ddd検証

* トランザクション等細かい処理は簡略しています。
* RDS,DynamoDBのer図は下記を参照

■RDS - ER図  

![RDS ER図](readmeFile/rds_er.png)

■DynamoDB - ER図  
![DynamoDB ER図](readmeFile/dynamodb_er.png)