package main;

import com.google.gson.Gson;

import processing.core.PApplet;
import processing.core.PImage;

public class AndroidPeerB extends PApplet{
	
	UDPConnectionPeerB udp;
	boolean listoelpedido;

	public static void main(String[] args) {
		PApplet.main("main.AndroidPeerB");
		

	}
	
	PImage BonYurt;
	PImage JugoHit;
	PImage Perro;
	PImage Sandwich;
	Gson gson;
	Pedido pedido;
	String mensaje;
	
	public void settings() {
		size(500,500);
	}
	public void setup() {
		
		udp = new UDPConnectionPeerB();
		udp.start();
		udp.setPeerB(this);
		BonYurt=loadImage("../Data/Bon yurt.jpg");
		JugoHit=loadImage("../Data/Jugo hit.jpg");
		Perro=loadImage("../Data/Perro.jpg");
		Sandwich=loadImage("../Data/Sandwich.jpg");
		gson=new Gson();
	
	}
	
	public void draw() {
		background(0);
		
		image(BonYurt, 10,10,100,100);
		image(JugoHit, 120,10,80,100);
		image(Perro, 220,10,130,100);
		image(Sandwich, 360,10,100,100);
		if(listoelpedido) {
			text("Ya esta listo el pedido",200,400);
			if(frameCount%200==0) {
				listoelpedido=false;
			}
			
		}
		
	}

	public void mousePressed() {
		
		
		if(mouseX>10&&mouseX<110&&mouseY>10&&mouseY<110) {
			System.out.println("BonYurt");
			pedido= new Pedido("BonYurt");
			mensaje=gson.toJson(pedido);
			udp.sendMessage(mensaje);
	
		}
		if(mouseX>120&&mouseX<220&&mouseY>10&&mouseY<110) {
			System.out.println("JugoHit");
			pedido= new Pedido("JugoHit");
			mensaje=gson.toJson(pedido);
			udp.sendMessage(mensaje);
		}
		if(mouseX>220&&mouseX<330&&mouseY>10&&mouseY<110) {
			System.out.println("Perro");
			pedido= new Pedido("Perro");
			mensaje=gson.toJson(pedido);
			udp.sendMessage(mensaje);
		}
		if(mouseX>360&&mouseX<460&&mouseY>10&&mouseY<110) {
			System.out.println("Sandwich");
			pedido= new Pedido("Sandwich");
			mensaje=gson.toJson(pedido);
			udp.sendMessage(mensaje);
			
			
		}
		
	}
	public void onConfirmacion(ConfirmaciondePedido confirmacion) {
		if(confirmacion.isListoelpedido()) {
			listoelpedido=true;
			System.out.println("confirmacion");
		}
		
	}
}
