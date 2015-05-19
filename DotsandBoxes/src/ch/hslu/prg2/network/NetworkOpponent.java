/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Adrian Ruckli, Patrick Rossacher
 */
public class NetworkOpponent {

    private int port = 0;

    public void server(int port) {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            while (true) {
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                int len = packet.getLength();
                byte[] data = packet.getData();
                //data = new Date().toString().getBytes();
                packet = new DatagramPacket(data, data.length, address, port);
                socket.send(packet);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void client() {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address
                    = InetAddress.getByName("localhost");
            byte[] raw = new byte[1024];
            DatagramPacket packet = new DatagramPacket(
                    raw, raw.length, address, 13);
            socket.send(packet);
            socket.receive(packet);
            int len = packet.getLength();
            byte[] data = packet.getData();
            System.out.println(new String(data, 0, len));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
