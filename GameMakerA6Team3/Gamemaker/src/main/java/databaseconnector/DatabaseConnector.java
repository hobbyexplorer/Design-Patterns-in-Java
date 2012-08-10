package databaseconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;


public class DatabaseConnector
{

	private static DatabaseConnector dBaseConn = null; 
	
  private static String dbURL = "jdbc:derby://tintin.cs.indiana.edu:1527/team03GameMakerTestDB2;create=true;user=gamemaker_user;password=gamemaker_pw;";
  // private static String dbURL = "jdbc:derby://localhost:1527/team03GameMakerTestDB2;create=true;user=gamemaker_user;password=gamemaker_pw;";
   //private static String dbURL = "jdbc:derby://129.79.49.221:1527/team03GameMakerTestDB2;create=true;user=gamemaker_user;password=gamemaker_pw;";
   private static String tableGame = "GAME";
   private static String tableGameVersion = "GAME_DATA";
   private static String tableUserScore = "SCORE";
   // jdbc Connection
   private static Connection conn = null;
   private static Statement stmtInsert = null,stmtUpdate = null,stmtSelect = null;
 
   
   private DatabaseConnector() {
	// TODO Auto-generated constructor stub
}
   
   public static DatabaseConnector getInstance(){
	   if(dBaseConn == null){
		   dBaseConn = new DatabaseConnector();
	   }
	   return dBaseConn;
   }
   
  /* public void main(String[] args)
   {
    //   createConnection();
     //  insertGameRecord(5, "LaVals", "Berkeley");
      // selectGames();
       //shutdown();
   }*/
   
   public void createConnection()
   {
       try
       {
           Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
           //Get a connection
           conn = DriverManager.getConnection(dbURL);
       }
       catch (Exception except)
       {
           except.printStackTrace();
       }
   }
   
   public void insertGameRecord(String gameName, String xml_data, long userId, String version, boolean isPublished)
   {
       try
       {
    	   long gameRowCount=0,gameDataRowCount=0,counter=0;
    	   boolean newGame=false;
    	   int published=0;
    	   String publishversion="0";
    	  
    	    stmtSelect = conn.createStatement();
           ResultSet result = stmtSelect.executeQuery("Select count(*) As Number from "+tableGame+ " where USER_ID = " + userId
        		   + " and GAME_NAME = '" + gameName + "'");
           while (result.next())
           {
        	   counter = result.getInt("Number");
           }
           if(counter==0)
        	   newGame = true;
           else
        	   newGame = false;
           if(isPublished)
    	   {
    		   published=1;
    		   publishversion=version;
    	   }
           if(newGame)
           {
	    	   stmtSelect = conn.createStatement();
	    	   ResultSet resultcount = stmtSelect.executeQuery("Select max(GAME_ID) As Number from "+tableGame);
	    	   while (resultcount.next())
	    		   gameRowCount = resultcount.getLong("Number");
	    	   gameRowCount++;
	           stmtInsert = conn.createStatement();
	           stmtInsert.executeUpdate("insert into "+tableGame+" (GAME_ID,IS_PUBLISHED,GAME_NAME,PUBLISHED_VERSION,USER_ID, AVERAGE_RATING) values (" + gameRowCount + ","+published+",'" + gameName + "',"+publishversion+","+userId+",0.0)" );
	           stmtInsert.close();
	          
	           stmtSelect = conn.createStatement();
	    	   ResultSet gameDataCount = stmtSelect.executeQuery("Select max(GAME_DATA_ID) As Number from "+ tableGameVersion);
	    	   while (gameDataCount.next())
	    		   gameDataRowCount = gameDataCount.getLong("Number");
	    	   gameDataRowCount++;
	           stmtInsert = conn.createStatement();
	           stmtInsert.executeUpdate("insert into "+tableGameVersion+" (GAME_DATA_ID,GAME_ID,VERSION,GAME_DATA,SUBMISSION_TIMESTAMP) values ("+gameDataRowCount + "," + gameRowCount + ","+version+",'" + xml_data + "',CURRENT TIMESTAMP)");
	           stmtInsert.close();
           }
           else
           {
        	   stmtSelect = conn.createStatement();
               ResultSet gameIdResult = stmtSelect.executeQuery("Select GAME_ID from "+tableGame+" where USER_ID = " + userId
            		   + " and GAME_NAME = '" + gameName + "'");
               int gameId=0;
               while(gameIdResult.next())
               gameId = gameIdResult.getInt("GAME_ID");

	           stmtSelect = conn.createStatement();
	    	   ResultSet gameDataCount = stmtSelect.executeQuery("Select max(GAME_DATA_ID) As Number from "+tableGameVersion);
	    	   while (gameDataCount.next())
	    		   gameDataRowCount = gameDataCount.getLong("Number");
	    	   gameDataRowCount++;
	           stmtInsert = conn.createStatement();
	           stmtInsert.executeUpdate("insert into "+tableGameVersion+" (GAME_DATA_ID,GAME_ID,VERSION,GAME_DATA,SUBMISSION_TIMESTAMP) values ("+gameDataRowCount + "," + gameId + ","+version+",'" + xml_data + "',CURRENT TIMESTAMP)");
	           if(isPublished)
	           {
	        	   stmtUpdate = conn.createStatement();
	        	   stmtUpdate.executeUpdate("update "+ tableGame +  " set IS_PUBLISHED = " + published + ", PUBLISHED_VERSION = "+ publishversion + " where GAME_NAME = '"
	        			   + gameName + "' and USER_ID = " + userId);
	        	   stmtUpdate.close();
	           }
	           
	           stmtInsert.close();
	           stmtSelect.close();
	          
               
           }
       }
       catch (SQLException sqlExcept)
       {
           sqlExcept.printStackTrace();
       }
   }
   
