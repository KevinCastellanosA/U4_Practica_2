/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author princ
 */
public class ArbolGeneral {
   private NodoGeneral raiz,padre;
    
    public ArbolGeneral(){
        raiz = null;
    }
    
    public boolean Insertar(char dato, String path){
        if(raiz == null){
            raiz = new NodoGeneral(dato);
            if (raiz == null) return false;
            return true;
        }
        if(path.isEmpty()) return false;
        
        NodoGeneral padre = BuscarNodo(path);
        
        if(padre == null) return false;
        
        NodoGeneral hijoYaExiste = BuscarNodo(path+"/"+dato);
        if(hijoYaExiste != null) return false;
        
        NodoGeneral nuevo = new NodoGeneral(dato);
        
        return padre.InsertarArista(nuevo);
    }
    
     public boolean Eliminar(String path){
        if(raiz  == null) return false;
        NodoGeneral hijo = BuscarNodo(path);
        if(hijo == null) return false;
        
        if(!hijo.EsHoja()) return false;
        
        if(hijo == raiz){
            raiz = null;
            return true;
        }
        String PathOriginal = getPath(path);
        padre = BuscarNodo(PathOriginal);
        
        return padre.EliminarArista(hijo);
    }
    
    private NodoGeneral BuscarNodo(String path) {
        path = path.substring(1);
        String[] vector = path.split("/");
        if(vector[0].charAt(0) == raiz.getDato()){  
            if(vector.length == 1) return raiz;
            padre = raiz;
            if(vector.length == 2){
                for (int i = 1; i < 2; i++) {
                    padre = padre.Hijos(vector[i].charAt(0));
                    if(padre == null) return null;
                }
                return padre;
            }
            
            return BuscarRecursivo(padre, path.substring(2));
        }
        return null;
    }
    
    private NodoGeneral BuscarRecursivo(NodoGeneral padre, String path){
        if(padre == null) return null;
        
        if (path.length() == 1) {
            return padre.Hijos(path.charAt(0));
        }
        
        return BuscarRecursivo(padre.Hijos(path.charAt(0)), path.substring(2));
    }
     public NodoGeneral BuscarPath(String path) {
        path = path.substring(1);
        if (path.split("/")[0].charAt(0) == raiz.dato && path.split("/").length == 1) {
            return raiz;
        }
        if (path.length() == 3) {
            return raiz.Hijos(path.charAt(2));
        }
        return BuscarRecursivo( raiz.Hijos(path.charAt(2)),path.substring(4));
    }
    private String getPath(String path) {
        int UltimoNodo = path.lastIndexOf("/");
        return path.substring(0,UltimoNodo);
    }   
}
