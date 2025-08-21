package com.example.shoppinglist;
/*This program is a list of the data to be linked
 *It first delacre the 3 variable names representing the data
 * then initilised them with the input from the keyboard
 * Then it it use a getter and setter function to retreive them
* */
public class ShopList {
    private String item_name;
    private String item_price;
    //url or shop name
    private String item_shop;

    public ShopList(String name, String shop, String price) {
        this.item_name = name;
        this.item_price = price;
        this.item_shop = shop;
    }

    public String getName() {
        return item_name;
    }

    public void setName(String name) {
        this.item_name = name;
    }

    public String getUrl() {
        return item_shop;
    }

    public void setUrl(String shop) {
        this.item_shop = shop;
    }

    public String getPrice() {
        return item_price;
    }

    public void setPrice(String price) {
        this.item_price = price;
    }
}
