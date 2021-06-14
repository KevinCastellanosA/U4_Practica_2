/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author princ
 */
public class NodoGeneral {
    protected char dato;
    private NodoHijo ini, fin,temp,busca;
    
    public NodoGeneral(char d){
        dato = d;
        ini = fin = null;
    }
    
    //identico a insertar de lista doble pero sin un dato
    public boolean InsertarArista(NodoGeneral hijo){
        NodoHijo enlace = new NodoHijo(hijo);
        
        if(enlace == null) return false;
        
        if(EsHoja()){
            ini = fin = enlace;
            return true;
        }
        
        fin.setSig(enlace);
        enlace.setAnt(fin);
        fin = enlace;
        
        return true;
    }
    
     public boolean EliminarArista(NodoGeneral hijo){
        
        if(ini == fin){
            if(fin.getEnlace() == hijo){
                ini = fin = null;
                return true;
            }
            return false;
        }
        temp = ini;
        if(ini.getEnlace() == hijo){
            ini = temp.getSig();
            ini.setAnt(null);
            temp.setSig(null);
            return true;
        }
        
        if(fin.getEnlace() == hijo){
            temp = fin;
            fin = temp.getAnt();
            fin.setSig(null);
            temp.setAnt(null);
            return true;
        }
        temp = temp.getSig();
        
        while(temp.getEnlace() != hijo && temp != null){
            temp = temp.getSig();
        }
        
        if(temp == null) return false;
        temp.getSig().setAnt(temp.getAnt());
        temp.getAnt().setSig(temp.getSig());
        temp.setSig(null);
        temp.setAnt(null);
        return true;
    }
    
    public boolean EsHoja() {
        return ini == null && fin == null;
    }
    
    public NodoGeneral Hijos(char ValorB) {
        
        if(EsHoja()) return null;
        
        for ( busca = ini; busca != null; busca = busca.getSig()) {
            if(busca.getEnlace().getDato() == ValorB){
                return busca.getEnlace();
            }
        }
        return null;
    }
    
    public char getDato() {
        return dato;
    }

    public void setDato(char dato) {
        this.dato = dato;
    }

    public NodoHijo getIni() {
        return ini;
    }

    public void setIni(NodoHijo ini) {
        this.ini = ini;
    }

    public NodoHijo getFin() {
        return fin;
    }

    public void setFin(NodoHijo fin) {
        this.fin = fin;
    }    
}
