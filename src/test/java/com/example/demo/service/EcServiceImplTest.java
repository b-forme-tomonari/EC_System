package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.impl.EcServiceImpl;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * EcServiceImplのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
        })
@DisplayName("EcServiceImplテスト")
public class EcServiceImplTest {

	@Autowired
	private EcServiceImpl ecServiceImpl;
	
	@Test
	@DisplayName("例外テスト")
	@DatabaseSetup("/xml/testData.xml")
	public void ExceptionTest() {
		MUser user = new MUser();
		user.setUserId("test");
		user.setPassword("$2a$10$5dQTDVAJHtr5UBV33mEro..1D77BhjTjxBMIV8b6xoKAsiieGXwrS");
		user.setName("test");
		user.setEmail("test@home.com");
		user.setDateOfBirth(new Date());
		user.setZipCode("101-0001");
		user.setAddress("東京都千代田区千代田1番1号");
		assertThrows(DataAccessException.class,
				() -> ecServiceImpl.insertOne(user));
	}
	
	@Test
	@DisplayName("getLoginUserのテスト")
	public void getLoginUserTest() {
		Optional<MUser> user = ecServiceImpl.getLoginUser("general");
		
		assertAll(
			() -> assertNotNull(user),
            () -> assertEquals("general", user.get().getUserId())
        );
	}
	
	@Test
	@DisplayName("getUserOneのテスト")
	@DatabaseSetup("/xml/testData.xml")
	public void getUserOneTest() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		MUser user = ecServiceImpl.getUserOne("test");
		
