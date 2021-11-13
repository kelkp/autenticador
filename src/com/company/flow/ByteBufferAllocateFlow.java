package com.company.flow;

import com.company.constants.ClientConstants;

import java.nio.ByteBuffer;

public class ByteBufferAllocateFlow {

    public ByteBuffer byteBufferAllocate() {
        return ByteBuffer.allocate(ClientConstants.BUFFER_SIZE);
    }
}
