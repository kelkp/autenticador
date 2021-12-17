package com.company.flow;

import com.company.constants.ClientConstants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.util.Arrays;

public class SendStartsRequest {

    public JSONObject start(DatagramSocket clientSocket, DatagramPacket request, DatagramPacket receiver) throws IOException, ParseException {


        clientSocket.send(request);

        while(Arrays.equals(receiver.getData(), ClientConstants.BUFFER)) try {
            clientSocket.receive(receiver);
        } catch (SocketTimeoutException e) {
            clientSocket.send(request);
        }

        String modifiedSentence = new String(receiver.getData());

        JSONParser parser = new JSONParser();
        JSONObject receivedJson = (JSONObject)parser.parse(modifiedSentence);

        return  receivedJson;
    }
}
