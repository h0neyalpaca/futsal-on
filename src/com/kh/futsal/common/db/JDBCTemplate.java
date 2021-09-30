package com.kh.futsal.common.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class JDBCTemplate {
   
   private static JDBCTemplate instance;
   private static PoolDataSource pds;
   
   private JDBCTemplate() {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         final String DB_URL="jdbc:oracle:thin:@dbfutsal_high?TNS_ADMIN=C:/CODE/Wallet_DBfutsal";
   	     final String DB_USER = "ADMIN";
   	     final String DB_PASSWORD = "sY8@88iytBRU4GJ";
   	     final String CONN_FACTORY_CLASS_NAME="oracle.jdbc.pool.OracleDataSource";
	   	   
  	    pds = PoolDataSourceFactory.getPoolDataSource();
        pds.setConnectionFactoryClassName(CONN_FACTORY_CLASS_NAME);
        pds.setURL(DB_URL);
        pds.setUser(DB_USER);
        pds.setPassword(DB_PASSWORD);
        pds.setConnectionPoolName("JDBC_UCP_POOL");
        
        // Default is 0. Set the initial number of connections to be created
        // when UCP is started.
        pds.setInitialPoolSize(5);

        // Default is 0. Set the minimum number of connections
        // that is maintained by UCP at runtime.
        pds.setMinPoolSize(5);

        // Default is Integer.MAX_VALUE (2147483647). Set the maximum number of
        // connections allowed on the connection pool.
        pds.setMaxPoolSize(20);

        // Default is 30secs. Set the frequency in seconds to enforce the timeout
        // properties. Applies to inactiveConnectionTimeout(int secs),
        // AbandonedConnectionTimeout(secs)& TimeToLiveConnectionTimeout(int secs).
        // Range of valid values is 0 to Integer.MAX_VALUE. .
        pds.setTimeoutCheckInterval(5);

        // Default is 0. Set the maximum time, in seconds, that a
        // connection remains available in the connection pool.
        pds.setInactiveConnectionTimeout(10);
         
      } catch (ClassNotFoundException | SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

  
   public static JDBCTemplate getInstance() {
      if(instance == null) {
         instance = new JDBCTemplate();
      }
      
      return instance;
   }
   
   public Connection getConnection() {
     	  
      Connection conn = null;
      
      try {
         conn = pds.getConnection();
         conn.setAutoCommit(false);
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return conn;
   }
   
   public void commit(Connection conn) {
      try {
         conn.commit();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void rollback(Connection conn) {
      try {
         conn.rollback();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void close(Connection conn) {
      try {
         if(conn != null && !conn.isClosed()) {
            conn.close();
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void close(Statement stmt) {
      try {
         if(stmt != null && !stmt.isClosed()) {
            stmt.close();
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void close(ResultSet rset) {
      try {
         if(rset != null && !rset.isClosed()) {
            rset.close();
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void close(Statement stmt, Connection conn) {
      close(stmt);
      close(conn);
   }
   
   public void close(ResultSet rset, Statement stmt) {
      close(rset);
      close(stmt);
   }
   
   public void close(ResultSet rset, Statement stmt, Connection conn) {
      close(rset);
      close(stmt);
      close(conn);
   }
   
   
   
   
   
   
   
   
   
   
   
   


}