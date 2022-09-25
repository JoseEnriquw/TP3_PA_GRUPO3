package Util;

public class UtilsSQL {
    public static String querty_create_Usuarios = "create table usuarios(ID integer primary key autoincrement, Nombre text, Correo text, Contrasenia text)";
    public static String query_login = "select ID,Nombre from usuarios where Correo = '%1$s' and Contrasenia = '%2$s'";
}
