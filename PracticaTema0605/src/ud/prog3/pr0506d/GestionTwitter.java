package ud.prog3.pr0506d;


import java.io.*;
import java.util.*;

public class GestionTwitter {

	private static TreeMap<String, UsuarioTwitter> mapaPorNic = new TreeMap<String, UsuarioTwitter>();
	private static HashMap<String, UsuarioTwitter> mapaPorID = new HashMap<String, UsuarioTwitter>();
	private static TreeSet<UsuarioTwitter> treeUsus = new TreeSet<UsuarioTwitter>();
	

	static String nUsuariosConAmigos;
	static String usuariosMapa;
	static String usuariosTree;

	
	public static void addUsuarioID(String id, UsuarioTwitter usu) {
		if(!mapaPorID.containsKey(id)) {
			mapaPorID.put(id, usu);
		}
    }
	
	public static void addUsuarioNick(String nick, UsuarioTwitter usu) {
		if(!mapaPorNic.containsKey(nick)) {
			mapaPorNic.put(nick, usu);
		}
    }
	
	
	public static TreeMap<String, UsuarioTwitter> getMapaPorNic() {
		return mapaPorNic;
	}

	public static void setMapaPorNic(TreeMap<String, UsuarioTwitter> mapaPorNic) {
		GestionTwitter.mapaPorNic = mapaPorNic;
	}

	public static String getUsuariosMapa() {
		return usuariosMapa;
	}
	

	public static HashMap<String, UsuarioTwitter> getMapaPorID() {
		return mapaPorID;
	}

	public static void setMapaPorID(HashMap<String, UsuarioTwitter> mapaPorID) {
		GestionTwitter.mapaPorID = mapaPorID;
	}

	public static void setUsuariosMapa(String usuariosMapa) {
		GestionTwitter.usuariosMapa = usuariosMapa;
	}

	public static void crearTree() {
	    int uConAmigosEnSistema = 0;
	    StringBuilder usuariosMapaBuilder = new StringBuilder();
	    StringBuilder usuariosTreeBuilder = new StringBuilder();

	    for (UsuarioTwitter usu : mapaPorNic.values()) {
	        int cont = 0;
	        ArrayList<String> amigosEnSistema = new ArrayList<>();

	        if (usu.getFriends() != null) {
	            for (String id : usu.getFriends()) {
	                if (mapaPorID.containsKey(id)) {
	                    cont++;
	                    amigosEnSistema.add(id);
	                }
	            }
	            usu.setAmigosEnSistema(amigosEnSistema);
	        }

	        if (cont >= 10) {
	            uConAmigosEnSistema++;
	            usu.setNAmigosEnSistema(cont);
	            treeUsus.add(usu);
	            usuariosMapaBuilder.append("Usuario ").append(usu.getScreenName())
	                    .append(" tiene ").append(usu.getFriendsCount() - usu.getNAmigosEnSistema())
	                    .append(" amigos fuera de nuestro sistema y ").append(usu.getNAmigosEnSistema())
	                    .append(" dentro.\n");
	        }
	    }

	    nUsuariosConAmigos = "Hay " + uConAmigosEnSistema + " usuarios con algunos amigos dentro de nuestro sistema.";

	    for (UsuarioTwitter u : treeUsus) {
	        usuariosTreeBuilder.append(u.getScreenName()).append(" - ").append(u.getNAmigosEnSistema())
	                .append(" amigos\n");
	    }

	    usuariosMapa = usuariosMapaBuilder.toString();
	    usuariosTree = usuariosTreeBuilder.toString();
	}

	
	
	
	public static TreeSet<UsuarioTwitter> getTreeSet(){
		return treeUsus;
	}
	
	public static String getTextoSalida() {
		String str = "";
		
		str += "Usuarios con amigos en sistema: \n";
		str += usuariosMapa + "\n";
		str += nUsuariosConAmigos + "\n";
		str += "Usuarios más sociables \n";
		str += usuariosTree;
		return str;
	}
	
	{
		try {
			
				String fileName = "C:\\Users\\pemma\\eclipse-workspace\\Practica05\\src\\ud\\prog3\\pr0506d\\data.csv";
				CSV.processCSV( new File( fileName ) );
			} catch (Exception e) {
				//e.printStackTrace();
			}
			crearTree();
		}
}