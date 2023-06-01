package com.example.demo.ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

public class TestUIUtils {

    /**
     * 表示部分をスクリーンショット
     *
     * @param driver ウェブドライバー
     * @param path 保存先パス
     * @param fileName 保存先ファイル名
     * @throws IOException
     */
    public static void screenShot(WebDriver driver, String path, String fileName) throws IOException{
        Duration duration = Duration.ofSeconds(30);

        // 暗黙的な待機の設定（最大30秒待つ）
        driver.manage().timeouts().implicitlyWait(duration);
        // フレームを基本に戻す
        driver.switchTo().defaultContent();
        // スクリーンショットを実行
        TakesScreenshot ts = (TakesScreenshot) new Augmenter().augment(driver);
        Path from = Paths.get(ts.getScreenshotAs(OutputType.FILE).toURI());
        Path to = Paths.get(path + "\\" + fileName + ".png");
        // ファイルの移動（ファイルが存在する場合は既存のファイルを置換）
        Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * キャプチャ用のフォルダを指定の場所に作成
     *
     * @param dirPath 保存先パス
     * @param dirName 保存先ファイル名
     * @return
     * @throws IOException
     */
    public static String mkdir(String dirPath, String dirName) throws IOException{
        String path = Paths.get(dirPath, dirName).toString();
        // ファルダが存在しない場合
        if(Files.notExists(Paths.get(dirPath,  dirName))) {
        	// フォルダ作成
            Files.createDirectories(Paths.get(dirPath, dirName));
        }
        return path;
    }
    
    /**
     * 実行結果をエクセルファイルで保存
     * @param path 保存先パス
     * @param fileName 保存先ファイル名
     * @throws IOException
     */
    public static void saveExcel(String path, String fileName) throws IOException {
    	FileInputStream fileInputStream;
    	Workbook workbook;
    	// エビデンス.xlsxがある場合
    	if(Files.exists(Paths.get(path + "\\"+ "エビデンス.xlsx"))) {
    		fileInputStream = new FileInputStream(path + "\\"+ "エビデンス.xlsx");
    		workbook = new XSSFWorkbook(fileInputStream);
    	}
    	else {
    		workbook = new XSSFWorkbook();
    	}
    	captchaSheet(workbook, path, fileName);
    }
    
    /**
     * キャプチャをエクセルシートに貼り付ける
     * @param workbook ワークブック
     * @param path 保存先パス
     * @param fileName 保存先ファイル名
     * @throws IOException
     */
    public static void captchaSheet(Workbook workbook, String path, String fileName) 
    		throws IOException{
	    Sheet sheet = workbook.getSheet(fileName);
	    // 既存のシートがある場合は削除
	    if(sheet != null) {
	    	workbook.removeSheetAt(workbook.getSheetIndex(sheet));
	    }
	    // シートを作成
	    sheet = workbook.createSheet(fileName); 
	    // 画像ファイルのパスを指定して入力ストリームを作成	
		InputStream imageStream = new FileInputStream(path + "\\" + fileName + ".png"); 
		// 入力ストリームからバイト配列に読み込む
		byte[] imageBytes = IOUtils.toByteArray(imageStream); 
		// 画像をワークブックに追加し、インデックスを取得
		int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_JPEG); 

		CreationHelper helper = workbook.getCreationHelper();
		// 描画オブジェクトを作成
		Drawing<?> drawing = sheet.createDrawingPatriarch(); 
		// イメージのアンカー作成
		ClientAnchor anchor = helper.createClientAnchor(); 
		// イメージを挿入するセルの開始列インデックス
		anchor.setCol1(1); 
		// イメージを挿入するセルの開始行インデックス
		anchor.setRow1(1); 
		// イメージを描画オブジェクトに追加
		Picture picture = drawing.createPicture(anchor, pictureIdx); 
		// イメージをセルのサイズに合わせてリサイズ
		picture.resize(1.0, 1.0); 
		// Excelファイルのパスを指定して出力ストリームを作成
		FileOutputStream fileOut = new FileOutputStream(path + "\\"+ "エビデンス.xlsx"); 
		// ワークブックを出力ストリーム
		workbook.write(fileOut); 
		// ストリームをクローズ
		fileOut.close();     	
    }
}

