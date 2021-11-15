package com.company.flow;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAdressGetNameFlow {

    public InetAddress inetAddressGetName(String hostName) throws UnknownHostException {
        return InetAddress.getByName(hostName);
    }
}
