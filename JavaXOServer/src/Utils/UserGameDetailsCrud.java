/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.PlayerDetails;
import Entities.User;
import Entities.UserGameDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A H M E D
 */
public 
class UserGameDetailsCrud{
    
    public void add(UserGameDetails entity) throws JSONException, IOException, SQLException{
        System.out.print(entity);
        
        String id = UUID.randomUUID().toString();
        PlayerDetails playerOne = playerDetailsCrud.add(entity.getPlayerOneDetails());
        if(playerOne==null){
             out.writeInt(-1);
            return;
        }
        PlayerDetails playerTwo = playerDetailsCrud.add(entity.getPlayerTwoDetails());
        if(playerTwo==null){
             out.writeInt(-1);
            return;
        }

        id = UUID.randomUUID().toString();
        PreparedStatement query = con.prepareStatement("INSERT INTO USERGAMEDETAILS VALUES(?,?,?,?,?,?,?,?,?)");
        query.setString(1,id);
        query.setString(2,entity.getGameMode().name());
        query.setString(3,entity.getGameDifficultyLvl().name());
        query.setString(4,playerOne.getId());
        query.setString(5,playerTwo.getId());
        query.setString(6,obm.writeValueAsString(entity.getRecord()));
        query.setString(7,obm.writeValueAsString(entity.getGameBordBeforRecording()));
        query.setString(8,obm.writeValueAsString(entity.getGameBord()));
        query.setBoolean(9,entity.isIsRecorded());
        int index = query.executeUpdate();
        if(index==0){
            out.writeInt(-1);
            return;
        }

        out.writeInt(index);
           
    }

    public void update(String id, Entities.UserGameDetails entity) throws JSONException, IOException, SQLException {
        JSONObject params = new JSONObject(id);
        

        String id2 = params.getString("id");
        System.out.print("id: "+id2+"entity: "+entity);

        out.writeInt(1);
    }

    public void delete(String id) throws JSONException, IOException, SQLException{
        JSONObject params = new JSONObject(id);

        System.out.println(params.getString("id"));

        out.writeInt(1);
    }

    public void get(String idParam)throws JSONException, IOException, SQLException{
            JSONObject params = new JSONObject(idParam);
            
            String id = params.getString("id");
            
            
            PreparedStatement query = con.prepareStatement("SELECT * FROM USERGAMEDETAILS WHERE ID=?");
            query.setString(1,id);
            
            ResultSet rs = query.executeQuery();

            
            if(rs.next()){
                out.writeUTF(
                    UserGameDetails.fromResultSet(
                        rs,
                        playerDetailsCrud.get(rs.getString("PLAYERONEDETAILS")),
                        playerDetailsCrud.get(rs.getString("PLAYERTWODETAILS"))
                     ).toJson()
                );
            }else{
                out.writeUTF("null");
            }
            
       
    }

    public void getAll() throws JSONException, IOException, SQLException{
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();

        ArrayList<UserGameDetails> userGamesDetails = new ArrayList<>();
        PreparedStatement query = con.prepareStatement("SELECT * FROM USERGAMEDETAILS");
        ResultSet rs = query.executeQuery();

        while(rs.next()){
            userGamesDetails.add(
                UserGameDetails.fromResultSet(
                   rs,
                   playerDetailsCrud.get(rs.getString("PLAYERONEDETAILS")),
                   playerDetailsCrud.get(rs.getString("PLAYERTWODETAILS"))
                )
            );
        }

         obm.writeValue(out2,  userGamesDetails);

        final byte[] data = out2.toByteArray();
        System.out.println(new String(data));

        out.writeUTF(new String(data));
        
    }
    
    
    public void getAllWithId(String idParams) throws JSONException, IOException, SQLException {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        JSONObject params = new JSONObject(idParams);

        String id = params.getString("id");
        
            ArrayList<UserGameDetails> userGamesDetails = new ArrayList<>();
            PreparedStatement query = con.prepareStatement(
                "SELECT UGD.* FROM "
                + "USERGAMEDETAILS AS UGD,PLAYERDETAILS AS PD  "
                + "WHERE "
                + "(UGD.PLAYERONEDETAILS=PD.ID OR UGD.PLAYERTWODETAILS=PD.ID) AND PD.USER_ID=?"
            );
            query.setString(1,id);
            
            ResultSet rs = query.executeQuery();
            while(rs.next()){
                userGamesDetails.add(
                    UserGameDetails.fromResultSet(
                       rs,
                       playerDetailsCrud.get(rs.getString("PLAYERONEDETAILS")),
                       playerDetailsCrud.get(rs.getString("PLAYERTWODETAILS"))
                    )
                );
            }
            obm.writeValue(out2,  userGamesDetails);

            final byte[] data = out2.toByteArray();
            System.out.println(new String(data));

            out.writeUTF(new String(data));
        
    }
    
    
    public void getAllWithUserName(String userNameParam) throws JSONException, IOException, SQLException {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        JSONObject params = new JSONObject(userNameParam);

        String userName = params.getString("userName");
        
            ArrayList<UserGameDetails> userGamesDetails = new ArrayList<>();
            PreparedStatement query = con.prepareStatement(
                "SELECT UGD.* FROM "
                + "USERGAMEDETAILS AS UGD,PLAYERDETAILS AS PD,USERDATA AS UD  "
                + "WHERE "
                + "(UGD.PLAYERONEDETAILS=PD.ID OR UGD.PLAYERTWODETAILS=PD.ID) AND PD.USER_ID=UD.ID AND UD.USERNAME=?"
            );
            query.setString(1,userName);
            
            ResultSet rs = query.executeQuery();
            while(rs.next()){
                userGamesDetails.add(
                    UserGameDetails.fromResultSet(
                       rs,
                       playerDetailsCrud.get(rs.getString("PLAYERONEDETAILS")),
                       playerDetailsCrud.get(rs.getString("PLAYERTWODETAILS"))
                    )
                );
            }
            obm.writeValue(out2,  userGamesDetails);

            final byte[] data = out2.toByteArray();
            System.out.println(new String(data));

            out.writeUTF(new String(data));
       

    }
    
    public UserGameDetailsCrud(DataInputStream in,DataOutputStream out, Connection con,PlayerDetailsCrud playerDetailsCrud) {
        this.out = out;
        this.in = in;
        this.con = con;
        this.playerDetailsCrud = playerDetailsCrud;
    }
   
    private final ObjectMapper obm = new ObjectMapper();
    private DataOutputStream out;
    private DataInputStream in;
    private  Connection con;
    private PlayerDetailsCrud playerDetailsCrud;

    
}
