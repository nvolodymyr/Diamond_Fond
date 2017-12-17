package JDBSConection;

import Entity.Diamond;
import Entity.Gem;
import Parsers.DomParser;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbsInputData extends ConnectionToCustomDataBase {

    private static void insertVisualParametrs(String XML_FILE_PATH) {
        File file = new File(XML_FILE_PATH);
        Diamond diamond = DomParser.parse(file);
        PreparedStatement preparedStatement = null;
        String sql = "Insert into visual_parameters (id,color,transparency,countEdge) VALUES(?,?,?,?);";
        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            for (Gem gem : diamond.getGems()) {
                preparedStatement.setString(1, gem.getVisualParameters().getVisualId());
                preparedStatement.setString(2, gem.getVisualParameters().getColor());
                preparedStatement.setInt(3, gem.getVisualParameters().getTransparency());
                preparedStatement.setInt(4,gem.getVisualParameters().getGemCutting());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void insertGem(String XML_FILE_PATH){
        File file = new File(XML_FILE_PATH);
        Diamond diamond=DomParser.parse(file);
        PreparedStatement preparedStatement=null;
        String sql = "Insert into diamond (id,nameDiamond,origin,preciousness,visualParametersID,valueWeight) VALUES(?,?,?,?,?,?);";
try(Connection connection=getConnection()){
    preparedStatement=connection.prepareStatement(sql);
    for(Gem gem:diamond.getGems()){
        preparedStatement.setString(1,gem.getId());
        preparedStatement.setString(2,gem.getName());
        preparedStatement.setString(3,gem.getOrigin());
        preparedStatement.setString(4,gem.getPreciousness().toString());
        preparedStatement.setString(5,gem.getVisualParameters().getVisualId());
        preparedStatement.setDouble(6,gem.getValue());
        preparedStatement.executeUpdate();
    }
}catch (SQLException e){
    e.printStackTrace();
}
    }
    public static void executeTwoPart(String XML_FILE_PATH){
        System.out.print("dataBase connection +++ ");
        insertVisualParametrs( XML_FILE_PATH);
        insertGem( XML_FILE_PATH);
    }
}
