package searchengine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbService {
    private DbManager dbManager;

    public DbService(){
        dbManager = new DbManager();
        dbManager.init();
        if(!isTablesExists()){
            createTables();
        }
    }

    public void saveShiftedSentences(ArrayList<ShiftedSentence> sentences, int content_id){
        for(ShiftedSentence index: sentences){
            ResultSet result = dbManager.executeOperation("INSERT INTO `Indexes` (`processed_content`, `target_word`, `point`, `orig_content_id`) " +
                    "VALUES ('"+index.getProcessedSentence()+"', '"+index.getProcessedSentence().split(" ")[0]+"', '"+index.getScore()+"', '"+content_id+"')");
        }
    }

    public ResultSet getSuggestions(String key){
        ResultSet result = dbManager.executeQuery("SELECT DISTINCT `cof`.`url`, `cof`.`content` FROM Indexes as i " +
                "JOIN Contents_of_Indexes as cof ON(cof.id = i.orig_content_id) " +
                "WHERE i.processed_content like \"%"+ key + "%\" " +
                "ORDER BY i.point DESC LIMIT 0,5");
        return result;
    }


    public int saveFullSentence(Sentence sentence) throws SQLException {
        ResultSet result = null;
        try {
            result = dbManager.executeOperation("INSERT INTO `Contents_of_Indexes` (`content`, `url`) " +
                    "VALUES ('" + sentence.getFullSentence() + "', '" + sentence.getUrl() + "')");
            result.next();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result.getInt(1);
    }

    private boolean isTablesExists(){
        boolean finded = false;
        try{
            ResultSet result = dbManager.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME LIKE 'Indexes'");
            if(result.next()){
                if(result.getRow() < 2){
                    finded = true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return finded;
    }
    private void createTables(){
        ResultSet result = null;
        try{
            result = dbManager.executeOperation("CREATE TABLE `Indexes` " +
                    "( `id` INT NOT NULL AUTO_INCREMENT, " +
                    "`processed_content` TEXT NOT NULL , " +
                    "`orig_content_id` INT NOT NULL , " +
                    "`target_word` TEXT NOT NULL , " +
                    "`point` FLOAT NOT NULL , " +
                    "PRIMARY KEY (`id`)) ENGINE = InnoDB;");

            result = dbManager.executeOperation("CREATE TABLE `Contents_of_Indexes` " +
                    "( `id` INT NOT NULL AUTO_INCREMENT, " +
                    "`content` LONGTEXT NOT NULL , " +
                    "`url` TEXT NOT NULL , " +
                    "PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


}
