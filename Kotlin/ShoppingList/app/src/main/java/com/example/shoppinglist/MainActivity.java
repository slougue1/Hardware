package com.example.shoppinglist;

/*This program essentially edit all variables and data fromShopList.Java
 * */
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recView;
    private List<ShopList> shopList;
    EditText list_name, list_price, shop_location;
    String name, price, shop;
    Button submit;
    @Override
    //Create a reference of the data to be used
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_name = findViewById(R.id.item_name);
        list_price = findViewById(R.id.price);
        shop_location = findViewById(R.id.url);
        submit = findViewById(R.id.submit);
        shopList = new ArrayList<>();

        recView = findViewById(R.id.shoplistRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(linearLayoutManager);

        ShopListAdapter adapter = new ShopListAdapter(shopList);
        recView.setAdapter(adapter);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            //Output the variable
            public void onClick(View view) {
                name = list_name.getText().toString();
                price = list_price.getText().toString();
                shop = shop_location.getText().toString();
                ShopList mUserData = new ShopList(name,price,shop);
                shopList.add(mUserData);
                adapter.notifyDataSetChanged();
                clearFields();
            }
            int selectedItem = -1;
            listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //save the clicked item position
                    // and the when the delete button is pressed, use it.
                    // keep in mind that this is not that good if you are using recycled list
                    selectedItem = position;            }
            });


    }
    public void clearFields(){
        list_name.setText("");
        list_price.setText("");
        shop_location.setText("");
    }
}