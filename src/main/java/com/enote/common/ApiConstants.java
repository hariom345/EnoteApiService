package com.enote.common;

public interface ApiConstants {

   public String ENOTE_ROOT_API = "/api/v1";
   
   public String USER_API = ENOTE_ROOT_API + "/users";

   public String AUTH_API = ENOTE_ROOT_API + "/auth";

   public String NOTE_API = ENOTE_ROOT_API + "/notes";
   
   //Category
   public String CATEGORY_ROOT_API = ENOTE_ROOT_API + "/category";
   public String GET_CATEGORY_BY_ID ="/{id}";
   public String ADD_CATEGORY_DETAILS = "";
   public String UPDATE_CATEGORY_DETAILS = "/update/{id}";
   public String DELETE_CATEGORY_DETAILS = "/delete/{id}";
   
   //Note
   public String Note_ROOT_API = ENOTE_ROOT_API + "/note";
   public String GET_Note_BY_ID ="/{id}";
   public String CREATE_NOTE = "";
   public String UPDATE_NOTE = "/update/{id}";
   public String DELETE_NOTE = "/delete/{id}";

   
  
}
