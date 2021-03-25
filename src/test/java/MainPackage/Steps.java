package MainPackage;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Steps {

    public WebDriver driver;
	private static String PAGE_URL = "https://www.migros.com.tr/";
	private GenericMethods gm;

	
	
	
	@BeforeTest
    public void setUp(){
        
    driver = DriverUtills.getDriver();
	gm = new GenericMethods(driver);

    }
    

	@Test
	public void CaseSteps() throws Exception {
		
		driver.get(PAGE_URL);
		driver.manage().window().maximize();
		
		//Verify the page is opening
		String expectedfirstpageurl = "https://www.migros.com.tr/";
		String actualfirtpageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualfirtpageUrl, expectedfirstpageurl, "Page is not opened");
		
		
		//Teslimat adresi seçimi yapma adımının ilk başta girip ürün seçme adımını sonradan gireceğim. Farklı olarak Cookieye değer göndererekte önce adresi dolu getirebiliriz.
		driver.findElement(By.xpath("//header/div[2]/div[1]/div[1]/div[1]/div[2]/span[1]/span[1]"));
		sleep(3000);
		driver.findElement(By.xpath("//fe-selectable-card[@id='delivery-options-modal_store']/mat-card[@class='mat-mdc-card mdc-card']//div[.=' Mağazadan Alacağım ']"));
		sleep(3000);
		driver.findElement(By.xpath("//span[@class=\"mat-mdc-select-placeholder ng-tns-c173-1 ng-star-inserted\"]"));
		driver.findElement(By.xpath("//div[@id='mat-select-0-panel']/div/mat-option[1]/span[@class='mdc-list-item__text']"));
		driver.findElement(By.xpath("//div[@id='mat-select-2-panel']/div/mat-option[2]/span[@class='mdc-list-item__text']"));
		
		//Elemente hover yaparak linke tıklatma
		Actions action = new Actions(driver);
		WebElement Menu = driver.findElement(By.xpath("/html//div[@id='header-menu']//nav[@class='main-navigation']//ul[@class='header-menu-bar-list']//a[@href='/et-tavuk-balik-c-3']/span"));
		action.moveToElement(Menu).perform();
		
		driver.findElement(By.linkText("Kırmızı Et")).click();
		sleep(1000);
		

		
		//List all element with the post-block post-block--featured post-block--unread and post-block post-block--image post-block--unread
		List<WebElement> products = driver.findElements(By.xpath("//h5[@class=\"title product-card-title\"]"));
        List<WebElement> submitButton = driver.findElements(By.xpath("//div[@class=\"center product-card-center\"]"));
    	
    	

		//for loopu içerisinde listedeki bütün ürünler gezilerek herhangi bir string değer bulunca tıklatıyorum
        for (int i = 0; i < products.size(); i++) {
    	
    	if (products.get(i).getText().contains("Dana Kıymalık"))
    			{
    	
    		submitButton.get(i).click();
    		break;
    		
    	}
		}
        sleep(3000);
        
        driver.findElement(By.id("product-detail-note-textarea")).sendKeys("120 gramlık paketler şeklinde hazırlanmasını istiyorum");

       driver.findElement(By.id("product-detail-add-note-button"));
      
        
        sleep(5000);
        driver.findElement(By.xpath("//body/div[@id='product-page']/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/form[1]/div[1]/div[2]/button[1]")).click();
       
		//Ürün eklendikten sonra kargo uyarı barı kaldırılana kadar ürün eklemeye devam edilip uyarı barı kaybolunca sebete git tıklanır.
		WebElement maxAmount = gm.getElement("progress-bar progress-bar-su", "cssname");
		
		for (int i = 0; i < 1000; i++) {
		
		
		if (maxAmount.isEnabled()) {
			
			gm.getElement("/html//div[@id='cart-bar']//ul//form[@action='/sepetim/guncelle']/table//td[@class='action-td']/div[@class='add-product show']//i[@class='icon plus-orange']", "xpath;");
			
		}	
		if (maxAmount.isDisplayed()) {
			
			driver.findElement(By.linkText("Sepete Git")).click();
		break;

		}
		}
		//Ürünün fiyatını ve sepet özeti fiyatını string değer olarak alıp assertion ile karşılaştırdım. If blogu içerisinde sadece uyarı da verilebilir.
		String SepetOzeti = driver.findElement(By.id("in-cart-next-button")).getText();
		String UrunFiyati = driver.findElement(By.xpath("//body/div[@id='page-area']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]")).getText();
		
		assertEquals(SepetOzeti, UrunFiyati);
	
	}
	@AfterTest
	public void closebrowser() {
		
		driver.quit();

	}
			
	

	// Sleep için kullandığım method
		private void sleep(long m) {
			try {
				Thread.sleep(m);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
}
}