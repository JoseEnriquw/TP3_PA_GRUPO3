package Util;

public class UtilsSQL {
    public static String QUERY_CREATE_USUARIOS = "create table usuarios(ID integer primary key autoincrement, Nombre text, Correo text, Contrasenia text)";
    public static String QUERY_LOGIN = "select ID,Nombre from usuarios where Correo = '%1$s' and Contrasenia = '%2$s'";
    public static String SELECT_USUARIO_BY_NAME = "select ID from usuarios where Nombre = ?";
    public static String SELECT_USUARIO_BY_MAIL = "select ID from usuarios where Correo = ?";
}
