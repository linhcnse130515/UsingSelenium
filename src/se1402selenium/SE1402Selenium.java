/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se1402selenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author nguye
 */
public class SE1402Selenium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        //testSearchGoogle_ResultsShowed();
        //checkFAPHomepage_LayoutApperance();
        checkPHPLogin_FullNameShown();
    }
    
    
    //ta xây dựng tình huống test thử tính năng search của Google
    //A test case to test the Google search feature
    //Test procedure/steps
    //1. Open a brower, e.g. Chrome
    //2. Key/Type url: https://google.com
    //3. In the search textbox, type: Ahihi đồ ngốc
    //4. Hit/click/enter
    //Expected results:
    // Google shows all sites (URL and short desc) that include the "Ahihi đồ ngốc"
    //keyword
    //vụ này nên ghi trong TestRail
    
    public static void testSearchGoogle_ResultsShowed(){
        String chromeDriverPath = "chromedriver.exe";//trình điều khiển chrome
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);//tạo kết nối tương tác với trình duyệt
        WebDriver myBrowser = new ChromeDriver();//gọi instance chrome lên
        myBrowser.get("https://youtube.com");//gõ url
        myBrowser.manage().window().maximize();
        //mỗi thứ trong cây DOM trang web đc tải về trong trình duyệt đc xem là 1 object thuộc nhóm webElement
        WebElement searchBox = myBrowser.findElement(By.name("search_query"));
        searchBox.sendKeys("nhạc trẻ");
        searchBox.submit();
    }
    
    
    //Test case 2: Check the FAP Homepage
    
    //*Expected result
    //FAP Login page appears with the following info:
    //2 sections: Parent and FUers
    //FUers dropdown shows 6 different campuses: Hoa Lac,TP.HCM, Can Tho, ...(ghi du option)
    //footer: 
    public static void checkFAPHomepage_LayoutApperance(){
        String chromeDriverPath = "chromedriver.exe";//trình điều khiển chrome
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);//tạo kết nối tương tác với trình duyệt
        WebDriver myBrowser = new ChromeDriver();//gọi instance chrome lên
        myBrowser.get("http://fap.fpt.edu.vn/");//gõ url
        myBrowser.manage().window().maximize();
        
        WebElement footer = myBrowser.findElement(By.xpath("//table[@id='cssTable']//p"));
        System.out.println("Footer: " + footer.getText());
        
        WebElement footerLink = myBrowser.findElement(By.xpath("//table[@id='cssTable']//p//a[@href='http://fpt.edu.vn']"));
        System.out.println("Footer Link: " + footerLink.getText());
        
        //kiem tra cai comboBox/dropdown list co du cac options k
        //Dropdown list lien quan 2 loai object
        //select la th ben ngoai, tung option ben trong thi la WebElement
        //select chua 1 list cac element
        WebElement cboCampus = myBrowser.findElement(By.name("ctl00$mainContent$ddlCampus"));
        Select campus = new Select(cboCampus);
        List<WebElement> options = campus.getOptions();
        //options chua danh sach cac the lua chon, minh chi viec in ra getText()
        System.out.println("THe list of campuses");
        for (WebElement x : options) {
            System.out.println(x.getText());
        }
    }
    
    //test case 3:check the login process of phptravels.net
    //to show the fullname of the user
    //Steps/Procedures:
    //1. Open a brower, e.g. Chrome
    //2. Type url: phptravels.net
    //Expected result:
    //The login page is shown with our own account option
    //3.Type user: xxx, passL yyy
    //4.Hit login button
    //Expected results:
    //The user dashboard is shown with the title of: Hi, Nguyen Linh
    
    public static void checkPHPLogin_FullNameShown() throws InterruptedException{
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
        System.out.println("name: " + name.getText());
    }
}
