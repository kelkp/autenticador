package com.company;

import com.company.constants.ClientConstants;
import com.company.flow.*;
import com.company.models.AuthReq;
import com.company.models.StartsReq;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Main {

    public static DatagramSocketBuilderFlow datagramSocketBuilderFlow = new DatagramSocketBuilderFlow();
    public static InetAdressGetNameFlow inetAdressGetNameFlow = new InetAdressGetNameFlow();
    public static DatagramPacketBuilderFlow datagramPacketBuilderFlow = new DatagramPacketBuilderFlow();
    public static DatagramReceivedPacketBuilderFlow datagramReceivedPacketBuilderFlow = new DatagramReceivedPacketBuilderFlow();
    public static SendRiverAuthRequest sendRiverAuthRequest = new SendRiverAuthRequest();
    public static SendStartsRequest sendStartsRequest = new SendStartsRequest();

    public static void main(String[] args) throws IOException, ParseException {

        AuthReq authReq = new AuthReq();
        JSONObject authReqJson = authReq.generateJsonBody();

        String riverJsonText = authReqJson.toJSONString();
        byte[] riverPumpOn = riverJsonText.getBytes(StandardCharsets.US_ASCII);
        byte[] riverBuffer = new byte[144];

        StartsReq startsReq = new StartsReq();
        JSONObject startsReqJson = startsReq.generateJsonBody();

        String startsjsonText = startsReqJson.toJSONString();
        byte[] startsPumpOn = startsjsonText.getBytes(StandardCharsets.US_ASCII);
        byte[] startsbuffer = new byte[144];


        DatagramSocket clientSocket = datagramSocketBuilderFlow.datagramSocketbuilder();

        InetAddress IPAddress = inetAdressGetNameFlow.inetAddressGetName(ClientConstants.SERVER_HOST_NAME);

        DatagramPacket receivePacketRiver1 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(riverBuffer, riverBuffer.length);
        DatagramPacket receivePacketRiver2 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(riverBuffer, riverBuffer.length);
        DatagramPacket receivePacketRiver3 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(riverBuffer, riverBuffer.length);
        DatagramPacket receivePacketRiver4 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(riverBuffer, riverBuffer.length);

        DatagramPacket startResponse = datagramReceivedPacketBuilderFlow.datagramPacketReceived(riverBuffer, riverBuffer.length);

        DatagramPacket datagramPacket1 = datagramPacketBuilderFlow.datagramPacketBuilder(riverPumpOn, riverPumpOn.length, IPAddress, ClientConstants.SERVER_PORT1);
        DatagramPacket datagramPacket2 = datagramPacketBuilderFlow.datagramPacketBuilder(riverPumpOn, riverPumpOn.length, IPAddress, ClientConstants.SERVER_PORT2);
        DatagramPacket datagramPacket3 = datagramPacketBuilderFlow.datagramPacketBuilder(riverPumpOn, riverPumpOn.length, IPAddress, ClientConstants.SERVER_PORT3);
        DatagramPacket datagramPacket4 = datagramPacketBuilderFlow.datagramPacketBuilder(riverPumpOn, riverPumpOn.length, IPAddress, ClientConstants.SERVER_PORT4);

        DatagramPacket startRequestPacket = datagramPacketBuilderFlow.datagramPacketBuilder(startsPumpOn, startsPumpOn.length, IPAddress, ClientConstants.SERVER_PORT4);

        ///////////////////////////////////////////////////////////

        JSONObject jsonAuthRiver1 = sendRiverAuthRequest.sendRiverAuthRequest(clientSocket, datagramPacket1, receivePacketRiver1);
        JSONObject jsonAuthRiver2 = sendRiverAuthRequest.sendRiverAuthRequest(clientSocket, datagramPacket2, receivePacketRiver2);
        JSONObject jsonAuthRiver3 = sendRiverAuthRequest.sendRiverAuthRequest(clientSocket, datagramPacket3, receivePacketRiver3);
        JSONObject jsonAuthRiver4 = sendRiverAuthRequest.sendRiverAuthRequest(clientSocket, datagramPacket4, receivePacketRiver4);

        JSONObject startResponseRiver1 = sendStartsRequest.start(clientSocket, startRequestPacket, startResponse);
        System.out.println(startResponseRiver1);

        System.out.println("Autenticações realizadas");

        clientSocket.close();
    }
}
