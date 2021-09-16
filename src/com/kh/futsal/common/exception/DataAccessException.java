package com.kh.futsal.common.exception;

import com.kh.futsal.common.code.ErrorCode;

//예외처리가 강제되지 않는 UnCheckedException 
//DAO에서 SQLException 대신 DataAccessException을 반환해서, Service단에서 예외처리가 강제되는 것을 방지
public class DataAccessException extends HandlableException{
   
   private static final long serialVersionUID = 521587827126031031L;

   public DataAccessException(Exception e) {
      super(ErrorCode.DATABASE_ACCESS_ERROR,e);
   }
}