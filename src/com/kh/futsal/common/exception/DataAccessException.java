package com.kh.futsal.common.exception;

import com.kh.futsal.common.code.ErrorCode;

public class DataAccessException extends HandlableException{
   
   private static final long serialVersionUID = 521587827126031031L;

   public DataAccessException(Exception e) {
      super(ErrorCode.DATABASE_ACCESS_ERROR,e);
   }
}