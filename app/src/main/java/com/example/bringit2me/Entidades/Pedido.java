package com.example.bringit2me.Entidades;

import com.google.android.gms.maps.model.LatLng;

public class Pedido {
    private int id;
    private String origen;
    private String destino;
    private int precio;
    private String fecha;
    LatLng locationOr;
    LatLng locationDes;

    public Pedido(int id, String origen, String destino, int precio, String fecha, Double latitudeOr, Double longitudeOr, Double latitudeDes, Double longitudeDes){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.fecha = fecha;
        this.locationOr = new LatLng(latitudeOr,longitudeOr);
        this.locationDes = new LatLng(latitudeDes,longitudeDes);

    }

    public int getPrecio() {
        return precio;
    }

    public String getDestino() {
        return destino;
    }

    public String getFecha() {
        return fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public LatLng getLocationDes() {
        return locationDes;
    }

    public LatLng getLocationOr() {
        return locationOr;
    }
}

