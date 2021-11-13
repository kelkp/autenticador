package com.company.flow;

import com.company.constants.ClientConstants;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAdressGetNameFlow {

    public InetAddress inetAddressGetName() throws UnknownHostException {
        return InetAddress.getByName(ClientConstants.SERVER_HOST_NAME);
    }
}
