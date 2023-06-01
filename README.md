# ECサイト
## 概要
Spring BootとThymeleafを使用したECサイトです。

## 機能
管理者画面で、商品の月別売り上げとランキング、商品情報の閲覧、登録、編集、削除、顧客情報の確認が可能。

商品一覧の商品画像をクリックすることで、商品詳細が確認できる。

ログイン機能があり、ログイン後は、ユーザー情報の閲覧、編集、購入履歴の確認が可能。

ログイン後はカートの商品を追加、削除、注文ができ、注文確定後はお知らせのメールが送信。

## 採用技術一覧
採用技術は以下の通りです。
|概要|詳細|
|:--|:--|
| プログラミング言語 | Java（ver:11）、JavaScript　|
| フレームワーク | SpringBoot（ver:2.7.6）、MyBatis（ver:3.5.2）、Bootstrap（ver:5.2.3）|
| フロントエンド | HTML、CSS、jQuery（ver:3.3.1）、hymeleaf、Chart.js　|
| データベース | MySQL（ver:8.0.29）、Redis（ver:3.0.504）|
| インフラ | Tomcat（ver:8.5.87）、Apache Http Server（ver:2.4.55）|
| テスト | JUnit、DBUnit（ver:2.7.3）、Selenium（ver:4.8.1）、Apache POI（ver:5.1.0）|
| コードカバレッジ | Jacoco　|
| CIツール | GitHub Actions |
| ビルドツール | Gradle（ver:7.5.1）|
| データベース移行ツール | Flyway（ver:7.10.0）|
| 統合開発環境 | Eclipse（ver:2022-06）|

## 開発時にこだわった点
管理者画面で、Chart.jsで月別売上と売上ランキングを棒グラフで表示。

ユーザー権限で管理者と一般ユーザーを分けており、管理者のみ管理者画面に遷移可能。

セッション管理をRedisで行うことでWebサーバーの負荷を軽減。

Webサーバー(Apache Http Server)とアプリケーションサーバー(Tomcat)を連携することで負荷分散を実現。

Seleniumによる、Chrome、Edge、Firefoxでの自動テストを実現。

自動テストでは、エビデンス画像を取得とエビデンス画像をエクセルシートに貼り付けを行う。

## テスト動画
Seleniumを使用してUIテストを実行している。

https://github.com/b-forme-tomonari/EC_System/assets/106940016/3b874147-d6c9-409f-8741-4b2926e0a345
