package com.example.booklibraryfinal.exception;

public class UserNameNotFoundExceptionMINE extends RuntimeException{
        public String userName;

        public UserNameNotFoundExceptionMINE(String userName){
            super("Username :" +userName+" does not exist");
            this.userName=userName;
        }
}
