package server;


import java.sql.Connection;
import java.sql.PreparedStatement;
import shared.Dbconnect;
import shared.Task;

public class DeleteFruitPrice implements Task {
    private String fruit;

    public DeleteFruitPrice(String fruit) {
        this.fruit = fruit;
    }
  
  public String execute() throws Exception {
        String response;
        try (Connection conn = Dbconnect.getConnection()) {
            System.out.println("Deleting fruit: '" + fruit + "'");
            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM fruit WHERE fruit = ?"
            );
            ps.setString(1, fruit);
            ps.executeUpdate();
            response = "SUCCESSFULLY DELETED " + fruit +" FRUIT FROM DATABASE";
        }
        catch(Exception e){
        response = "DB Error: " + e.getMessage();
        }
        
        return response;
    }
}
