package automation.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class DataHelper {

    String PATH = System.getProperty("user.dir") + "/src/main/java/automation/data/";

    public HashMap<String, String> getBillingData() {
        HashMap<String, String> billingData = new HashMap<String, String>();
        billingData.put("firstname", "test");
        billingData.put("lastname", "user");
        billingData.put("email", "testUser@test.com");
        billingData.put("company", "my company");
        billingData.put("country", "Bangladesh");
        billingData.put("state", "Other");
        billingData.put("city", "Dhaka");
        billingData.put("address", "Dhaka, Bangladesh");
        billingData.put("zip", "1216");
        billingData.put("phone", "01234567890");
        return billingData;
    }

    public HashMap<String, String> getCardInfo() {
        HashMap<String, String> cardData = new HashMap<String, String>();
        cardData.put("cardType", "Visa");
        cardData.put("cardholder", "Test User");
        cardData.put("expireMonth", "2");
        cardData.put("expireYear", "2024");
        cardData.put("cardCode", "123");
        cardData.put("cardNumber", "4111111111111111");
        return cardData;
    }

    public String getDynamicEmail() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = myDateObj.format(myFormatObj);
        return "test" + formattedDate + "@test.com";
    }

    public Object[][] getUserRegistrationData(String fileName) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(PATH + fileName));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            List<CSVRecord> records = csvParser.getRecords();

            // removing header values
            records.remove(0);
            Object[][] data = new Object[records.size()][records.get(0).values().length];

            for (int i = 0; i < records.size(); i++) {
                for (int j = 0; j < records.get(i).values().length; j++) {
                    data[i][j] = records.get(i).get(j).trim();
                }
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
