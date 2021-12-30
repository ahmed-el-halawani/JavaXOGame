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
class UserGameDetailsCrud implements ICrud<UserGameDetails>{
    
    @Override
    public void add(UserGameDetails entity) throws JSONException, IOException{
        System.out.print(entity);
        
        
        Connection con = null;
         try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
                String id = UUID.randomUUID().toString();
                PlayerDetailsCrud player = new PlayerDetailsCrud(in, out);
                PlayerDetails playerOne = player.add(entity.getPlayerOneDetails());
                if(playerOne==null){
                     out.writeInt(-1);
                    return;
                }
                PlayerDetails playerTwo = player.add(entity.getPlayerTwoDetails());
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
            } catch (IOException ex) {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    @Override
    public void update(String id, Entities.UserGameDetails entity) throws JSONException, IOException {
        JSONObject params = new JSONObject(id);
        

        String id2 = params.getString("id");
        System.out.print("id: "+id2+"entity: "+entity);

        out.writeInt(1);
    }

    @Override
    public void delete(String id) throws JSONException, IOException{
        JSONObject params = new JSONObject(id);

        System.out.println(params.getString("id"));

        out.writeInt(1);
    }

    @Override
    public void get(String idParam) throws JSONException, IOException{
        try {
            JSONObject params = new JSONObject(idParam);
            
            String id = params.getString("id");
            
            Connection con = null;
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
            
            PreparedStatement query = con.prepareStatement("SELECT * FROM USERGAMEDETAILS WHERE ID=?");
            query.setString(1,id);
            
            ResultSet rs = query.executeQuery();
            PlayerDetailsCrud playerDetailsCrud = new PlayerDetailsCrud(in,out);

            
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
            
        } catch (SQLException ex) {
            Logger.getLogger(UserGameDetailsCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void getAll() throws JSONException, IOException{
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();

        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
            ArrayList<UserGameDetails> userGamesDetails = new ArrayList<>();
            PreparedStatement query = con.prepareStatement("SELECT * FROM USERGAMEDETAILS");
            ResultSet rs = query.executeQuery();
            PlayerDetailsCrud playerDetailsCrud = new PlayerDetailsCrud(in,out);

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
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void getAllWithId(String idParams) throws JSONException, IOException {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        PlayerDetailsCrud playerDetailsCrud = new PlayerDetailsCrud(in,out);
        JSONObject params = new JSONObject(idParams);

        String id = params.getString("id");
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
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
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void getAllWithUserName(String userNameParam) throws JSONException, IOException {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        PlayerDetailsCrud playerDetailsCrud = new PlayerDetailsCrud(in,out);
        JSONObject params = new JSONObject(userNameParam);

        String userName = params.getString("userName");
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
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
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public UserGameDetailsCrud(DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
   
    private final ObjectMapper obm = new ObjectMapper();
    private DataOutputStream out;
    private DataInputStream in;
    
}