		assertAll(
			() -> assertNotNull(user),
            () -> assertEquals("test", user.getUserId()),
            () -> assertEquals("test", user.getName()),
            () -> assertEquals("test@sample.com", user.getEmail()),
            () -> assertEquals("2013-12-21", dateFormat.format(user.getDateOfBirth())),
            () -> assertEquals("101-0001", user.getZipCode()),
            () -> assertEquals("東京都千代田区千代田1番1号", user.getAddress())
        );
	}
	
	@Test
	@DisplayName("getUserAllのテスト")
	public void getUserAllTest() {
		List<MUser> userList = ecServiceImpl.getUserAll();
		
		assertEquals(2, userList.size());
		
	}
	    
	@Test
	@DisplayName("insertOneテスト")
	public void insertOneTest() {
		MUser user = new MUser();
		user.setUserId("test2");
		user.setPassword("$2a$10$5dQTDVAJHtr5UBV33mEro..1D77BhjTjxBMIV8b6xoKAsiieGXwrS");
		user.setName("test2");
		user.setEmail("test2@home.com");
		user.setDateOfBirth(new Date());
		user.setZipCode("101-0001");
		user.setAddress("東京都千代田区千代田1番1号");
		int result = ecServiceImpl.insertOne(user);
		
		assertEquals(1, result);
	}
    
	@Test
	@DisplayName("updateOneテスト")
	@DatabaseSetup("/xml/testData.xml")
	public void updateOneTest() {
		MUser user = new MUser();
		user.setUserId("test");
		user.setPassword("$2a$10$5dQTDVAJHtr5UBV33mEro..1D77BhjTjxBMIV8b6xoKAsiieGXwrS");
		user.setName("test");
		user.setEmail("test@home.com");
		user.setDateOfBirth(new Date());
		user.setZipCode("101-0001");
		user.setAddress("東京都千代田区千代田1番1号");
		int result = ecServiceImpl.updateOne(user);
		
		assertEquals(1, result);
	}
	
	@Test
	@DisplayName("insertUserRoleOneテスト")
	public void insertUserRoleOneTest() {
		int result = ecServiceImpl.insertUserRoleOne("test");
		
		assertEquals(1, result);
	}
    
	@Test
	@DisplayName("findGoodsManyテスト")
	public void findGoodsManyTest() {
		List<MGoods> goodsList = ecServiceImpl.findGoodsMany();
		
		assertEquals(12, goodsList.size());
	}
    
	@Test
	@DisplayName("findGoodsOneテスト")
	@DatabaseSetup("/xml/testData.xml")
	public void findGoodsOneTest() {
		MGoods goods = ecServiceImpl.findGoodsOne("cart20");
		
		assertAll(
			() -> assertNotNull(goods),
            () -> assertEquals("cart20", goods.getGoodsId()),
            () -> assertEquals("サーバー", goods.getGoodsName()),
            () -> assertEquals(20000, goods.getPrice()),
            () -> assertEquals(100, goods.getQuantity()),
            () -> assertEquals("image/Server20.jpg", goods.getGoodsImage()),
            () -> assertEquals("高性能サーバーです。", goods.getGoodsExplanation())
        );
	}
	
	@Test
	@DisplayName("insertGoodsOneテスト")
	public void insertGoodsOneTest() {
		MGoods goods = new MGoods();
		goods.setGoodsId("cart21");
		goods.setGoodsName("サーバー");
		goods.setPrice(20000);
		goods.setQuantity(100);
		goods.setGoodsImage("image/Server21.jpg");
		goods.setGoodsExplanation("高性能サーバーです。");
		int result = ecServiceImpl.insertGoodsOne(goods);
		
		assertEquals(1, result);
	}
	
	@Test
	@DisplayName("updateGoodsOneテスト")
	@DatabaseSetup("/xml/testData.xml")
	public void updateGoodsOneTest() {
		MGoods goods = new MGoods();
		goods.setGoodsId("cart20");
		goods.setGoodsName("サーバー");
		goods.setPrice(30000);
		goods.setQuantity(200);
		goods.setGoodsImage("image/Server20.jpg");
		goods.setGoodsExplanation("高性能サーバーです。");
		int result = ecServiceImpl.updateGoodsOne(goods);
		
		assertEquals(1, result);
	}
    
	@Test
	@DisplayName("deleteGoodsOneテスト")
	@DatabaseSetup("/xml/testData.xml")
	public void deleteGoodsOneTest() {
		MGoods goods = new MGoods();
		goods.setGoodsId("cart20");
		goods.setGoodsName("サーバー");
		goods.setPrice(20000);
		goods.setQuantity(100);
		goods.setGoodsImage("image/Server20.jpg");
		goods.setGoodsExplanation("高性能サーバーです。");
		int result = ecServiceImpl.deleteGoodsOne(goods);
		
		assertEquals(1, result);
	}
    
	@Test
	@DisplayName("findHistoryManyテスト")
	public void findHistoryManyTest() {
		List<MHistory> historyList = ecServiceImpl.findHistoryMany("admin");
		
		assertEquals(12, historyList.size());
	}
    
	@Test
	@DisplayName("insertHistoryOneテスト")
	public void insertHistoryOneTest() {
		MHistory history = new MHistory();
		history.setPurchaseId("test20230111153611");
		history.setGoodsId("cart5");
		history.setUserId("test");
		history.setQuantity(1);
		history.setPurchaseDate(new Date());
		history.setPrice(2000);
		int result = ecServiceImpl.insertHistoryOne(history);
		
		assertEquals(1, result);
	}
    
	@Test
	@DisplayName("findBillingOneテスト")
	@DatabaseSetup("/xml/testData.xml")
	public void findBillingOneTest() {
		MBilling bill = ecServiceImpl.findBillingOne("test");
		
		assertAll(
			() -> assertNotNull(bill),
            () -> assertEquals("test", bill.getUserId()),
            () -> assertEquals("test@sample.com", bill.getBillingName()),
            () -> assertEquals("555-5555", bill.getBillingZipCode()),
            () -> assertEquals("大阪府吹田市山手町３丁目３−３５", bill.getBillingAddress()),
            () -> assertEquals("072-990-0000", bill.getBillingNumber())
        );
	}

	@Test
	@DisplayName("insertBillingOneテスト")
	public void insertBillingOneTest() {
		MBilling bill = new MBilling();
		bill.setUserId("hogehoge");
		bill.setBillingName("hoge@sample.com");
		bill.setBillingZipCode("555-5555");
		bill.setBillingAddress("大阪府吹田市山手町３丁目３−３５");
		bill.setBillingNumber("072-990-1111");
		int result = ecServiceImpl.insertBillingOne(bill);
		
		assertEquals(1, result);	
	}
    
	@Test
	@DisplayName("updateBillingOneテスト")
	@DatabaseSetup("/xml/testData.xml")
	public void updateBillingOneTest() {
		MBilling bill = new MBilling();
		bill.setUserId("test");
		bill.setBillingName("test@sample.com");
		bill.setBillingZipCode("555-5555");
		bill.setBillingAddress("大阪府吹田市山手町３丁目３−３５");
		bill.setBillingNumber("072-990-1111");
		int result = ecServiceImpl.updateBillingOne(bill);
		
		assertEquals(1, result);
	}
}
