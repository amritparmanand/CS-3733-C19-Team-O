/*
 * Copyright (c) 2015, Jim Connors
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   * Neither the name of this project nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package socketfx;

import java.net.*;


public class FxSocketServer extends GenericSocket
        implements SocketListener {

    private SocketListener fxListener;
    private ServerSocket serverSocket;

    /**
     * Called whenever a message is read from the socket.  In
     * JavaFX, this method must be run on the main thread and is
     * accomplished by the Platform.runLater() call.  Failure to do so
     * *will* result in strange errors and exceptions.
     * @param line Line of text read from the socket.
     */
    @Override
    public void onMessage(final String line) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fxListener.onMessage(line);
            }
        });
    }

    /**
     * Called whenever the open/closed status of the Socket
     * changes.  In JavaFX, this method must be run on the main thread and is
     * accomplished by the Platform.runLater() call.  Failure to do so
     * *will* result in strange errors and exceptions.
     * @param isClosed true if the socket is closed
     */
    @Override
    public void onClosedStatus(final boolean isClosed) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fxListener.onClosedStatus(isClosed);
            }
        });
    }

    /**
     * Initialize the FxSocketServer up to and including issuing the 
     * accept() method on its socketConnection.
     * @throws java.net.SocketException
     */
    @Override
    protected void initSocketConnection() throws SocketException {
        try {
            /*
             * Create server socket
             */
            serverSocket = new ServerSocket(port);
            /*
             * Allows the socket to be bound even though a previous
             * connection is in a timeout state.
             */
            serverSocket.setReuseAddress(true);
            /*
             * Wait for connection
             */
            if (debugFlagIsSet(Constants.instance().DEBUG_STATUS)) {
                System.out.println("Waiting for connection");
            }
            socketConnection = serverSocket.accept();
            if (debugFlagIsSet(Constants.instance().DEBUG_STATUS)) {
                System.out.println("Connection received from "
                        + socketConnection.getInetAddress().getHostName());
            }
        } catch (Exception e) {
            if (debugFlagIsSet(Constants.instance().DEBUG_EXCEPTIONS)) {
                e.printStackTrace();
            }
            throw new SocketException();
        }
    }

    /**
     * For FxSocketServer class, additional ServerSocket instance has
     * to be closed.
     */
    @Override
    protected void closeAdditionalSockets() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public FxSocketServer(SocketListener fxListener,
            int port, int debugFlags) {
        super(port, debugFlags);
        this.fxListener = fxListener;
    }

    public FxSocketServer(SocketListener fxListener) {
        this(fxListener, Constants.instance().DEFAULT_PORT,
            Constants.instance().DEBUG_NONE);
    }

    public FxSocketServer(SocketListener fxListener, int port) {
        this(fxListener, port, Constants.instance().DEBUG_NONE);
    }
}
