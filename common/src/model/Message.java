package model;


import java.io.*;

/**
 * Message class for communication between server and client
 * Implements Serializable interface
 *
 */
public class Message implements Serializable {
    /** message header value for successful processing of the client's request by the server */
    public static final String OK = "ok";
    /** message header value for failed processing of the client's request by the server */
    public static final String ERROR = "error";
    /** line separator */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /** message header attribute */
    private String header;
    /** message body attribute */
    private String body;

    /**
     * Constructor for Message
     *
     * @param header: message header
     * @param body: message body
     */
    Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    /**
     * Method that return a MessageBuilder instance
     *
     * @return new MessageBuilder instance
     */
    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    /** getter for header attribute */
    public String getHeader() {
        return header;
    }

    /** getter for body attribut */
    public String getBody() {
        return body;
    }

    /**
     * Method to write the Message to a specified OutputStream
     *
     * @param os: output stream
     * @throws IOException in case the OutputStream write operation fails
     */
    public void writeTo(OutputStream os) throws IOException {
        os.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }

    /**
     * Method to read the Message from a specified InputStream
     *
     * @param is: input stream
     * @throws IOException in care the InputStream read operation fails
     */
    public void readFrom(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        header = br.readLine();
        body = br.readLine();
    }

    /**
     * String conversion for Message
     *
     * @return String representation for the Message
     */
    @Override
    public String toString() {
        return "Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    /**
     * public MessageBuilder inner class
     *
     */
    public static class MessageBuilder {
        /** message header */
        private String header;
        /** message body */
        private String body;

        /**
         * Method to update the header of the builder
         *
         * @param header: message header
         * @return the Message, for which the header has been updated
         */
        public MessageBuilder header(String header) {
            this.header = header;
            return this;
        }

        /**
         * Method to update the body of the builder
         *
         * @param body: message body
         * @return the MessageBuilder, for which the body has been updated
         */
        public MessageBuilder body(String body) {
            this.body = body;
            return this;
        }

        /**
         * Method that returns a new Message instance with header and body
         *
         * @return new Message
         */
        public Message build() {
            return new Message(header, body);
        }
    }
}

