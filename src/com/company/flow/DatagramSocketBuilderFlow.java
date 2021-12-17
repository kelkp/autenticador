package com.company.flow;

import com.company.constants.ClientConstants;

import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramSocketBuilderFlow {

    public DatagramSocket datagramSocketbuilder() throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.setSoTimeout(ClientConstants.DATAGRAM_TIMEOUT);
        return datagramSocket;
    }

}
