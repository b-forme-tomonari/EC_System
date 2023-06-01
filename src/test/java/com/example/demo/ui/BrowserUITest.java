package com.example.demo.ui;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 自動でブラウザでの動作を確認するクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("UIテスト")
public class BrowserUITest {
	
    // キャプチャ用ファイル配置先
    protected String capturePath;
	
    /**
     * 画面スクロール操作するテスト
     * @param driver
     * @throws InterruptedException
     */
	private void scrollUITest(WebDriver driver)throws InterruptedException{
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);
        
        javascriptExecutor.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);
	}
	
	/**
	 * 画面スクロールするメソッド
	 * @param driver
	 * @param height
	 * @throws InterruptedException
	 */
	private void scrollHeight(WebDriver driver, int height)throws InterruptedException{
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, "+ Integer.valueOf(height).toString() + ");");
        Thread.sleep(1000);
	}
	
	/**
	 * ログイン画面を操作するテスト
	 * @param driver
	 * @param duration
	 * @throws InterruptedException
	 */
	private void loginUITest(WebDriver driver, Duration duration) 
			throws InterruptedException {
        driver.get("http://localhost/ec/login");
        
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(duration);
        //テキストボックスにadmin入力
        driver.findElement(By.id("userId")).sendKeys("admin");

        //テキストボックスにadmin入力
        driver.findElement(By.id("password")).sendKeys("admin") ;
        Thread.sleep(1000);
        //検索ボタンを押下
        driver.findElement(By.id("login")).click();
	}
	
	public void homeUITest(WebDriver driver, Duration duration) 
			throws InterruptedException, IOException {
        // キャプチャ保存フォルダを作成
        String path = TestUIUtils.mkdir(this.capturePath, "evidence");
        String xPathFirst = "/html/body/main/div/div/div[1]/div[";
        String xPathend = "]/div/div/div/div/a";
        String xPathCart = "/html/body/header/div/div/div/button";
        
        driver.get("http://localhost/ec/home");
        driver.manage().timeouts().implicitlyWait(duration);

        for(int i = 0; i < 9; i++) {
        	Thread.sleep(500);
        	scrollHeight(driver, (i / 3) * 600);
            // 画面をキャプチャ
            TestUIUtils.screenShot(driver, path, "home"+ i);
            
        	Thread.sleep(1000);
        	driver.manage().timeouts().implicitlyWait(duration);
        	driver.findElement(By.xpath(xPathFirst + Integer.valueOf(i + 1) + xPathend)).click();
        	Thread.sleep(500);
        }
        
        driver.findElement(By.xpath(xPathCart)).click();
        Thread.sleep(1000);
        
        scrollUITest(driver);
	}
	
	/**
	 * ホーム画面を操作するテスト
	 * @param driver
	 * @param duration
	 * @throws InterruptedException
	 */
	public void homeCartUITest(WebDriver driver, Duration duration) 
			throws InterruptedException {
		
        String xPathFirst = "/html/body/main/div/div/div[1]/div[";
        String xPathend = "]/div/a";
        String xPathInCart = "/html/body/main/div/div/div[3]/div/div/div/div/button";
        String xPathCart = "/html/body/header/div/div/div/button";
        
        driver.get("http://localhost/ec/home");
        driver.manage().timeouts().implicitlyWait(duration);
        
        for(int i = 0; i < 9; i++) {
        	Integer.valueOf(i).toString();
        	Thread.sleep(500);
        	driver.manage().timeouts().implicitlyWait(duration);
            
        	
        	scrollHeight(driver, (i / 3) * 600);
        	
        	Thread.sleep(1000);
        	driver.manage().timeouts().implicitlyWait(duration);
            driver.findElement(By.xpath(xPathFirst + Integer.valueOf(i + 1).toString() + xPathend)).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(xPathInCart)).click();
        }
        
        Thread.sleep(1000);
        driver.findElement(By.xpath(xPathCart)).click();
        Thread.sleep(1000);
        scrollUITest(driver);
	}
	
	/**
	 * 管理者画面を操作するテスト
	 * @param driver
	 * @param duration
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private void adminUITest(WebDriver driver, Duration duration) 
			throws InterruptedException, IOException {
        // キャプチャ保存フォルダを作成
        String path = TestUIUtils.mkdir(this.capturePath, "エビデンス");
		driver.get("http://localhost/ec/home");
        driver.manage().timeouts().implicitlyWait(duration);
        
        
        scrollUITest(driver);

        driver.findElement(By.className("dropdown-toggle")).click();
        
        driver.manage().timeouts().implicitlyWait(duration);
        
        
        List<WebElement> elementList = driver.findElements(By.className("dropdown-item"));
        
        WebElement element = elementList.get(3);
        Thread.sleep(1000);
        element.click();
        
        for(int i = 1; i <= 5 ;i++) {
        	Thread.sleep(1000);
        	List<WebElement> list = driver.findElements(By.className("nav-link"));
            // 画面をキャプチャする
            TestUIUtils.screenShot(driver, path, driver.toString().charAt(0) + "_管理者_"+ i);
            TestUIUtils.saveExcel(path,driver.toString().charAt(0) + "_管理者_"+ i);
        	list.get(i).click();
        }
        
        Thread.sleep(1000);
	}
	
	/**
	 * UIテストのメインメソッド
	 * @param driver
	 */
	public void UITest(WebDriver driver) {
        
        Duration duration = Duration.ofSeconds(5);

        //暗黙的な待機の設定（ブラウザ操作時の要素を見つけるまで最大5秒待つ）
        driver.manage().timeouts().implicitlyWait(duration);
        // 実行ユーザーのデスクトップパスを取得する
        String desktopPath = System.getProperty("user.dir");

        try {
            // デスクトップにキャプチャ保存フォルダを作成
            this.capturePath = TestUIUtils.mkdir(desktopPath, "テスト結果");
        	
            loginUITest(driver, duration);
        	
        	//homeUITest(driver, duration);
        	
        	//homeCartUITest(driver, duration);

        	adminUITest(driver, duration);
            
        } catch(Exception e) {
            System.out.println(e.getMessage());

        } finally {
            //セッションの終了時にブラウザーを終了
            driver.quit();
        }
	}
	
	/**
	 * Chromeでのテスト
	 */
	@DisplayName("Chromeでのテスト")
	public void ChromeTest() {
		//Chromeドライバーを格納したパスをシステム変数にセット
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "C:/chromedriver_win32/chromedriver.exe");
		System.setProperty("webdriver.http.factory", "jdk-http-client");

		UITest(new ChromeDriver());
	}
	
	/**
	 * Edgeでのテスト
	 */
	@DisplayName("Edgeでのテスト")
	public void EdgeTest() {
		//Edgeドライバーを格納したパスをシステム変数にセット
		System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, "C:/edgedriver_win64/msedgedriver.exe");
		System.setProperty("webdriver.http.factory", "jdk-http-client");

		UITest(new EdgeDriver());
	}
			
	/**
	 * Firefoxでのテスト
	 */
	@DisplayName("Firefoxでのテスト")
	public void FirefoxTest() {
		//Firefoxドライバーを格納したパスをシステム変数にセット
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver-v0.32.2-win64/geckodriver.exe");		
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        UITest(new FirefoxDriver());
	}
	
	public static void main(String[] args) {
		BrowserUITest UITest = new BrowserUITest();
		UITest.ChromeTest();
		UITest.EdgeTest();
		UITest.FirefoxTest();
	}
}
