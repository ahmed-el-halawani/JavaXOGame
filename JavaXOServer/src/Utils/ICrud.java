/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.User;
import Entities.UserGameDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author A H M E D
 */


public interface ICrud<T>{
    public void add(T entity)throws JSONException, IOException;
    public void update(String id,T entity)throws JSONException, IOException;
    public void delete(String id)throws JSONException, IOException;
    public void get(String id)throws JSONException, IOException;
    public void getAll()throws JSONException, IOException;
} 


