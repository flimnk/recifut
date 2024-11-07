package com.example.demo.infra.excepitions;

public class ValidaRegraDeNegocios  extends  RuntimeException{
    public  ValidaRegraDeNegocios(String msg){
        super(msg);
    }
}