   public String loadGameRecord(long gameId, int version, long userId)
   {
	   String xml_data="";
	   try
       {
	   stmtSelect = conn.createStatement();
	   ResultSet loadedGame = stmtSelect.executeQuery("select * from " + tableGameVersion + 
	    " where GAME_ID = " + gameId + " and VERSION = " + version );
	   while(loadedGame.next())
	   xml_data = loadedGame.getString("GAME_DATA");
	   stmtSelect.close();
	   
       }
	   catch(SQLException sqlExcept)
	   {
		   sqlExcept.printStackTrace();
	   }
	   return xml_data;
   }
   public ArrayList<String[]> getGameRecords(long userId)
   {
       ArrayList<String[]> gameRecordList = new ArrayList<String[]>();
      
	   try
       {
           stmtSelect = conn.createStatement();
           ResultSet gameIdList = stmtSelect.executeQuery("Select * from " + tableGame + " where USER_ID=" + userId);
           String gameId = "", gameName = "";
           while (gameIdList.next())
           {
        	   gameId = gameIdList.getString("GAME_ID");
        	   gameName = gameIdList.getString("GAME_NAME");
        	   stmtSelect = conn.createStatement();
        	   ResultSet gameVersionList = stmtSelect.executeQuery("Select MAX(VERSION)  As Latest_Version " +
        	   		"from "+ tableGameVersion +" where GAME_ID = "+ gameId );
        	   while(gameVersionList.next())
        	   {
        		   String version;
        		   version = gameVersionList.getString("Latest_Version");
        		   String[] gameRecord = new String[2];
        		  
        		   gameRecord[0] = gameName;
        		   gameRecord[1] = version;
        		   gameRecordList.add(gameRecord);
        		   
        	   }
           }
          
           stmtSelect.close();
           return gameRecordList;
       }
       catch (SQLException sqlExcept)
       {
           sqlExcept.printStackTrace();
       }
       
      return null;
   }
   public ArrayList<String[]> getLoadGameRecords(long userId)
   {
	   ArrayList<String[]> gameRecordList = new ArrayList<String[]>();
	   try
       {
           stmtSelect = conn.createStatement();
           ResultSet gameIdList = stmtSelect.executeQuery("Select * from " + tableGame + " where USER_ID=" + userId);
           String gameId = "", gameName = "";
           while (gameIdList.next())
           {
        	   gameId = gameIdList.getString("GAME_ID");
        	   gameName = gameIdList.getString("GAME_NAME");
        	   stmtSelect = conn.createStatement();
        	   ResultSet gameVersionList = stmtSelect.executeQuery("Select * " +
        	   		"from "+ tableGameVersion +" where GAME_ID = "+ gameId );
        	   while(gameVersionList.next())
        	   {
        		   String version;
        		   version = gameVersionList.getString("VERSION");
        		   String[] gameRecord = new String[3];
        		   gameRecord[0] = gameId;
        		   gameRecord[1] = gameName;
        		   gameRecord[2] = version;
        		   gameRecordList.add(gameRecord);
        		   
        	   }
           }
           
           stmtSelect.close();
           return gameRecordList;
       }
	   catch (SQLException sqlExcept)
       {
           sqlExcept.printStackTrace();
       }
	   
	   return null;
   
   }
   
   public void saveScores(long userId,long gameId, long score, boolean isWin)
   {
	   long scoreId=0;
	   int win=0;
	   if(isWin)
	   {
		   win=1;
	   }
	   try
	   {
		   stmtSelect = conn.createStatement();
		   ResultSet countRecord = stmtSelect.executeQuery("select max(SCORE_ID) as Number from " + tableUserScore);
		   while(countRecord.next())
		   scoreId = countRecord.getLong("Number");
		   scoreId++;
		   stmtInsert = conn.createStatement();
		   stmtInsert.executeUpdate("insert into "+ tableUserScore + " (SCORE_ID,USER_ID,GAME_ID,SCORE,SUBMISSION_TIMESTAMP,IS_WIN) values (" 
		   + scoreId + "," + userId + "," + gameId + "," + score + ", CURRENT TIMESTAMP , " + win + ")"  );
		   stmtSelect.close();
		   stmtInsert.close();
		   
	   }
	   catch (SQLException sqlExcept)
       {
           sqlExcept.printStackTrace();
       }
	   
   }
   public void selectGames()
   {
       try
       {
           stmtSelect = conn.createStatement();
           ResultSet results = stmtSelect.executeQuery("select * from " + tableGameVersion);
           ResultSetMetaData rsmd = results.getMetaData();
           int numberCols = rsmd.getColumnCount();
           for (int i=1; i<=numberCols; i++)
           {
               //print Column Names
               System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
           }
 
          // System.out.println("\n-------------------------------------------------");
 
           while(results.next())
           {
               int id = results.getInt(1);
               String save = results.getString(2);
               String gameName = results.getString(3);
               //System.out.println(id + "\t\t" + save + "\t\t" + gameName);
           }
           results.close();
           stmtSelect.close();
       }
       catch (SQLException sqlExcept)
       {
           sqlExcept.printStackTrace();
       }
   }
   
   public void shutdown()
   {
       try
       {
           if (stmtInsert != null)
           {
        	   stmtInsert.close();
        	  
        	  
           }
           if(stmtSelect!=null)
           {
        	   stmtSelect.close();
           }
           if(stmtUpdate!=null)
           {
        	   stmtUpdate.close();
           }
           if (conn != null)
           {
               DriverManager.getConnection(dbURL + ";shutdown=true");
               conn.close();
           }           
       }
       catch (SQLException sqlExcept)
       {
           
       }
 
   }
}