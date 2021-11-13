package com.company.flow;

import com.company.constants.ClientConstants;

import java.nio.ByteBuffer;

public class ByteBufferAllocateFlow {

    public ByteBuffer byteBufferAllocate() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(ClientConstants.BUFFER_SIZE);
        return byteBuffer;
    }
}
