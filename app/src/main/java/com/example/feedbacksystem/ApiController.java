package com.example.feedbacksystem;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
     private static final String root_url = "http://192.168.1.163/FeedbackSystem/";
     public static  ApiController clientobject;
     private static Retrofit retrofit;
     //Constructor that will initialize the retrofit object
     ApiController(){
         retrofit = new Retrofit.Builder()
                 .baseUrl(root_url)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

     }
     public static synchronized ApiController getInstance(){
         if (retrofit==null)
             clientobject=new ApiController();
         return clientobject;

     }
     //This function will list out all api mentioned in the class
     apiset getapi(){
         return retrofit.create(apiset.class);
     }

}
