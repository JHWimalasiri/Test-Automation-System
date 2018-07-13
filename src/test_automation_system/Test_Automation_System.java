/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_automation_system;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;



/**
 *
 * @author hwima
 */
public class Test_Automation_System {
    
 public static String driverPath = "C:/selenium-java-3.13.0/";
 public static WebDriver driver;

    public static void checkPrice(int price){
        if(price<= 5000000 && price>=7500000){
            System.out.println("Price is out of range");
        }
    }
    
    public static void checkNoOfBeds(int beds){
        if(beds !=3){
            System.out.println("Invalid no. of beds");
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("launching chrome browser");
		      System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ikman.lk/");

        //click property
        driver.findElement(By.cssSelector("body.on-home-landing > div.app-content > div.home-top > div.container > div.home-focus > div.home-categories > div.row > div.col-6.lg-3:nth-child(2) > a ")).click();

        // click houses
        driver.findElement(By.cssSelector("body > div > div > div > div > div > div > div > div > div > form > div > div > div > ul > li > ul > li > ul.ui-link-tree > li.ui-link-tree-item.cat-411 > a")).click();

        //click Colombo
        driver.findElement(By.cssSelector("body > div > div > div > div > div > div > div > div > div > form > div > div > div > ul > li.ui-link-tree-item.is-active > ul.ui-link-tree > li.ui-link-tree-item.loc-1506 > a")).click();

        //click price
        driver.findElement(By.cssSelector("body > div > div > div > div > div > div > div > div > div > form.ui-form.is-standard.serp-filter-form > div.ui-accordion > div.ui-accordion-item.filter-price > a.ui-accordion-title.t-small")).click();

        //get web element corresponding to the minimum price value input
        WebElement minimum_price = driver.findElement(By.xpath("//*[@id=\"filters[0][minimum]\"]"));

        //get web element corresponding to the maximum price value input
        WebElement maximum_price = driver.findElement(By.xpath("//*[@id=\"filters[0][maximum]\"]"));

        //input values
        minimum_price.sendKeys("5000000");
        maximum_price.sendKeys("7500000");

        //click apply filters
        driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div.ui-accordion-item.filter-price.is-open > div > div:nth-child(6) > div > div > button")).click();

        //click beds
        driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div.ui-accordion-item.filter-enum.filter-bedrooms > a")).click();

        //choose 3 beds
        driver.findElement(By.xpath("//*[@id=\"filters2values-3\"]")).click();

        //get the no. ads of houses with three beds
        WebElement houses = driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div.ui-accordion-item.filter-enum.filter-bedrooms.is-open > div > ul > li.ui-link-tree-item.bedrooms-3 > a > span"));
        System.out.println("No of Ads Found : " + houses.getText());

        int no_houses = Integer.parseInt(houses.getText());

        //get the filtered items
        WebElement items = driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-9 > div > div > div.row.lg-g > div.col-12.lg-9 > div"));
        List<WebElement> itemList = items.findElements(By.className("ui-item"));

        
        int num = 1;

        for(int i=0; i<no_houses/25 + 1; i++){
            
            for (WebElement item : itemList){
                String price = item.findElement(By.className("item-info")).getText().replace("Rs ", "").replace(",","");
                checkPrice(Integer.parseInt(price));
                System.out.println("Ad Number " +num+ " Price is : Rs "+price);
                num++;
                
                String beds = item.findElement(By.className("item-meta")).getText().replace("Beds: ", "").replaceAll(",.*","");
                checkNoOfBeds(Integer.parseInt(beds));
//                System.out.println(beds);
                
            }
            
            //next page
            if(i != no_houses/25){
                driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div.row.lg-g > div > div > div > div > div > a > span"));
            }
            
        }
            
    }
    
}
