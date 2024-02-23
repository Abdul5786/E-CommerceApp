package com.AbdulKhaliq.EcommerceApplication.exception;

public class ResponseMessageException extends RuntimeException
{

    public ResponseMessageException()
    {
    }

    public ResponseMessageException(String message)
    {
        super(message);
    }
}
