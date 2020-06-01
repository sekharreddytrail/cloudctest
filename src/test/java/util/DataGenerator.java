package util;

import java.util.HashMap;
import java.util.Map;

public class DataGenerator {


    public Map<String, Object> dataforuser() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("login_id", "sekharreddy.04.08@gmail.com");
        params.put("api_key", "04658ca0b2c89e86e9a7dcb3b7743608ee7ee7162d4eed073ce0285a8c9fa533");

        return params;
    }


    public Map<String, Object> dataforquote() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("buy_currency", "USD");
        params.put("sell_currency", "GBP");
        params.put("fixed_side", "sell");
        params.put("amount", "1000");
        params.put("conversion_date_preference", "earliest");


        return params;
    }


}
