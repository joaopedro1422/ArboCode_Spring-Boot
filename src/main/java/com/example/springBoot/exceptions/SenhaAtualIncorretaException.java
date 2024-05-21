package com.example.springBoot.exceptions;

public class SenhaAtualIncorretaException extends Exception{
    public SenhaAtualIncorretaException(){
         super("A senha atual informada está incorreta");
    }
}
