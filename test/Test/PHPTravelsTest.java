/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author nguye
 */
public class PHPTravelsTest {
    
    @Test
    public void checkPHPLogin_FullNameShown() throws InterruptedException{
        String chromeDriverPath = "chromedriver.exe";//trình điều khiển chrome
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);//tạo kết nối tương tác với trình duyệt
        WebDriver myBrowser = new ChromeDriver();//gọi instance chrome lên
        myBrowser.get("https://www.phptravels.net/login/");//gõ url
        myBrowser.manage().window().maximize();
        
        WebElement userName = myBrowser.findElement(By.name("username"));
        userName.sendKeys("nguyenlinhtt2cbl@gmail.com");
        
        WebElement password = myBrowser.findElement(By.name("password"));
        password.sendKeys("linh1031999");
        
        WebElement button = myBrowser.findElement(By.xpath("//form[@id='loginfrm']//button"));
        button.click();
        
        Thread.sleep(3000);
        //khi tien trinh truoc chua kip xu li xong ben browser
        //browser la 1 thread luong khac, khac thread minh dang dung
        //minh cho 1 ti dong bo, cho browser login xong,tra ve trang dashboard roi moi tim Hi,...
        WebElement name = myBrowser.findElement(By.xpath("//div[@class='col-md-8']//h3"));
        //System.out.println("name: " + name.getText());
        assertEquals("Hi, Chu Linh", name.getText());
    }
    //nang cap, day dam user/pass title ra thanh bo tham so, va truyen vao
    //neu tat ca mau xanh, tinh nang login va chao user la on
}
